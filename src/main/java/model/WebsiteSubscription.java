package model;

import enums.CommunicationChannel;
import enums.Frequency;

public class WebsiteSubscription {
    private Website website;
    private Frequency frequency;
    private CommunicationChannel communicationChannel;
    private String previousContent;

    public WebsiteSubscription(Website website, Frequency frequency, CommunicationChannel communicationChannel) {
        this.website = website;
        this.frequency = frequency;
        this.communicationChannel = communicationChannel;
        this.previousContent = "";
    }

    public Website getWebsite() {
        return website;
    }

    public void modify(Frequency frequency, CommunicationChannel communicationChannel) {
        this.frequency = frequency;
        this.communicationChannel = communicationChannel;
    }

    public void cancel() {
        System.out.println("Canceling website subscription...");
    }
    public String getPreviousContent() {
        return previousContent;
    }
    public void setPreviousContent(String previousContent) {
        this.previousContent = previousContent;
    }

}
