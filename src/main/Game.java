package main;

import background.BackgroundGenerator;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1920, HEIGHT = WIDTH / 16 * 9;

    private Thread thread;
    private boolean running = false;

    private Random r = new Random();

    private Handler handler;
    private HUD hud;
    private Spawner spawner;
    private Menu menu;
    private MouseInput mouseInput;

    private BufferedImage gameBackground = null;
    private BufferedImage menuBackground = null;

    static boolean paused = false;
    static STATE gameState = STATE.Menu;
    static int diff = 0;

    private BackgroundGenerator generator;

    // 0 = normal
    // 1 = hard

    public Game() {
        handler = new Handler();
        hud = new HUD();
        spawner = new Spawner(handler, hud);
        FontManager.loadFonts();
        menu = new Menu(handler, hud, spawner, this);
        mouseInput = new MouseInput(handler, hud, spawner, menu, this);
        this.addKeyListener(new KeyInput(handler, mouseInput));
        this.addMouseListener(mouseInput);
        this.addMouseMotionListener(mouseInput);

        AudioPlayer.load();

        generator = new BackgroundGenerator();

        main.AudioPlayer.getMusic("music").loop(1, 0.5f);

        new Window(WIDTH, HEIGHT, "WAVE", this);

        getNewBackground();

        gameBackground = BufferedImageLoader.getImage("res/backgrounds/game_background.png");
        menuBackground = BufferedImageLoader.getImage("res/backgrounds/menu_background.png");

        if (gameState == STATE.Game) {
            handler.addObject(new Player((WIDTH / 2) - 16, (HEIGHT / 2) - 32, ID.Player, handler, mouseInput));
        } /*else {
            for (int i = 0; i < 20; i++) {
                handler.addObject(new main.MenuParticle(r.nextInt(main.Game.WIDTH - 30),
                        r.nextInt(main.Game.HEIGHT - 60), main.ID.main.MenuParticle, handler));
            }
        }*/
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        int ticks = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
                ticks++;
                //render();
                //frames++;
            }
            if (running) {
                render();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.printf("FPS: %d   ticks: %d\n", frames, ticks);
                frames = 0;
                ticks = 0;
            }
        }
        stop();
    }

    private void tick() {
        if (gameState == STATE.Game) {
            if (!paused) {
                handler.tick();
                hud.tick();
                spawner.tick();
                mouseInput.tick();

                if (HUD.HEALTH == 0) {
                    HUD.HEALTH = 100;
                    handler.objects.clear();
                    gameState = STATE.End;
                    SaveManager.saveScore(hud.getScore());
                    getNewBackground();
                    gameBackground = BufferedImageLoader.getImage("res/backgrounds/game_background.png");
                    /*for (int i = 0; i < 20; i++) {
                        handler.addObject(new main.MenuParticle(r.nextInt(WIDTH - 100) + 50,
                                r.nextInt(HEIGHT - 100) + 50, main.ID.main.MenuParticle, handler));
                    }*/
                }
            }
        } else if (gameState == STATE.Menu || gameState == STATE.Stats || gameState == STATE.End
                || gameState == STATE.Select) {
            handler.tick();
            menu.tick();
            mouseInput.tick();
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        if (gameState == STATE.Game) {
            g.drawImage(gameBackground, 0, 0, null);
        } else {
            g.drawImage(menuBackground, 0, 0, null);
        }
        //g.setColor(Color.BLACK);
        //g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        if (paused) {
            g.setColor(Color.WHITE);
            g.drawString("PAUSED", 100, 100);
        }

        if (gameState == STATE.Game) {
            hud.render(g);
        } else if (gameState == STATE.Menu || gameState == STATE.Stats || gameState == STATE.End
                || gameState == STATE.Select) {
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    public static float clamp(float pos, float min, float max) {
        if (pos >= max) {
            return max;
        } else if (pos <= min) {
            return min;
        } else {
            return pos;
        }
    }

    void getNewBackground() {
        generator.generate();
    }

    public static void main(String[] args) {
        new Game();
    }

}