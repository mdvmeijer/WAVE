package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BufferedImageLoader {

    private static BufferedImage image;

    public static BufferedImage getImage(String path) {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
