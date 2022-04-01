package Graphics;

import Stuff.Publisher;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private GraphicsHandler graphics;
    private Publisher publisher;
    private boolean isPressed;

    private final int ESCAPE_KEY = 27;

    public KeyHandler(GraphicsHandler graphics, Publisher publisher) {
        this.graphics = graphics;
        isPressed = false;

        graphics.getFrame().addKeyListener(this);
        this.publisher = publisher;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!isPressed && e.getKeyCode() == ESCAPE_KEY) {
            publisher.notifySubscribers();
            isPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == ESCAPE_KEY) {
            isPressed = false;
        }
    }
}
