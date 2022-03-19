package Graphics;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler {
    private boolean isPressed = false;
    private boolean paused = false;

    public void keyChecker(JFrame frame) {
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (!isPressed && e.getKeyCode() == 27) {
                    paused = !paused;
                    isPressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 27) {
                    isPressed = false;
                }
            }
        });
    }

    public boolean isPaused() {
        return paused;
    }
}
