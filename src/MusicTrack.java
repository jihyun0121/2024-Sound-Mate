package src;

import java.util.ArrayList;

public class MusicTrack {
    private ArrayList<Music> musics;

    public MusicTrack() {
        musics = new ArrayList<>();
        musics.add(new Music(
                "Twinkle Twinkle Little Star",
                "sound/sing/LittleStar.txt.wav",
                "easy",
                "src/img/STAR.png",
                "rhythm/LittleStar.txt.txt"
        ));
        musics.add(new Music(
                "Yeosu Night Sea - Busker Busker",
                "sound/sing/YeosuNightSea.txt.wav",
                "normal",
                "src/img/NIGHT.png",
                "rhythm/YeosuNightSea.txt.txt"
        ));
        musics.add(new Music(
                "Welcome to the Show - Day6",
                "sound/sing/WelcomeToTheShow.txt.wav",
                "hard",
                "src/img/WTTS.png",
                "rhythm/WelcomeToTheShow.txt.txt"
        ));
    }

    public ArrayList<Music> getMusic() {
        return musics;
    }
}
