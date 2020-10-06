package main;

import java.awt.*;

public class HUD {

    public static float HEALTH = 100;
    private float greenValue;

    private int score = 0;
    private int level = 0;

    public void tick() {
        HEALTH = Game.clamp(HEALTH, 0, 100);
        greenValue = HEALTH * 2;
        greenValue = Game.clamp(greenValue, 0, 255);

        score++;
    }

    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(15, 15, 200, 32);
        g.setColor(new Color(100, (int)greenValue, 0));
        g.fillRect(15, 15, (int)HEALTH * 2, 32);
        g.setColor(Color.WHITE);
        g.drawRect(15, 15, 200, 32);

        g.drawString("Score: " + score, 10, 64);
        g.drawString("Level: " + level, 10, 80);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}