package main;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FontManager {

    private static ArrayList<Font> fontList = new ArrayList<>();

    public static void loadFonts() {

        try {
            fontList.add(Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/space_age.ttf")));
            fontList.add(Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/robotica.ttf")));
            fontList.add(Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/sofachrome.ttf")));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        for (Font font : fontList) {
            ge.registerFont(font);
        }
    }

}