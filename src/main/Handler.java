package main;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Handler {

    ArrayList<GameObject> objects = new ArrayList<>();

    public void tick() {
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).render(g);
        }
    }

    public void clearEnemies() {
        Iterator<GameObject> it = objects.iterator();
        while (it.hasNext()) {
            GameObject object = it.next();
            if (object.getId() == ID.Player || object.getId() == ID.PlayerBullet || object.getId() == ID.Trail) { }
            else { it.remove(); }
        }
    }

    public void addObject(GameObject object) {
        objects.add(object);
    }

    public void removeObject(GameObject object) {
        objects.remove(object);
    }

    public ArrayList<GameObject> getObjects() {
        return objects;
    }

}