package Actors;

public class Actor {

    //All arrays are in the form [x, y]. Units are meters, meters/sec, meters/sec^2.
    protected double[] position;
    protected double[] velocity;
    protected double[] acceleration;

    public Actor() {
        this.position = new double[]{0, 0};
        this.velocity = new double[]{0, 0};
        this.acceleration = new double[]{0, 0};
    }

    public Actor(double[] position, double[] velocity, double[] acceleration) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public double[] getPosition() { return this.position; }

    public void setPosition(double[] position) {
        this.position = position;
    }

    public void setVelocity(double[] vel) {
        this.velocity = vel;
    }

    public void setAcceleration(double[] accel) {
        this.acceleration = accel;
    }

    public void updateKinematics(double sampleTime) {
        this.velocity[0] += this.acceleration[0]*sampleTime;
        this.velocity[1] += this.acceleration[1]*sampleTime;
        this.position[0] += this.velocity[0]*sampleTime;
        this.position[1] += this.velocity[1]*sampleTime;
    }

}
