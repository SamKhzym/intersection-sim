package Intersection;

import Actors.AutonomousCar;
import Actors.Car;
import Actors.Direction;
import Actors.DrivenCar;
import Utils.SimConstants;
import Utils.SimulationWindow;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Intersection {

    private ArrayList<ArrayDeque<Car>> cars;
    private ArrayDeque<Car> crossings;
    private double nextCrossTime = 0;
    private double nextSpawnTime = 0;
    private double elapsedTime = 0;
    public SimulationWindow window;

    //Create a new Intersection object
    public Intersection() {

        this.cars = new ArrayList<ArrayDeque<Car>>();
        for (int i = 0; i < 4; i++) {
            this.cars.add(new ArrayDeque<Car>());
        }

        this.crossings = new ArrayDeque<Car>();
        initializeSimulationWindow();
    }

    public void initializeSimulationWindow() {

        JFrame frame = new JFrame("Simulation");
        window = new SimulationWindow();
        frame.add(window);
        frame.setSize(SimConstants.WINDOW_WIDTH, SimConstants.WINDOW_HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void execute() {
        window.repaint();
        this.elapsedTime += SimConstants.SIM_SAMPLE_RATE;
        spawnCarRandomly();
        crossCar();
    }

    //Return the next car in the crossings queue if the crossing time of the last car has passed.
    public Car crossCar() {

        if (this.elapsedTime >= nextCrossTime && crossings.size() > 0) {
            //remove the car from the crossings array and the array of cars
            Car car = crossings.remove();
            int street = car.getStreet().ordinal();
            cars.get(street).remove();

            nextCrossTime = this.elapsedTime + car.getCrossTime();
            System.out.println("CAR CROSSING! (" + elapsedTime + ")");

            return car;
        }
        else
            return null;

    }

    private void spawnCarRandomly() {

        if (this.elapsedTime >= this.nextSpawnTime) {

            Random rand = new Random();

            int streetInt = rand.nextInt(4);
            int directionInt = rand.nextInt(3);

            boolean autonomous = rand.nextDouble() <= SimConstants.AUTONOMOUS_PERCENTAGE;
            Street street = Street.values()[streetInt];
            Direction dir = Direction.values()[directionInt];

            spawnCar(autonomous, street, dir);

            nextSpawnTime = (this.elapsedTime + rand.nextDouble()*(SimConstants.CAR_SPAWN_TIME_MAX - SimConstants.CAR_SPAWN_TIME_MIN)
                    + SimConstants.CAR_SPAWN_TIME_MIN);

        }

    }

    private void spawnCar(boolean autonomous, Street street, Direction dir) {

        Car car = autonomous ? new AutonomousCar(dir, street) : new DrivenCar(dir, street);
        cars.get(street.ordinal()).add(car);

        //THIS LOGIC ADDS A CAR TO THE EVENT QUEUE BASED ON SPAWN TIME. TO BE CHANGED.
        crossings.add(car);
        window.addCar(car);

        System.out.println("CAR SPAWNED! (" + elapsedTime + ") Autonomous = " + autonomous + ", Street = " + street + ", Direction = " + dir);

    }

}
