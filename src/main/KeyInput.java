package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private ArrayList<GameObject> objects;
    private MouseInput mouseInput;
    private boolean spacePressed;

    public KeyInput(Handler handler, MouseInput mouseInput) {
        this.handler = handler;
        this.mouseInput = mouseInput;
        objects = handler.getObjects();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getId() == ID.Player) {

                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                    objects.get(i).setAccY(-0.2f);
                    objects.get(i).adjustY(false);
                }
                if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
                    objects.get(i).setAccY(0.2f);
                    objects.get(i).adjustY(false);
                }
                if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                    objects.get(i).setAccX(-0.2f);
                    objects.get(i).adjustX(false);
                }
                if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                    objects.get(i).setAccX(0.2f);
                    objects.get(i).adjustX(false);
                }
                if (key == KeyEvent.VK_SPACE) {
                    float mx = mouseInput.getMouseX();
                    float my = mouseInput.getMouseY();

                    float radiansToMouse = (float)(Math.atan2(objects.get(i).getY() - my,
                            objects.get(i).getX() - mx) - Math.PI);

                    objects.get(i).setAccX((float)(Math.cos(radiansToMouse) * 0.1));
                    objects.get(i).setAccY((float)(Math.sin(radiansToMouse) * 0.1));

                    spacePressed = true;

                    System.out.println(radiansToMouse);
                }

                //objects.get(i).setVelX(Game.clamp(objects.get(i).getVelX(), -5, 5));
                //objects.get(i).setVelY(Game.clamp(objects.get(i).getVelY(), -5, 5));

                break;
            }
        }

        if (key == KeyEvent.VK_P) {
            if (Game.gameState == STATE.Game) {
                Game.paused = !Game.paused;
            }
        }

        if (key == KeyEvent.VK_ESCAPE) { System.exit(0); }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getId() == ID.Player) {

                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                    objects.get(i).setAccY(0.1f);
                    objects.get(i).adjustY(true);
                }
                if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
                    objects.get(i).setAccY(-0.1f);
                    objects.get(i).adjustY(true);
                }
                if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                    objects.get(i).setAccX(0.1f);
                    objects.get(i).adjustX(true);
                }
                if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                    objects.get(i).setAccX(-0.1f);
                    objects.get(i).adjustX(true);
                }
                if (key == KeyEvent.VK_SPACE) {

                    objects.get(i).setAccX(0);
                    objects.get(i).setAccY(0);

                }

                //objects.get(i).setVelX(Game.clamp((int)objects.get(i).getVelX(), -5, 5));
                //objects.get(i).setVelY(Game.clamp((int)objects.get(i).getVelY(), -5, 5));

                break;
            }
        }
    }

}