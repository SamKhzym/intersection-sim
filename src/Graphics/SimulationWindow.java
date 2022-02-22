package Graphics;

import Actors.Actor;
import Actors.Car;
import Utils.SimConstants;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import javax.swing.JPanel;

public class SimulationWindow extends JPanel {

    private Graphics2D g2d;
    private ArrayList<Actor> actors;

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(1,50,1));

        for (Actor actor: actors) {
            int posX = (int)(actor.getPosition()[0] * SimConstants.GRAPHICS_SCALING_FACTOR);
            int posY = (int)(actor.getPosition()[1] * SimConstants.GRAPHICS_SCALING_FACTOR);
            //change to include scaling factor
            g2d.fillOval(posX, posY, SimConstants.CAR_WIDTH, SimConstants.CAR_HEIGHT);
        }

    }

    public void drawActors(ArrayList<Actor> actors) {
        this.actors = actors;
        this.repaint();
    }

}
