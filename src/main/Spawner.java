package main;

import java.util.Random;

public class Spawner {

    private Handler handler;
    private HUD hud;
    private Random r = new Random();

    private int scoreKeep = 0;

    public Spawner(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void tick() {
        scoreKeep++;

    if (Game.diff == 0) {
        if (scoreKeep >= 250) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            switch (hud.getLevel()) {
                case 3: handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 200) + 100,
                        -50, ID.BasicEnemy, handler));
                    break;
                case 2: handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 200) + 100,
                        -50, ID.BasicEnemy, handler));
                    break;
                case 6: handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 30),
                        r.nextInt(Game.HEIGHT - 60), ID.FastEnemy, handler));
                    break;
                case 1: handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 30),
                        r.nextInt(Game.HEIGHT - 60), ID.SmartEnemy, handler));
                    break;
                case 300: handler.clearEnemies();
                    handler.addObject(new EnemyBoss(Game.WIDTH / 2 - 48,
                            -96, ID.EnemyBoss, handler));
                    break;
            }
        }
    } else if (Game.diff == 1) {
        if (scoreKeep >= 250) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            switch (hud.getLevel()) {
                case 1: handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 30),
                        r.nextInt(Game.HEIGHT - 60), ID.BasicEnemy, handler));
                    break;
                case 2: handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 30),
                        r.nextInt(Game.HEIGHT - 60), ID.BasicEnemy, handler));
                    break;
                case 3: handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 30),
                        r.nextInt(Game.HEIGHT - 60), ID.FastEnemy, handler));
                    break;
                case 4: handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 30),
                        r.nextInt(Game.HEIGHT - 60), ID.SmartEnemy, handler));
                    break;

            }
        }
    }
    }

    void setScoreKeep(int score) {
        this.scoreKeep = score;
    }
}