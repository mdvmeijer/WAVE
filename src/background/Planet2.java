package background;

import java.awt.*;

class Planet2 extends GalaxyObject {

    private float size;

    Planet2(int maxX, int maxY) {
        super(maxX, maxY);

        size = random.nextInt(25) + 20;
    }

    void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, (int)size, (int)size);
        g.setColor(Color.WHITE);
        g.drawOval((int)(x - 0.37 * size), (int)(y + 0.38 * size), (int)(size + 0.8 * size), (int)(0.2 * size));
        g.drawOval((int)(x - 0.37 * size), (int)(y + 0.47 * size), (int)(size + 0.8 * size), (int)(0.2 * size));
    }

}