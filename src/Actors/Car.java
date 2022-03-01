package Actors;

import Intersection.Street;
import Utils.SimConstants;

public class Car extends Actor {

    protected Direction dir;
    protected double timeWaiting;
    protected boolean crossing;
    protected double crossTime;
    protected Street street;
    protected int[] position;

    public Car(Direction dir, Street street) {
        this.dir = dir;
        this.timeWaiting = 0;
        this.crossing = false;
        this.street = street;
        position = new int[2];

        switch (street.ordinal()) {
            case 0:
                position[0] = SimConstants.CAR_NORTH_POSITION_X - (SimConstants.CAR_WIDTH / 2);
                position[1] = SimConstants.CAR_NORTH_POSITION_Y - (SimConstants.CAR_HEIGHT / 2);;
                break;
            case 1:
                position[0] = SimConstants.CAR_EAST_POSITION_X - (SimConstants.CAR_WIDTH / 2);;
                position[1] = SimConstants.CAR_EAST_POSITION_Y - (SimConstants.CAR_WIDTH / 2);;
                break;
            case 2:
                position[0] = SimConstants.CAR_SOUTH_POSITION_X - (SimConstants.CAR_WIDTH / 2);;
                position[1] = SimConstants.CAR_SOUTH_POSITION_Y - (SimConstants.CAR_WIDTH / 2);;
                break;
            case 3:
                position[0] = SimConstants.CAR_WEST_POSITION_X - (SimConstants.CAR_WIDTH / 2);;
                position[1] = SimConstants.CAR_WEST_POSITION_Y - (SimConstants.CAR_WIDTH / 2);;
                break;
        }
    }

    public double getCrossTime() {
        return crossTime;
    }

    public Street getStreet() {
        return this.street;
    }

    public int getPositionX() {
        return position[0];
    }

    public int getPositionY() {
        return position[1];
    }

    public void setPosition(int x, int y) {
        position[0] = x;
        position[1] = y;
    }

    public void updatePosition() {
        // All of this will be changed soon.
        switch (street.ordinal()) {
            case 0:
                position[1]++;
                break;
            case 1:
                position[0]--;
                break;
            case 2:
                position[1]--;
                break;
            case 3:
                position[0]++;
                break;
        }
    }
}
