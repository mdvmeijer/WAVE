package background;

import java.awt.*;

class Star extends GalaxyObject {

    private int size;

    Star(int maxX, int maxY) {
        super(maxX, maxY);

        size = random.nextInt(5) + 1;
    }

    void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, size, size);
    }

}