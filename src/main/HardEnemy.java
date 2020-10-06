package main;

import java.awt.*;
import java.util.Random;

public class HardEnemy extends Enemy {

    private Random r = new Random();

    public HardEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id, handler);

        velX = 4;
        velY = 4;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    public void tick() {
        if (health <= 0) { handler.removeObject(this); }

        x += velX;
        y += velY;

        if (y <= 0 && velY < 0) {
            velX = r.nextInt(16) - 8;
            if (velX == 0) { velX = 1; }
            velY = r.nextInt(8) + 3;
        } else if (y >= Game.HEIGHT - 50 && velY > 0) {
            velX = r.nextInt(16) - 8;
            if (velX == 0) { velX = 1; }
            velY = -(r.nextInt(8) + 3);
        } else if (x <= 0 && velX < 0) {
            velX = r.nextInt(8) + 3;
            velY = r.nextInt(16) - 8;
            if (velY == 0) { velY = 1; }
        } else if (x >= Game.WIDTH - 22 && velX > 0) {
            velX = -(r.nextInt(8) + 3);
            velY = r.nextInt(16) - 8;
            if (velY == 0) { velY = 1; }
        }

        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.CYAN, 16, 16, 0.04f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect((int)x, (int)y, 16, 16);
    }

}