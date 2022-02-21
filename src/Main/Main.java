package Main;

import Intersection.Intersection;
import Utils.SimConstants;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Intersection intersection = new Intersection();

        while (true) {

            intersection.execute();
            Thread.currentThread().sleep((long)(SimConstants.SIM_SAMPLE_RATE*1000));

        }

    }

}
