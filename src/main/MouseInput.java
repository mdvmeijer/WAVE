package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MouseInput extends MouseAdapter {
    private Handler handler;
    private HUD hud;
    private Spawner spawner;
    private Menu menu;
    private Game game;
    private ArrayList<GameObject> objects;
    private boolean isDown = false;
    private long oldTime, nowTime;

    public MouseInput(Handler handler, HUD hud, Spawner spawner, Menu menu, Game game) {
        this.handler = handler;
        this.hud = hud;
        this.spawner = spawner;
        this.menu = menu;
        this.game = game;
        objects = handler.getObjects();

        oldTime = System.currentTimeMillis();
    }

    public void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (Game.gameState == STATE.Menu) {
            if (mouseOver(mx, my, 290, 180, 200, 64)) { //play button
                //main.AudioPlayer.getSound("click_sound").play();
                Game.gameState = STATE.Select;
            }

            if (mouseOver(mx, my, 290, 290, 200, 64)) { //help button
                //main.AudioPlayer.getSound("click_sound").play();
                Game.gameState = STATE.Stats;
            }

            if (mouseOver(mx, my, 290, 400, 200, 64)) { //quit button
                //main.AudioPlayer.getSound("click_sound").play();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                System.exit(0);
            }
        } else if (Game.gameState == STATE.Stats) { //back button
            if (mouseOver(mx, my, 290, 400, 200, 64)) {
                //main.AudioPlayer.getSound("click_sound").play();
                Game.gameState = STATE.Menu;
            }
        } else if (Game.gameState == STATE.End) { //try again button
            if (mouseOver(mx, my, 275, 400, 230, 64)) {
                //main.AudioPlayer.getSound("click_sound").play();

                Game.gameState = STATE.Menu;
                hud.setLevel(0);
                hud.setScore(0);
                spawner.setScoreKeep(0);

                try {
                    Thread.sleep(350);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        } else if (Game.gameState == STATE.Select) {
            if (mouseOver(mx, my, 290, 180, 200, 64)) { //normal button
                //main.AudioPlayer.getSound("click_sound").play();

                Game.gameState = STATE.Game;
                handler.clearEnemies();

                Game.diff = 0;

                try {
                    Thread.sleep(350);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                handler.addObject(new Player((Game.WIDTH / 2) - 16, (Game.HEIGHT / 2) - 32, ID.Player,
                        handler, this));
            }

            if (mouseOver(mx, my, 290, 290, 200, 64)) { //hard button
                //main.AudioPlayer.getSound("click_sound").play();

                Game.gameState = STATE.Game;
                handler.clearEnemies();

                Game.diff = 1;

                try {
                    Thread.sleep(350);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                handler.addObject(new Player((Game.WIDTH / 2) - 16, (Game.HEIGHT / 2) - 32, ID.Player,
                        handler, this));
            }

            if (mouseOver(mx, my, 290, 400, 200, 64)) { //back button
                //main.AudioPlayer.getSound("click_sound").play();
                Game.gameState = STATE.Menu;
            }

        }
    }

    public void mousePressed(MouseEvent e) {
        if (Game.gameState == STATE.Game && !e.isAltDown() && !e.isMetaDown()) {
            isDown = true;
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (Game.gameState == STATE.Game && !e.isAltDown() && !e.isMetaDown()) {
            isDown = false;
        }
    }

    public void tick() {
        int mx = getMouseX();
        int my = getMouseY();

        if (Game.gameState == STATE.Menu) {
            if (mouseOver(mx, my, 290, 180, 200, 64)) { //play button
                menu.isOn1 = true;
            } else {
                menu.isOn1 = false;
            }

            if (mouseOver(mx, my, 290, 290, 200, 64)) { //help button
                menu.isOn2 = true;
            } else {
                menu.isOn2 = false;
            }

            if (mouseOver(mx, my, 290, 400, 200, 64)) { //quit button
                menu.isOn3 = true;
            } else {
                menu.isOn3 = false;
            }
        } else if (Game.gameState == STATE.Stats) { //back button
            if (mouseOver(mx, my, 290, 400, 200, 64)) {
                menu.isOn3 = true;
            } else {
                menu.isOn3 = false;
            }
        } else if (Game.gameState == STATE.End) { //try again button
            if (mouseOver(mx, my, 275, 400, 230, 64)) {
                menu.isOn3 = true;
            } else {
                menu.isOn3 = false;
            }
        } else if (Game.gameState == STATE.Select) {
            if (mouseOver(mx, my, 290, 180, 200, 64)) { //normal button
                menu.isOn1 = true;
            } else {
                menu.isOn1 = false;
            }

            if (mouseOver(mx, my, 290, 290, 200, 64)) { //hard button
                menu.isOn2 = true;
            } else {
                menu.isOn2 = false;
            }

            if (mouseOver(mx, my, 290, 400, 200, 64)) { //back button
                menu.isOn3 = true;
            } else {
                menu.isOn3 = false;
            }
        } else if (Game.gameState == STATE.Game) {
            if (isDown) {
                nowTime = System.currentTimeMillis();
                if (nowTime - oldTime > 300) {
                    oldTime = nowTime;
                    for (int i = 0; i < objects.size(); i++) {
                        if (objects.get(i).getId() == ID.Player) {
                            handler.addObject(new Bullet((int) objects.get(i).getX(), (int) objects.get(i).getY(), 1.2f,
                                    ID.PlayerBullet, Color.RED, handler, this));
                        }
                        break;
                    }
                }
            }
        }
    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width && my > y && my < y + height) {
            return true;
        } else {
            return false;
        }
    }

    public int getMouseX() {
        Point point = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(point, game);
        return (int) point.getX();
    }

    public int getMouseY() {
        Point point = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(point, game);
        return (int) point.getY();
    }

}