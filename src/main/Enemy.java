package main;

import java.util.Random;

public abstract class Enemy extends GameObject {

    protected Random random = new Random();
    protected float accX, accY;
    protected int health = 1;


    public Enemy(float x, float y, ID id, Handler handler) {
        super(x, y, id, handler);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
