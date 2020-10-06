package background;
import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BackgroundGenerator {

    static final Random random = new Random();

    private ArrayList<GalaxyObject> shapes = new ArrayList<>();
    private int windowWidth = Game.WIDTH;
    private int windowHeight = Game.HEIGHT;

    public BackgroundGenerator() {
        if (Game.WIDTH == 1920) {
            shapes.ensureCapacity(1280);
        } else if (Game.WIDTH == 800) {
            shapes.ensureCapacity(600);
        }
    }

    public void generate() {

        int numberOfShapes = 0;
        if (Game.WIDTH == 1920) {
            numberOfShapes = random.nextInt(100) + 180;
        } else if (Game.WIDTH == 800) {
            numberOfShapes = random.nextInt(100) + 80;
        }

        GalaxyObject shape;

        if (Game.WIDTH == 1920) {
            for (int i = 0; i < 1000; i++) {
                shape = new backgroundPixel(windowWidth, windowHeight);
                shapes.add(shape);
            }
        } else if (Game.WIDTH == 800) {
            for (int i = 0; i < 400; i++) {
                shape = new backgroundPixel(windowWidth, windowHeight);
                shapes.add(shape);
            }
        }

        for (int i = 0; i <= numberOfShapes; i++) {
            int shapeNumber = random.nextInt(70);
            switch (shapeNumber) {
                case 0: shape = new Planet1(windowWidth, windowHeight);
                    break;
                case 1: shape = new Planet1(windowWidth, windowHeight);
                    break;
                case 2: shape = new Planet2(windowWidth, windowHeight);
                    break;
                case 3: shape = new Planet2(windowWidth, windowHeight);
                    break;
                case 4: shape = new Planet3(windowWidth, windowHeight);
                    break;
                case 5: shape = new Planet3(windowWidth, windowHeight);
                    break;
                case 6: shape = new Comet(windowWidth, windowHeight);
                    break;
                case 7: shape = new Supernova(windowWidth, windowHeight);
                    break;
                default: shape = new Star(windowWidth, windowHeight);
                    break;
            }

            shapes.add(shape);
        }

        saveBackground();
    }

    private void saveBackground() {
        BufferedImage tempImage = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = tempImage.createGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, windowWidth, windowHeight);

        for (GalaxyObject object : shapes){
            object.draw(g);
        }

        shapes.clear();

        try {
            ImageIO.write(tempImage, "png", new File("res/backgrounds/game_background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}