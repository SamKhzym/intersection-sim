package Main;

import Graphics.KeyHandler;
import Stuff.Publisher;
import Threads.Drawing;
import Threads.Physics;


public class Main {

    public static void main(String[] args) {
        Physics physics = new Physics();
        Drawing drawing = new Drawing(physics);

        Publisher publisher = new Publisher();
        publisher.addSubscriber(physics);
        publisher.addSubscriber(drawing);

        new KeyHandler(drawing.getGraphics(), publisher);

        Thread drawingThread = new Thread(drawing);
        Thread physicsThread = new Thread(physics);

        drawingThread.start();
        physicsThread.start();
    }
}
