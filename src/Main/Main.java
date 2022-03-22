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

        Timer drawTime = new Timer();
        Timer physicsTime = new Timer();
        Timer simulationLoop = new Timer();

        boolean goDraw = true;
        boolean goUpdate = true;

        ArrayList<Actor> actors = new ArrayList<>();

        int frames = 0;
        Timer fps = new Timer();
        fps.start();

        int loopTime = (SimConstants.PHYSICS_TIME < SimConstants.DRAW_TIME) ? SimConstants.DRAW_TIME : SimConstants.PHYSICS_TIME;

        while (true) {
            simulationLoop.start();
            if (!keyHandler.isPaused()) {

                if (goUpdate) {

                    //Update intersection and actor data
                    actors = intersection.execute();
                    goUpdate = false;
                }

                if (goDraw) {
                    frames++;
                    //Draw them
                    graphics.drawActors(actors);
                    goDraw = false;
                }
            }

            long currentPhysicsTime = physicsTime.getTime();
            if (currentPhysicsTime > 1000/SimConstants.PHYSICS_TIME) {
                physicsTime.start();
                goUpdate = true;
            }

            long currentDrawTime = drawTime.getTime();
            if (currentDrawTime > 1000/SimConstants.DRAW_TIME) {
                drawTime.start();
                goDraw = true;
            }

            long currentTime = simulationLoop.getTime();
            if (currentTime < 1000/loopTime) {
                Thread.sleep(1000/loopTime - currentTime);
            }


            System.out.println(frames / (fps.getTime()/1000.f));
        }
    }
}
