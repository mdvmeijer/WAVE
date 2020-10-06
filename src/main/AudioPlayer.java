package main;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import java.util.HashMap;
import java.util.Map;

public class AudioPlayer {

    private static Map<String, Sound> soundMap = new HashMap<>();
    private static Map<String, Music> musicMap = new HashMap<>();

    public static void load() {

        try {
            soundMap.put("click_sound", new Sound("res/sounds/click_sound.wav"));
            musicMap.put("music", new Music("res/sounds/music.ogg"));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public static Music getMusic(String key) {
        return musicMap.get(key);
    }

    public static Sound getSound(String key) {
        return soundMap.get(key);
    }

}