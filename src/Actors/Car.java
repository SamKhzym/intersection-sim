package Actors;

import Intersection.Street;
import Utils.SimConstants;

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

    public void setCrossing(boolean crossing) {
        System.out.println("time to cross? " + crossing); this.crossing = crossing; }

    public double getCrossTime() {
        return crossTime;
    }

    public Street getStreet() {
        return this.street;
    }

    public void updateCar() {

        double RADIUS = 50;

        if (this.crossing) {
            if (this.dir == Direction.STRAIGHT) {
                this.setAccelCartesian(new double[]{4,0});
            }
            else if (this.dir == Direction.LEFT) {
                if (this.getVelMag() == 0) { this.setVelPolar(5, -Math.PI/2); }
                double accelMag = Math.pow(this.getVelMag(), 2) / RADIUS;
                double accelAngle = this.getHeading() - Math.PI/2;
                this.setAccelPolar(accelMag, accelAngle);
            }
            else if (this.dir == Direction.RIGHT) {
                if (this.getVelMag() == 0) { this.setVelPolar(5, -Math.PI/2); }
                double accelMag = Math.pow(this.getVelMag(), 2) / RADIUS;
                double accelAngle = this.getHeading() + Math.PI/2;
                this.setAccelPolar(accelMag, accelAngle);
            }
        }
        else {
            this.setVelCartesian(new double[]{0,0});
            this.setAccelCartesian(new double[]{0,0});
        }

        this.updateKinematics(SimConstants.SIM_SAMPLE_RATE);

    }

}
