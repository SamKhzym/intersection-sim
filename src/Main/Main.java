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
        KeyHandler keyHandler = new KeyHandler(graphics);
        Timer time = new Timer();

        while (true) {
            time.start();
            if (!keyHandler.isPaused()) {

                //Update intersection and actor data
                ArrayList<Actor> actors = intersection.execute();

                //Draw them
                graphics.drawActors(actors);
            }

            long currentTime = time.getTime();
            if (currentTime < 1000/SimConstants.FPS) {
                Thread.sleep(1000/SimConstants.FPS - currentTime);
            }
        }
    }
}
