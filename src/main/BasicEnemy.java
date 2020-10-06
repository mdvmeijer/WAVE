package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BasicEnemy extends Enemy {

    private int timer = 50;
    private BufferedImage sprite;

    public BasicEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id, handler);
        sprite = SpriteManager.getSprite("basic_drone");

        if (random.nextInt(2) == 0) {
            velX = 1;
        } else  {
            velX = -1;
        }
        velY = 0.5f;

        health = 10;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    public void tick() {
        if (health <= 0) { handler.removeObject(this); }

        if (timer <= 0) {
            if (random.nextInt(30) == 0) {
                accY = (random.nextFloat() / 10) - 0.1f;
            }
            velY += (175 - y) / 3000000;
            velY = Game.clamp(velY, -2, 2);

            if (y <= 80) { accY = 0.05f; }
            if (y >= 300) { accY = -0.1f; }
        } else {
            timer--;
        }

        if (x <= 90 || x >= Game.WIDTH - 90) { velX *= -1; }

        velX += accX;
        velY += accY;
        x += velX;
        y += velY;

        if (random.nextInt(60) == 0) {
            handler.addObject(new Bullet((int)(x + 8), (int)(y + 8), 0, 5, ID.EnemyBullet, Color.BLUE, handler));
        }
        //handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.RED, 16, 16, 0.04f, handler));
    }

    public void render(Graphics g) {
        g.drawImage(sprite, (int) x, (int) y, null);
    }

}