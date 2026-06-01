package monitor;

import Observer.Observer;
import model.Notification;
import model.WebsiteSubscription;
import strategy.WebsiteComparisionStrategy;

import java.util.ArrayList;
import java.util.List;


public class WebsiteMonitor implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private WebsiteComparisionStrategy comparisionStrategy;

    public WebsiteMonitor(WebsiteComparisionStrategy comparisionStrategy) {
        this.comparisionStrategy = comparisionStrategy;
    }
    public void checkUpdates(List<WebsiteSubscription> subscriptions) {
        for (WebsiteSubscription subscription : subscriptions) {
            String currentContent = mockUpCurrentContent(subscription);
            Notification notification = detectUpdate(subscription, currentContent);
            if (notification != null) {
                notifyObservers(notification)
                ;
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
        } else {
            return new Notification(
                    "No change detected for: " + subscription.getWebsite().getUrl()
            );
        }

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
        return "<html><body>Mock content for " + subscription.getWebsite().getUrl() + "</body></html>";
    }
}
