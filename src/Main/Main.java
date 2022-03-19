package Main;

import Actors.Actor;
import Graphics.GraphicsHandler;
import Graphics.KeyHandler;
import Graphics.Timer;
import Intersection.Intersection;
import Utils.SimConstants;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Intersection intersection = new Intersection();
        GraphicsHandler graphics = new GraphicsHandler();
        KeyHandler keyHandler = new KeyHandler();
        keyHandler.keyChecker(graphics.getFrame());
        Timer time = new Timer();

        while (true) {
            System.out.println(keyHandler.isPaused());
            if (!keyHandler.isPaused()) {
                time.start();

                //Update intersection and actor data
                ArrayList<Actor> actors = intersection.execute();

                //Draw them
                graphics.drawActors(actors);

                if (time.getTime() < 1000/SimConstants.FPS) {
                    Thread.sleep(1000/SimConstants.FPS - time.getTime());
                }
            }
        }
    }
}
