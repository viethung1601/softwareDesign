package model;

import enums.CommunicationChannel;
import enums.Frequency;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<WebsiteSubscription> subscriptions;

    public User() {
        subscriptions = new ArrayList<>();
    }

    public WebsiteSubscription registerForSubscription(String url, Frequency frequency, CommunicationChannel channel) {
        Website website = new Website(url);

        WebsiteSubscription subscription = new WebsiteSubscription(website, frequency, channel);
        subscriptions.add(subscription);

        return subscription;
    }

    public List<WebsiteSubscription> getSubscriptions() {
        return subscriptions;
    }
}