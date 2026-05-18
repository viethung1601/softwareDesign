package monitor;

import model.Notification;
import model.WebsiteSubscription;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WebsiteMonitor {
    public List<Notification> checkUpdates(List<WebsiteSubscription> subscriptions) {
        List<Notification> notifications = new ArrayList<>();
        for (WebsiteSubscription subscription : subscriptions) {
            Notification notification = detectUpdate(subscription);

            if (notification != null) {
                notifications.add(notification);
            }
        }
        return notifications;
    }

    public Notification detectUpdate(WebsiteSubscription subscription) {
        Random random = new Random();

        boolean hasUpdate = random.nextBoolean();
        if (hasUpdate) {
            return new Notification(
                    "Update detected for: " + subscription.getWebsite().getUrl()
            );
        }

        return null;
    }
}
