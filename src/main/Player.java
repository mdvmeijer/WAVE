package main;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends GameObject {

    private BufferedImage sprite;
    private MouseInput mouseInput;
    private boolean xCheck, yCheck;
    //private float velRatio;

    public Player(int x, int y, ID id, Handler handler, MouseInput mouseInput) {
        super(x, y, id, handler);
        sprite = SpriteManager.getSprite("player_ship");
        this.mouseInput = mouseInput;
        xCheck = true;
        yCheck = true;
        adjX = false;
        adjY = false;
    }

    public void tick() {
        x += velX;
        y += velY;
        velX += accX;
        velY += accY;

        //velRatio = velX / velY;

        if (adjX) {
            if (velX >= -0.1f && velX <= 0.1f) {
                velX = 0;
                accX = 0;
                adjX = false;
            }
        }

        if (adjY) {
            if (velY >= -0.1f && velY <= 0.1f) {
                velY = 0;
                accY = 0;
                adjY = false;
            }
        }

        x = Game.clamp(x, 17, Game.WIDTH - 23);
        y = Game.clamp(y, 17, Game.HEIGHT - 52);

        velX = Game.clamp(velX, -3, 3);
        velY = Game.clamp(velY, -3, 3);

        collision();

        /*velTot = (float) Math.sqrt(velX * velX + velY + velY);

        if (velTot > 5 || velTot < -5) {
            velTot = Game.clamp(velTot, -5, 5);
            velX = (float) Math.cos(Math.toRadians(45)) * velTot;
            velY = (float) Math.sin(Math.toRadians(45)) * velTot;
        }*/
        //handler.addObject(new main.Trail(x, y, main.ID.main.Trail, Color.WHITE, 32, 32, 0.05f, handler));
    }

    private void collision() {
        for (GameObject object : handler.getObjects()) {
            if (object.getId() == ID.BasicEnemy || object.getId() == ID.FastEnemy || object.getId() == ID.SmartEnemy
                    || object.getId() == ID.EnemyBoss) {
                if (getBounds().intersects(object.getBounds())) {
                    HUD.HEALTH -= 2;
                }
            }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x - 16, (int)y - 16, 32, 32);
    }

    public void render(Graphics g) {
        float mx = mouseInput.getMouseX();
        float my = mouseInput.getMouseY();

        float radiansToMouse = (float)(Math.atan2(y - my, x - mx) - 0.5 * Math.PI);

        AffineTransform reset = new AffineTransform();
        reset.rotate(0,0,0);
        Graphics2D g2 = (Graphics2D) g;
        g2.rotate(radiansToMouse, x, y);
        g2.drawImage(sprite, (int)x - 16, (int)y - 16,null);
        g2.setTransform(reset);
    }

}