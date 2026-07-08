package monitor;

import observer.Observer;
import model.Notification;
import model.WebsiteSubscription;
import strategy.WebsiteComparisonStrategy;

import java.util.ArrayList;
import java.util.List;


public class WebsiteMonitor implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private WebsiteComparisonStrategy comparisionStrategy;
    private int counter = 0;

    public WebsiteMonitor(WebsiteComparisonStrategy comparisionStrategy) {
        this.comparisionStrategy = comparisionStrategy;
    }
    public void checkUpdates(List<WebsiteSubscription> subscriptions) {
        for (WebsiteSubscription subscription : subscriptions) {
            String currentContent = mockUpCurrentContent(subscription);
            Notification notification = detectUpdate(subscription, currentContent);
            if (notification != null) {
                notifyObservers(notification);
            }
        }
    }

    public Notification detectUpdate(WebsiteSubscription subscription, String currentContent) {
        String previousContent = subscription.getPreviousContent();
        if(previousContent == null || previousContent.isBlank()){
            subscription.setPreviousContent(currentContent);
            return null;
        }

        boolean hasChanged = comparisionStrategy.hasChanged(previousContent, currentContent);

        subscription.setPreviousContent(currentContent);

        if (hasChanged) {
            return new Notification(
                    "Update detected for: " + subscription.getWebsite().getUrl()
            );
        }
        return null;

    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Notification notification) {
        for (Observer observer : observers) {
            observer.update(notification);
        }
    }

    private String mockUpCurrentContent(WebsiteSubscription subscription) {
        counter++;
        return "<html><body>Mock content version " + counter + " for "
                + subscription.getWebsite().getUrl()
                + "</body></html>";
    }
}
