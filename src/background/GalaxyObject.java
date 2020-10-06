package background;

import java.awt.*;
import java.util.Random;
abstract class GalaxyObject {

    Random random = BackgroundGenerator.random;

    protected int x, y;

    protected Color color;

    private int maxX, maxY;

    GalaxyObject(int maxX, int maxY) {
        this.maxX = maxX; this.maxY = maxY;
        x = random.nextInt(maxX);
        y = random.nextInt(maxY);
        color = getRandomColour();
    }

    private Color getRandomColour() {
        int r = random.nextInt(200) + 56;
        int g = random.nextInt(200) + 56;
        int b = random.nextInt(200) + 56;
        return new Color(r, g, b);
    }

    abstract void draw(Graphics g);

}