package main;

import java.awt.*;
import java.util.Random;

public class EnemyBossBullet extends GameObject {

    Random r = new Random();

    public EnemyBossBullet(float x, float y, ID id, Handler handler) {
        super(x, y, id, handler);

        velX = r.nextInt(10) - 5;
        velY = 4;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        //if (y <= 0 || y >= main.Game.HEIGHT - 50) { velY *= -1; }
        //if (x <= 0 || x >= main.Game.WIDTH - 22) { velX *= -1; }

        if (y >= Game.HEIGHT) { handler.removeObject(this); }

        handler.addObject(new main.Trail((int)x, (int)y, ID.Trail, Color.YELLOW, 16, 16, 0.04f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect((int)x, (int)y, 16, 16);
    }

}