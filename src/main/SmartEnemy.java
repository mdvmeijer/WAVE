package main;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class SmartEnemy extends Enemy {

    private BufferedImage sprite;
    private GameObject player;

    private float accTot, radians;

    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);

        sprite = SpriteManager.getSprite("player_ship");

        for (GameObject object : handler.getObjects()) {
            if (object.getId() == ID.Player) { player = object; }
        }

        health = 5;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    public void tick() {
        if (health <= 0) { handler.removeObject(this); }

        x += velX;
        y += velY;

        accTot = 0.05f;

        radians = (float)(Math.atan2(y - player.getY(), x - player.getX()) - 0.5 * Math.PI);
        velX += (float) Math.cos(radians - 0.5 * Math.PI) * accTot;
        velY += (float) Math.sin(radians - 0.5 * Math.PI) * accTot;

        velX = Game.clamp(velX, -3, 3);
        velY = Game.clamp(velY, -3, 3);

        if (random.nextInt(30) == 0) {
            handler.addObject(new Bullet((int)x, (int)y, 8f, 2f, ID.EnemyBullet, Color.BLUE, handler));
        }
        //handler.addObject(new Trail(x, y, ID.Trail, Color.GREEN, 16, 16, 0.04f, handler));
    }

    public void render(Graphics g) {
        AffineTransform reset = new AffineTransform();
        reset.rotate(0,16,16);
        Graphics2D g2 = (Graphics2D) g;
        g2.rotate(radians, x, y);
        g2.drawImage(sprite, (int)x - 8, (int)y - 8,null);
        g2.setTransform(reset);
    }

}