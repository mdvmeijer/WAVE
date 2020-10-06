package main;

import java.awt.*;
import java.util.Random;

public class MenuParticle extends GameObject {

    private Random r = new Random();
    private Color color;

    public MenuParticle(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);

            velX = r.nextInt(12) - 6;
            if (velX >= 0) {
                velX = Game.clamp(velX, 2, 6);
            } else if (velX < 0) {
                velX = Game.clamp(velX, -6, -2);
            }

            velY = r.nextInt(12) - 6;
            if (velY >= 0) {
                velY = Game.clamp(velY, 2, 6);
            } else if (velX < 0) {
                velY = Game.clamp(velY, -6, -2);
            }

        color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 50) { velY *= -1; }
        if (x <= 0 || x >= Game.WIDTH - 22) { velX *= -1; }

        handler.addObject(new Trail((int)x, (int)y, ID.Trail, color, 16, 16, 0.07f, handler));
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int)x, (int)y, 16, 16);
    }

}