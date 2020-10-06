package main;

import java.awt.*;

public class Menu {
    private Handler handler;
    private HUD hud;
    private Spawner spawner;
    private Game game;

    private Font titleFont = new Font("Space Age", Font.PLAIN, 120);
    private Font selectDiffFont = new Font("Space Age", Font.PLAIN, 55);
    private Font gameOverFont = new Font("Space Age", Font.PLAIN, 100);
    private Font buttonFont = new Font("Robotica", 1, 30);
    private Font onButtonFont = new Font("Robotica", 1, 35);
    private Font statsFont = new Font("Sofachrome Rg", Font.PLAIN, 12);

    private Color statsColor = new Color(255, 255, 255);
    private Color textBoxColor = new Color(255, 255, 255, 50);

    boolean isOn1 = false, isOn2 = false, isOn3 = false;

    public Menu(Handler handler, HUD hud, Spawner spawner, Game game) {
        this.handler = handler;
        this.hud = hud;
        this.spawner = spawner;
        this.game = game;
    }

    public void tick() {

    }

    public void render(Graphics g) {

        if (Game.gameState == STATE.Menu) {
            g.setFont(titleFont);
            g.setColor(Color.WHITE);
            g.drawString("Wave", 177, 120);

            g.setFont(statsFont);
            g.drawString("High score: " + SaveManager.loadScore(), 10, 500);

            g.setColor(Color.GRAY);
            g.fillRect(290, 180, 200, 64);
            g.setColor(Color.WHITE);
            g.drawRect(290, 180, 200, 64);

            if (isOn1) {
                g.setFont(onButtonFont);
                g.drawString("Play", 337, 222);
            } else {
                g.setFont(buttonFont);
                g.drawString("Play", 345, 220);
            }

            g.setColor(Color.GRAY);
            g.fillRect(290, 290, 200, 64);
            g.setColor(Color.WHITE);
            g.drawRect(290, 290, 200, 64);

            if (isOn2) {
                g.setFont(onButtonFont);
                g.drawString("Stats", 324, 332);
            } else {
                g.setFont(buttonFont);
                g.drawString("Stats", 333, 330);
            }

            g.setColor(Color.GRAY);
            g.fillRect(290, 400, 200, 64);
            g.setColor(Color.WHITE);
            g.drawRect(290, 400, 200, 64);

            if (isOn3) {
                g.setFont(onButtonFont);
                g.drawString("Quit", 338, 442);
            } else {
                g.setFont(buttonFont);
                g.drawString("Quit", 345, 440);
            }
        } else if (Game.gameState == STATE.Stats) {
            g.setFont(titleFont);
            g.setColor(Color.white);
            g.drawString("Stats", 128, 120);

            g.setColor(textBoxColor);
            g.fillRoundRect(130, 150, 526, 220, 50, 50);

            g.setFont(statsFont);
            g.setColor(statsColor);
            g.drawString("High score (easy): ", 150, 175);
            g.drawString(String.valueOf(SaveManager.loadScore()), 540, 175);
            g.drawString("High score (normal): ", 150, 175 + g.getFontMetrics().getHeight());
            g.drawString(String.valueOf(SaveManager.loadScore()), 540, 175 + g.getFontMetrics().getHeight());
            g.drawString("High score (hard): ", 150, 175 + 2 * (g.getFontMetrics().getHeight()));
            g.drawString(String.valueOf(SaveManager.loadScore()), 540, 175 + 2 * (g.getFontMetrics().getHeight()));

            g.setColor(Color.GRAY);
            g.fillRect(290, 400, 200, 64);
            g.setColor(Color.WHITE);
            g.drawRect(290, 400, 200, 64);

            if (isOn3) {
                g.setFont(onButtonFont);
                g.drawString("Back", 335, 442);
            } else {
                g.setFont(buttonFont);
                g.drawString("Back", 342, 440);
            }
        } else if (Game.gameState == STATE.End) {
            g.setFont(gameOverFont);
            g.setColor(Color.WHITE);
            g.drawString("Game over", 19, 120);

            g.setFont(buttonFont);
            g.drawString("You lost with a score of " + hud.getScore(), 100, 230);

            g.setColor(Color.GRAY);
            g.fillRect(275, 400, 230, 64);
            g.setColor(Color.WHITE);
            g.drawRect(275, 400, 230, 64);

            if (isOn3) {
                g.setFont(onButtonFont);
                g.drawString("Try again", 279, 442);
            } else {
                g.setFont(buttonFont);
                g.drawString("Try again", 295, 440);
            }
        } else if (Game.gameState == STATE.Select) {
            g.setFont(selectDiffFont);
            g.setColor(Color.WHITE);
            g.drawString("SELECT DIFFICULTY", 26, 100);

            g.setFont(buttonFont);
            g.setColor(Color.GRAY);
            g.fillRect(290, 180, 200, 64);
            g.setColor(Color.WHITE);
            g.drawRect(290, 180, 200, 64);

            if (isOn1) {
                g.setFont(onButtonFont);
                g.drawString("Normal", 311, 223);
            } else {
                g.setFont(buttonFont);
                g.drawString("Normal", 322, 220);
            }

            g.setColor(Color.GRAY);
            g.fillRect(290, 290, 200, 64);
            g.setColor(Color.WHITE);
            g.drawRect(290, 290, 200, 64);

            if (isOn2) {
                g.setFont(onButtonFont);
                g.drawString("Hard", 332, 333);
            } else {
                g.setFont(buttonFont);
                g.drawString("Hard", 340, 330);
            }

            g.setColor(Color.GRAY);
            g.fillRect(290, 400, 200, 64);
            g.setColor(Color.WHITE);
            g.drawRect(290, 400, 200, 64);

            if (isOn3) {
                g.setFont(onButtonFont);
                g.drawString("Back", 333, 443);
            } else {
                g.setFont(buttonFont);
                g.drawString("Back", 340, 440);

            }
        }
    }

}