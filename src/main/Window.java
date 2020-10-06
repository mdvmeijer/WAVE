package main;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window(int width, int height, String title, Game game) {
        this.setTitle(title);

        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
        this.setMinimumSize(new Dimension(width, height));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.add(game);
        this.setVisible(true);
        game.start();
    }

}