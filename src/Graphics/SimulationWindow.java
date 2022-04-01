package Graphics;

import Actors.Actor;
import Utils.SimConstants;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.*;

public class SimulationWindow extends JPanel {

    private Graphics2D g2d;
    private ArrayList<Actor> actors;
    private boolean paused;

    public SimulationWindow() {
        actors = new ArrayList<>();
        paused = false;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(0,0,0));

        drawRoad(g2d);
        drawCars(g2d);

        if (paused) {
            g2d.drawString("PAUSED",0,10);
        }
    }

    public void drawRoad(Graphics2D g2d) {
        for (int i = 0; i < 3; i++) {
            Line2D lin = new Line2D.Float(
                    (float) (getWidth() / 2 - SimConstants.LANE_WIDTH * (i - 1) * SimConstants.GRAPHICS_SCALING_FACTOR),
                    0,
                    (float) (getWidth() / 2 - SimConstants.LANE_WIDTH * (i - 1) * SimConstants.GRAPHICS_SCALING_FACTOR),
                    getHeight()
            );
            g2d.draw(lin);
        }

        for (int i = 0; i < 3; i++) {
            Line2D lin = new Line2D.Float(
                    0,
                    (float) (getHeight() / 2 - SimConstants.LANE_WIDTH * (i - 1) * SimConstants.GRAPHICS_SCALING_FACTOR),
                    getWidth(),
                    (float) (getHeight() / 2 - SimConstants.LANE_WIDTH * (i - 1) * SimConstants.GRAPHICS_SCALING_FACTOR)
            );
            g2d.draw(lin);
        }
    }

    public void drawCars(Graphics g2d) {
        for (Actor actor: actors) {
            int posX = (int) ((actor.getPosition()[0] - SimConstants.CAR_WIDTH / 2) * SimConstants.GRAPHICS_SCALING_FACTOR) + getWidth() / 2;
            int posY = -1 * (int) ((actor.getPosition()[1] + SimConstants.CAR_HEIGHT / 2) * SimConstants.GRAPHICS_SCALING_FACTOR) + getHeight() / 2;
            int carWidth = (int) (SimConstants.CAR_WIDTH * SimConstants.GRAPHICS_SCALING_FACTOR);
            int carHeight = (int) (SimConstants.GRAPHICS_SCALING_FACTOR * SimConstants.CAR_HEIGHT);
            //change to include scaling factor
            g2d.fillOval(posX, posY, carWidth, carHeight);
        }
    }

    public void drawActors(ArrayList<Actor> actors) {
        this.actors = actors;
        this.repaint();
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
