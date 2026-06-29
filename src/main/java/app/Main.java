package app;

import enums.CommunicationChannel;
import enums.Frequency;
import model.User;
import observer.NotificationObserver;
import monitor.WebsiteMonitor;
import strategy.ContentSizeComparision;
import strategy.HtmlContentComparision;
import strategy.TextContentComparision;

public class Main {
    public static void main(String[] args) {
        User hung = new User();
        hung.registerForSubscription("https://www.dw.com/de/themen/s-9077", Frequency.DAILY, CommunicationChannel.EMAIL);
        hung.registerForSubscription("https://mail.google.com/mail/u/0/?hl=vi#inbox", Frequency.DAILY, CommunicationChannel.EMAIL);
        hung.registerForSubscription("https://www.instagram.com/hylo_open/", Frequency.MONTHLY, CommunicationChannel.SMS);
        hung.registerForSubscription("https://www.youtube.com/", Frequency.MONTHLY, CommunicationChannel.SMS);

        WebsiteMonitor websiteMonitor = new WebsiteMonitor(new TextContentComparision());
        NotificationObserver observer = new NotificationObserver();
        websiteMonitor.addObserver(observer);
        websiteMonitor.checkUpdates(hung.getSubscriptions());
        websiteMonitor.checkUpdates(hung.getSubscriptions());
        websiteMonitor.checkUpdates(hung.getSubscriptions());

    }

}