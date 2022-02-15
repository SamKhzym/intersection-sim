package Intersection;

import Actors.AutonomousCar;
import Actors.Car;
import Actors.Direction;
import Actors.DrivenCar;
import Utils.SimulationConstants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Intersection {

    private ArrayList<Queue<Car>> cars;
    private Queue<Car> crossings;
    private long nextCrossTime = 0;
    private long nextSpawnTime = 0;

    //Create a new Intersection object
    public Intersection() {
        cars = new ArrayList<Queue<Car>>();
        for (int i = 0; i < 4; i++) {
            cars.add(new LinkedList<Car>());
        }
        crossings = new LinkedList<Car>();
    }

    public void execute() {

        spawnCarRandomly();
        crossCar();

    }

    //Return the next car in the crossings queue if the crossing time of the last car has passed.
    public Car crossCar() {

        if (System.currentTimeMillis() >= nextCrossTime && crossings.size() > 0) {
            //remove the car from the crossings array and the array of cars
            Car car = crossings.remove();
            int street = car.getStreet().ordinal();
            cars.get(street).remove();

            nextCrossTime = System.currentTimeMillis() + (long)car.getCrossTime()*1000;
            System.out.println("CAR CROSSING!");

            return car;
        }
        else
            return null;

    }

    private void spawnCarRandomly() {

        if (System.currentTimeMillis() >= nextSpawnTime) {

            Random rand = new Random();

            int streetInt = rand.nextInt(4);
            int directionInt = rand.nextInt(3);

            boolean autonomous = rand.nextDouble() <= SimulationConstants.AUTONOMOUS_PERCENTAGE;
            Street street = Street.values()[streetInt];
            Direction dir = Direction.values()[directionInt];

            spawnCar(autonomous, street, dir);

            nextSpawnTime = System.currentTimeMillis() +
                    (long)(1000*(rand.nextDouble()*(SimulationConstants.CAR_SPAWN_TIME_MAX - SimulationConstants.CAR_SPAWN_TIME_MIN) +
                            SimulationConstants.CAR_SPAWN_TIME_MIN));

        }

    }

    private void spawnCar(boolean autonomous, Street street, Direction dir) {

        Car car = autonomous ? new AutonomousCar(dir, street) : new DrivenCar(dir, street);
        cars.get(street.ordinal()).add(car);

        //THIS LOGIC ADDS A CAR TO THE EVENT QUEUE BASED ON SPAWN TIME. TO BE CHANGED.
        crossings.add(car);

        System.out.println("CAR SPAWNED! Autonomous = " + autonomous + ", Street = " + street + ", Direction = " + dir);

    }

}
