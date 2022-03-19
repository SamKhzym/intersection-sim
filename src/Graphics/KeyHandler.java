package Graphics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private GraphicsHandler graphics;
    private boolean isPressed;
    private boolean paused;

    public KeyHandler(GraphicsHandler graphics) {
        this.graphics = graphics;
        isPressed = false;
        paused = false;

        graphics.getFrame().addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!isPressed && e.getKeyCode() == 27) {
            paused = !paused;
            graphics.drawPause(paused);
            isPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 27) {
            isPressed = false;
        }
    }

    public boolean isPaused() {
        return paused;
    }
}
