package main;

import java.awt.image.BufferedImage;

public class SpriteManager {

    public static BufferedImage getSprite(String file) {
        return BufferedImageLoader.getImage("res/sprites/" + file + ".png");
    }

}