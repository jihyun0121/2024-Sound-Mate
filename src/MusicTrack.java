package src;

import java.util.ArrayList;

public class MusicTrack {
    private ArrayList<Music> musics;

    public MusicTrack() {
        musics = new ArrayList<>();
        musics.add(new Music(
                "Twinkle Twinkle Little Star",
                "sound/sing/LittleStar.wav",
                "easy",
                "src/img/STAR.png",
                "src/rhythm/P-LittleStar.txt",
                "src/rhythm/D-LittleStar.txt",
                "src/rhythm/G-LittleStar.txt"
        ));
        musics.add(new Music(
                "Yeosu Night Sea - Busker Busker",
                "sound/sing/YeosuNightSea.wav",
                "normal",
                "src/img/NIGHT.png",
                "src/rhythm/P-YeosuNightSea.txt",
                "src/rhythm/D-YeosuNightSea.txt",
                "src/rhythm/G-YeosuNightSea.txt"
        ));
        musics.add(new Music(
                "Welcome to the Show - Day6",
                "sound/sing/WelcomeToTheShow.wav",
                "hard",
                "src/img/WTTS.png",
                "src/rhythm/P-WelcomeToTheShow.txt",
                "src/rhythm/D-WelcomeToTheShow.txt",
                "src/rhythm/G-WelcomeToTheShow.txt"
        ));
    }

    public ArrayList<Music> getMusic() {
        return musics;
    }
}
