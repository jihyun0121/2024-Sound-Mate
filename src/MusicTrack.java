package src;

import java.util.ArrayList;

public class MusicTrack {
    private ArrayList<Music> musics;

    public MusicTrack() {
        musics = new ArrayList<>();
        musics.add(new Music("Twinkle Twinkle Little Star", "path/to/twinkle.wav", "easy"));
        musics.add(new Music("Yeosu Night Sea - Busker Busker", "path/to/yeosu.wav", "nomal"));
        musics.add(new Music("Welcome to the Show - Day6", "path/to/welcome.wav", "hard"));
    }

    public ArrayList<Music> getMusic() {
        return musics;
    }
}
