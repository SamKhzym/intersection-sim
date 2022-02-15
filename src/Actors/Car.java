package Actors;

import Intersection.Street;

public class Car extends Actor {

    protected Direction dir;
    protected double timeWaiting;
    protected boolean crossing;
    protected double crossTime;
    protected Street street;

    public Car(Direction dir, Street street) {
        this.dir = dir;
        this.timeWaiting = 0;
        this.crossing = false;
        this.street = street;
    }

    public double getCrossTime() {
        return crossTime;
    }

    public Street getStreet() {
        return this.street;
    }

}
