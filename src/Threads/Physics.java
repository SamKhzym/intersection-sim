package Threads;

import Actors.Actor;
import Interfaces.Subscriber;
import Intersection.Intersection;
import Stuff.Timer;
import Utils.SimConstants;

import java.util.ArrayList;

public class Physics extends Thread implements Subscriber {
    private boolean paused;
    private ArrayList<Actor> actors;

    public Physics() {
        paused = false;
        actors = new ArrayList<>();
    }

    public void update() {
        paused = !paused;
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public void run() {
        Intersection intersection = new Intersection();
        Timer time = new Timer();

        while (true) {
            time.start();

            if (!paused) {
                //Update intersection and actor data
                actors = intersection.execute();
            }

            long currentTime = time.getTime();
            if (currentTime < 1000.0/SimConstants.PHYSICS_TIME) {
                try {
                    Thread.sleep((long)(1000.0/SimConstants.PHYSICS_TIME - currentTime));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
