package main;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class Bullet extends GameObject {

    Random random = new Random();
    private Color color;
    //private BufferedImage sprite;
    private MouseInput mouseInput;


    private float mx, my, radians, radiansToMouse;

    public Bullet(int x, int y, float spread, ID id, Color color, Handler handler, MouseInput mouseInput) {
        super(x, y, id, handler);
        this.mouseInput = mouseInput;
        //this.sprite = SpriteManager.getSprite(file);
        this.color = color;

        float sprX = (random.nextFloat() - 0.5f) * spread;
        float sprY = (random.nextFloat() - 0.5f) * spread;

        mx = mouseInput.getMouseX();
        my = mouseInput.getMouseY();
        radiansToMouse = (float)(Math.atan2(y - my, x - mx) - 0.5 * Math.PI);
        velX = (float) Math.cos(radiansToMouse - 0.5 * Math.PI) * 18 + sprX;
        velY = (float) Math.sin(radiansToMouse - 0.5 * Math.PI) * 18 + sprY;
    }

    public Bullet(int x, int y, int velX, int velY, ID id, Color color, Handler handler) {
        super(x, y, id, handler);
        this.color = color;
        this.velX = velX;
        this.velY = velY;
    }

    public Bullet(int x, int y, float velTot, float spread, ID id, Color color, Handler handler) {
        super(x, y, id, handler);
        this.color = color;
        float spr = (random.nextFloat() - 0.5f) * spread;

        for (GameObject object : handler.getObjects()) {
            if (object.getId() == ID.Player) {
                radians = (float)(Math.atan2(y - object.getY(), x - object.getX()) - 0.5 * Math.PI);
                velX = (float) Math.cos(radians - 0.5 * Math.PI) * velTot + spr;
                velY = (float) Math.sin(radians - 0.5 * Math.PI) * velTot + spr;
                radiansToMouse = (float)(Math.atan2(velY, velX) - 0.5 * Math.PI);
            }
        }
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y <= -50 || y >= Game.HEIGHT + 50) { handler.removeObject(this); }
        if (x <= -50 || x >= Game.WIDTH + 50) { handler.removeObject(this); }

        collision();
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 2, 16);
    }

    private void collision() {
        for (int i = 0; i < handler.getObjects().size(); i++) {
            if (id == ID.PlayerBullet) {
                if (handler.getObjects().get(i) instanceof Enemy) {
                    if (getBounds().intersects(handler.getObjects().get(i).getBounds())) {
                        handler.getObjects().get(i).setHealth(handler.getObjects().get(i).getHealth() - 2);
                        handler.removeObject(this);
                    }
                }
            } else if (id == ID.EnemyBullet) {
                if (handler.getObjects().get(i).getId() == ID.Player) {
                    if (getBounds().intersects(handler.getObjects().get(i).getBounds())) {
                        HUD.HEALTH -= 10;
                        handler.removeObject(this);
                    }
                }
            }
        }
    }

    public void render(Graphics g) {
        AffineTransform reset = new AffineTransform();
        reset.rotate(0,0,0);
        Graphics2D g2 = (Graphics2D) g;
        g2.rotate(radiansToMouse, x, y);
        g2.setColor(color);
        g2.fillRect((int) x - 1, (int) y - 8, 2, 16);
        g2.setTransform(reset);
    }

}