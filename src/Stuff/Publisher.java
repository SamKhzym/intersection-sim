package Stuff;

import Interfaces.Subscriber;

import java.util.ArrayList;

public class Publisher {
    private ArrayList<Subscriber> subscribers;

    public Publisher() {
        subscribers = new ArrayList<>();
    }

    public void addSubscriber(Subscriber s) {
        subscribers.add(s);
    }

    public void removeSubscriber(Subscriber s) {
        subscribers.remove(s);
    }

    public void notifySubscribers() {
        for (Subscriber s : subscribers) {
            s.update();
        }
    }
}
