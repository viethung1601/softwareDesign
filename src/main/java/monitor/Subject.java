package monitor;

import Observer.Observer;
import model.Notification;


public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Notification notification);
}
