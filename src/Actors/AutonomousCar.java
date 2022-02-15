package Actors;

import Intersection.Street;
import Utils.HelperMethods;
import Utils.SimulationConstants;

public class AutonomousCar extends Car {

    public AutonomousCar(Direction dir, Street street) {
        super(dir, street);
        this.crossTime = getCrossTime();
    }

    @Override
    public double getCrossTime() {

        if (this.dir == Direction.LEFT)  {
            return HelperMethods.randomSampleFromDistribution(
                    SimulationConstants.LEFT_CROSS_TIME_AUTO_MEAN,
                    SimulationConstants.LEFT_CROSS_TIME_AUTO_STDDEV);
        }
        else if (this.dir == Direction.RIGHT) {
            return HelperMethods.randomSampleFromDistribution(
                    SimulationConstants.RIGHT_CROSS_TIME_AUTO_MEAN,
                    SimulationConstants.RIGHT_CROSS_TIME_AUTO_STDDEV);
        }
        else if (this.dir == Direction.STRAIGHT) {
            return HelperMethods.randomSampleFromDistribution(
                    SimulationConstants.STRAIGHT_CROSS_TIME_AUTO_MEAN,
                    SimulationConstants.STRAIGHT_CROSS_TIME_AUTO_STDDEV);
        }
        else return -1;

    }

    public Street getStreet() {
        return this.street;
    }

}
