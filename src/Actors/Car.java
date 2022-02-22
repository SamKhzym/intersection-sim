package Actors;

import Intersection.Street;

public class Car extends Actor {

    protected Direction dir;
    protected double timeWaiting;
    protected boolean crossing;
    protected double crossTime;
    protected Street street;

    public Car(Direction dir, Street street) {
        super();
        this.dir = dir;
        this.timeWaiting = 0;
        this.crossing = false;
        this.street = street;
    }

    public Car(Direction dir, Street street, double[] position, double[] vel, double[] accel) {
        super(position, vel, accel);
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

    public void updateCar() {

        if (this.crossing) {

        }

    }

}
