package main;

import java.awt.*;

public abstract class GameObject {

    Handler handler;

    protected float x, y;
    protected ID id;
    protected float velX, velY, accX, accY;
    protected boolean adjX, adjY;

    public GameObject(float x, float y, ID id, Handler handler) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.handler = handler;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    public int getHealth() { return 1; }
    public void setHealth(int health) {}
    public void adjustX(boolean adj) { this.adjX = adj; }
    public void adjustY(boolean adj) { this.adjY = adj; }


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public float getAccX() {
        return accX;
    }

    public void setAccX(float accX) {
        this.accX = accX;
    }

    public float getAccY() {
        return accY;
    }

    public void setAccY(float accY) {
        this.accY = accY;
    }

}