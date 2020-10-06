package main;

import java.awt.*;

public class FastEnemy extends Enemy {

    public FastEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);

        velX = 3;
        velY = 9;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    public void tick() {
        if (health <= 0) { handler.removeObject(this); }

        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 50) { velY *= -1; }
        if (x <= 0 || x >= Game.WIDTH - 22) { velX *= -1; }

        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.ORANGE, 16, 16, 0.04f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect((int)x, (int)y, 16, 16);
    }

}