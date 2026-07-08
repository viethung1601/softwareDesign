package app;

import enums.CommunicationChannel;
import enums.Frequency;
import model.User;
import observer.NotificationObserver;
import monitor.WebsiteMonitor;
import strategy.HtmlContentComparison;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide a website URL as command line parameter.");
            System.out.println("Example: java app.Main https://example.com");
            return;
        }

        String websiteUrl = args[0];

        User user = new User();
        user.registerForSubscription(
                websiteUrl,
                Frequency.DAILY,
                CommunicationChannel.EMAIL
        );

        WebsiteMonitor websiteMonitor = new WebsiteMonitor(new HtmlContentComparison());
        NotificationObserver observer = new NotificationObserver();

        websiteMonitor.addObserver(observer);

        System.out.println("Monitoring website: " + websiteUrl);

        websiteMonitor.checkUpdates(user.getSubscriptions());
        websiteMonitor.checkUpdates(user.getSubscriptions());
    }
}