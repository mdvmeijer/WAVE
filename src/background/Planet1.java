package background;

import java.awt.*;

class Planet1 extends GalaxyObject {

    private float size;

    Planet1(int maxX, int maxY) {
        super(maxX, maxY);

        size = random.nextInt(10) + 25;
    }

    void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, (int)size, (int)size);
        g.setColor(Color.WHITE);
        g.drawOval((int)(x - 0.24 * size), (int)(y + 0.4 * size), (int)(size + 0.52 * size), (int)(0.35 * size));
    }

}