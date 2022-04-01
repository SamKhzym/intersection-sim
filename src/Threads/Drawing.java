package Threads;

import Graphics.GraphicsHandler;
import Interfaces.Subscriber;
import Stuff.Timer;
import Utils.SimConstants;

public class Drawing extends Thread implements Subscriber {
    private boolean paused;
    private Physics physics;
    private GraphicsHandler graphics;

    public Drawing(Physics physics) {
        paused = false;
        this.physics = physics;
        this.graphics = new GraphicsHandler();
    }

    public GraphicsHandler getGraphics() {
        return graphics;
    }

    public void update() {
        paused = !paused;
        graphics.setPaused(paused);
    }

    public void run() {
        Timer time = new Timer();

        while (true) {
            time.start();

            if (!paused) {
                graphics.drawActors(physics.getActors());
            }

            long currentTime = time.getTime();
            if (currentTime < 1000.0 / SimConstants.DRAW_TIME) {
                try {
                    Thread.sleep((long)(1000.0/SimConstants.DRAW_TIME) - currentTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
