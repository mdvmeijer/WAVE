package background;

import java.awt.*;

class backgroundPixel extends GalaxyObject {

    backgroundPixel(int maxX, int maxY) {
        super(maxX, maxY);

        color = new Color(216, 46, 219);
    }

    void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(x, y, x, y);
    }

}