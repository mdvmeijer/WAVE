package background;

import java.awt.*;

class Supernova extends GalaxyObject {

    private float size;

    Supernova(int maxX, int maxY) {
        super(maxX, maxY);

        size = random.nextInt(10) + 45;
    }

    void draw(Graphics g) {
        g.setColor(color);
        for (int i = 0; i < 50; i++) {
            int locX = random.nextInt((int)(size)) - (int)(0.5 * size);
            int locY = random.nextInt((int)(size)) - (int)(0.5 * size);
            if (locX + locY > 0.5 * size || locX + locY < -0.5 * size) {
                locX = 0;
                locY = 0;
            }
            g.drawLine(x + locX, y + locY, x + locX, y + locY);
        }
    }

}