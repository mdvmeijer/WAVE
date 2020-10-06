package background;

import java.awt.*;

class Planet3 extends GalaxyObject{

    private float size;

    Planet3(int maxX, int maxY) {
        super(maxX, maxY);

        size = random.nextInt(30) + 35;
    }

    void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, (int)size, (int)size);
    }

}