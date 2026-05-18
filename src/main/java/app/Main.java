package app;

import enums.CommunicationChannel;
import enums.Frequency;
import model.Notification;
import model.User;
import monitor.WebsiteMonitor;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        User hung = new User();
        hung.registerForSubscription("https://www.google.com/search?q=bwf&sca_esv=e2c09a0892320c96&sxsrf=ANbL-n6f_OpOiSHqIRJsXe0X9gIxl69qNA%3A1779112287914&source=hp&ei=XxkLaqafNcr_7M8P6b_f8AU&iflsig=AFdpzrgAAAAAagsnb5_kNvUmiulYmr2rILG6EKYYcebM&gs_ssp=eJzj4tTP1TdIskhOMzVg9GJOKk8DACUeBGQ&oq=bwf&gs_lp=Egdnd3Mtd2l6IgNid2YqAggCMgoQIxiABBiKBRgnMgwQIxiABBiKBRgTGCcyBRAuGIAEMgUQABiABDIFEAAYgAQyBRAAGIAEMgUQLhiABDIFEAAYgAQyBRAAGIAEMgUQABiABEj8J1BrWJYYcAV4AJABAJgBUqAB4gOqAQE3uAEDyAEA-AEBmAIMoAKZBKgCCsICBxAjGPAFGCfCAgsQLhiABBjHARjRA8ICBxAjGOoCGCfCAgcQLhjqAhgnwgIKECMY8AUY6gIYJ8ICBBAjGCeYAwbxBV66PpFVkRuVkgcCMTKgB9xMsgcBN7gHgwTCBwUwLjcuNcgHKYAIAQ&sclient=gws-wiz", Frequency.DAILY, CommunicationChannel.EMAIL);
        hung.registerForSubscription("https://mail.google.com/mail/u/0/?hl=vi#inbox", Frequency.DAILY, CommunicationChannel.EMAIL);
        hung.registerForSubscription("https://www.instagram.com/hylo_open/", Frequency.MONTHLY, CommunicationChannel.SMS);
        hung.registerForSubscription("https://www.youtube.com/", Frequency.MONTHLY, CommunicationChannel.SMS);

        WebsiteMonitor websiteMonitor = new WebsiteMonitor();
        List<Notification> showToConsole = websiteMonitor.checkUpdates(hung.getSubscriptions());

        for (Notification notification : showToConsole) {
            notification.send();
        }


    }

}