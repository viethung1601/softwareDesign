package Observer;

import model.Notification;

public class NotificationObserver implements Observer {

    @Override
    public void update(Notification notification) {
        notification.send();
    }
}
