package gui;

import enums.CommunicationChannel;
import enums.Frequency;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Notification;
import model.User;
import model.WebsiteSubscription;
import monitor.WebsiteMonitor;

import java.util.List;

public class WebsiteMonitorApp extends Application {

    private final User user = new User();
    private final WebsiteMonitor monitor = new WebsiteMonitor();

    private final ObservableList<WebsiteSubscription> subscriptionList =
            FXCollections.observableArrayList();

    private ListView<WebsiteSubscription> subscriptionListView;
    private TextArea notificationArea;

    @Override
    public void start(Stage stage) {

        Label title = new Label("Website Monitor System");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField urlField = new TextField();
        urlField.setPromptText("Enter website URL");

        ComboBox<Frequency> frequencyBox =
                new ComboBox<>(FXCollections.observableArrayList(Frequency.values()));
        frequencyBox.setValue(Frequency.DAILY);

        ComboBox<CommunicationChannel> channelBox =
                new ComboBox<>(FXCollections.observableArrayList(CommunicationChannel.values()));
        channelBox.setValue(CommunicationChannel.EMAIL);

        Button registerButton = new Button("Register");
        Button modifyButton = new Button("Modify Selected");
        Button cancelButton = new Button("Cancel Selected");
        Button checkButton = new Button("Check Updates");

        subscriptionListView = new ListView<>(subscriptionList);

        notificationArea = new TextArea();
        notificationArea.setEditable(false);
        notificationArea.setPromptText("Notifications will appear here...");

        registerButton.setOnAction(e -> {
            String url = urlField.getText();

            if (url == null || url.isBlank()) {
                showAlert("Please enter a website URL.");
                return;
            }

            WebsiteSubscription subscription =
                    user.registerForSubscription(
                            url,
                            frequencyBox.getValue(),
                            channelBox.getValue()
                    );

            subscriptionList.add(subscription);
            urlField.clear();
        });

        modifyButton.setOnAction(e -> {
            WebsiteSubscription selected =
                    subscriptionListView.getSelectionModel().getSelectedItem();

            if (selected == null) {
                showAlert("Please select a subscription to modify.");
                return;
            }

            selected.modify(
                    frequencyBox.getValue(),
                    channelBox.getValue()
            );

            subscriptionListView.refresh();
        });

        cancelButton.setOnAction(e -> {
            WebsiteSubscription selected =
                    subscriptionListView.getSelectionModel().getSelectedItem();

            if (selected == null) {
                showAlert("Please select a subscription to cancel.");
                return;
            }

            selected.cancel();
            subscriptionList.remove(selected);
            user.getSubscriptions().remove(selected);
        });

        checkButton.setOnAction(e -> {
            List<Notification> notifications =
                    monitor.checkUpdates(user.getSubscriptions());

            notificationArea.clear();

            if (notifications.isEmpty()) {
                notificationArea.appendText("No updates detected.\n");
            } else {
                for (Notification notification : notifications) {
                    notificationArea.appendText(notification.getMessage() + "\n");
                }
            }
        });

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        form.add(new Label("Website URL:"), 0, 0);
        form.add(urlField, 1, 0);

        form.add(new Label("Frequency:"), 0, 1);
        form.add(frequencyBox, 1, 1);

        form.add(new Label("Channel:"), 0, 2);
        form.add(channelBox, 1, 2);

        HBox buttonBox = new HBox(10, registerButton, modifyButton, cancelButton, checkButton);

        VBox root = new VBox(
                15,
                title,
                form,
                buttonBox,
                new Label("Registered Subscriptions:"),
                subscriptionListView,
                new Label("Notifications:"),
                notificationArea
        );

        root.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(root, 700, 600);

        stage.setTitle("Website Monitor");
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}