package main;

import java.awt.*;
import java.util.Random;

public class EnemyBoss extends Enemy {

    private Random r = new Random();

    private int timer = 50;
    private int timer2 = 50;

    public EnemyBoss(float x, float y, ID id, Handler handler) {
        super(x, y, id, handler);

        velX = 0;
        velY = 2;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 96, 96);
    }

    public void tick() {
        if (health <= 0) { handler.removeObject(this); }

        x += velX;
        y += velY;

        if (timer <= 0) { velY = 0; }
        else { timer--; }

        if (timer <= 0) { timer2--; }

        if (timer2 <= 0) {

            if (velX == 0) { velX = 2; }

            if (velX > 0) { velX += 0.005f; }
            else if (velX < 0) { velX -= 0.005f; }

            velX = Game.clamp(velX, -10, 10);

            if (random.nextInt(10) == 0) {
                handler.addObject(new Bullet((int)x + 40, (int)y + 48, (float) 7, (float) 3,
                        ID.EnemyBullet, Color.YELLOW, handler));
            }
        }

        //if (y <= 0 || y >= main.Game.HEIGHT - 50) { velY *= -1; }
        if (x <= 0 || x >= Game.WIDTH - 100) { velX *= -1; }

        //handler.addObject(new main.Trail((int)x, (int)y, main.ID.main.Trail, Color.RED, 96, 96, 0.5f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int)x, (int)y, 96, 96);
    }

}