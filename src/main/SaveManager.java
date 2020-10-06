package main;

import java.io.*;

public class SaveManager {

    static File file = new File("data.txt");

    public static void saveScore(int score) {
        String scoreString = String.valueOf(score);
        String highScore;

        try {
            BufferedReader br = new BufferedReader(new FileReader("data.txt"));
            highScore = br.readLine();
            if (highScore == null || Integer.parseInt(scoreString) > Integer.parseInt(highScore)) {
                BufferedWriter bw = new BufferedWriter(new FileWriter("data.txt"));
                bw.write(scoreString);
                bw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int loadScore() {
        int highScore = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader("data.txt"));
            highScore = Integer.parseInt(br.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return highScore;
    }

}