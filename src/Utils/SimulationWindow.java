package Utils;

import Actors.Car;
import Utils.SimConstants;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import javax.swing.JPanel;

public class SimulationWindow extends JPanel {

    private Graphics2D g2d;
    ArrayList<Car> cars = new ArrayList<>();

    @Override
    public void paint(Graphics g) {
        // Window
        super.paint(g);
        g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(0,0,0));

        drawRoad(g2d);

        g2d.setColor(new Color(72, 94, 72));

        for (int i = 0; i != cars.size(); ++i) {
            //cars.get(i).updatePosition();
            g2d.fillOval(cars.get(i).getPositionX(), cars.get(i).getPositionY(), SimConstants.CAR_WIDTH, SimConstants.CAR_HEIGHT);
        }
    }

    public void drawRoad(Graphics2D g2d) {
        int intersectionOffset = (SimConstants.CAR_WIDTH + SimConstants.CAR_HEIGHT);

        g2d.drawLine(0, getSize().height/2 - intersectionOffset,
                getSize().width, getSize().height/2 - intersectionOffset);

        g2d.drawLine(0, getSize().height/2 + intersectionOffset,
                getSize().width, getSize().height/2 + intersectionOffset);

        g2d.drawLine(getSize().width/2 - intersectionOffset, 0,
                getSize().width/2 - intersectionOffset, getSize().height);

        g2d.drawLine(getSize().width/2 + intersectionOffset, 0,
                getSize().width/2 + intersectionOffset, getSize().height);

        g2d.setColor(new Color(102, 102, 102));
        g2d.drawLine(0, getSize().height/2, getSize().width, getSize().height/2);
        g2d.drawLine(getSize().width/2, 0, getSize().width/2, getSize().height);
    }

    public void addCar(Car car) {
        cars.add(car);
        int intersectionOffset = (SimConstants.CAR_WIDTH + SimConstants.CAR_HEIGHT);

        switch (car.getStreet().ordinal()) {
            case 0:
                car.setPosition(getWidth()/2 - intersectionOffset/2 - (SimConstants.CAR_WIDTH / 2),
                        getHeight()/2 - 3*intersectionOffset/2 - (SimConstants.CAR_HEIGHT / 2));
                break;
            case 1:
                car.setPosition(getWidth()/2 + 3*intersectionOffset/2 - (SimConstants.CAR_WIDTH / 2),
                        getHeight()/2 - intersectionOffset/2 - (SimConstants.CAR_WIDTH / 2));
                break;
            case 2:
                car.setPosition(getWidth()/2 + intersectionOffset/2 - (SimConstants.CAR_WIDTH / 2),
                    getHeight()/2 + 3*intersectionOffset/2 - (SimConstants.CAR_WIDTH / 2));
                break;
            case 3:
                car.setPosition(getWidth()/2 - 3*intersectionOffset/2 - (SimConstants.CAR_WIDTH / 2),
                    getHeight()/2 + intersectionOffset/2 - (SimConstants.CAR_WIDTH / 2));
                break;
        }
    }
}
