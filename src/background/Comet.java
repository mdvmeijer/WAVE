package background;

import java.awt.*;
import java.awt.geom.AffineTransform;

class Comet extends GalaxyObject {
    private long size, RGB;
    private int red, green, blue, alpha;
    private double angle;

    Comet(int maxX, int maxY) {
        super(maxX, maxY);

        size = random.nextInt(6) + 5;
        angle = (double)(random.nextInt(42)) / 6.0;
    }

    void draw(Graphics g) {
        RGB = color.getRGB();
        red = (int) (RGB >> 16 & 0xFF);
        green = (int) (RGB >> 8 & 0xFF);
        blue = (int) (RGB & 0xFF);
        alpha = 100;
        color = new Color(red, green, blue, alpha);

        AffineTransform reset = new AffineTransform();
        reset.rotate(0, 0, 0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(angle, x, y);
        g2d.setColor(color);
        g2d.fillOval(x, y, (int)size, (int)size);
        alpha = 100;
        color = new Color(red, green, blue, alpha);
        g2d.drawLine((int)(x + 0.5 * size), y, (int)(x + 3 * size), (int)(y - 1.1 * size));
        g2d.drawLine((int)(x + 0.6 * size), (int)(y + 0.1 * size), (int)(x + 3.6 * size), (int)(y - 1.2 * size));
        g2d.drawLine((int)(x + 0.7 * size), (int)(y + 0.2 * size), (int)(x + 4 * size), (int)(y - 1.3 * size));
        g2d.drawLine((int)(x + 0.8 * size), (int)(y + 0.3 * size), (int)(x + 4.6 * size), (int)(y - 1.4 * size));
        g2d.drawLine((int)(x + 0.9 * size), (int)(y + 0.4 * size), (int)(x + 5 * size), (int)(y - 1.5 * size));
        g2d.drawLine((int)(x + 0.9 * size), (int)(y + 0.5 * size), (int)(x + 5.6 * size), (int)(y - 1.6 * size));
        g2d.drawLine((int)(x + 0.9 * size), (int)(y + 0.6 * size), (int)(x + 5 * size), (int)(y - 1.2 * size));
        g2d.drawLine((int)(x + 0.9 * size), (int)(y + 0.7 * size), (int)(x + 4.6 * size), (int)(y - 0.8 * size));
        g2d.drawLine((int)(x + 0.9 * size), (int)(y + 0.8 * size), (int)(x + 4 * size), (int)(y - 0.5 * size));
        g2d.drawLine((int)(x + 0.5 * size), (int)(y + 1 * size), (int)(x + 3.6 * size), (int)(y - 0.2 * size));
        g2d.setTransform(reset);
    }

}