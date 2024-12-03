package src;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class GuitarGame extends JFrame {
    private String musicTitle;
    private Track track;

    public GuitarGame(Music music, Map<Integer, Character> keyMapping) {
        this.musicTitle = music.getTitle();

        setTitle("Guitar Game - " + musicTitle);
        this.setSize(1280, 720);
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // 노트 트랙 패널
        track = new Track();
        track.setBackground(Color.white); // 배경 색 설정
        add(track, BorderLayout.CENTER);

        // 상단 게임바 패널
        gameBar gameBar = new gameBar();
        add(gameBar, BorderLayout.NORTH);

        // 하단 피아노 키보드 패널
        Guitar guitar = new Guitar();
        guitar.setBackground(Color.white); // 배경 색 설정
        add(guitar, BorderLayout.SOUTH);

        // 텍스트 파일에서 노트 데이터 로드
        String noteFilePath = music.getNoteFilePath();
        List<Note> noteTimingData = BeatLoader.loadNotes(noteFilePath, keyMapping);
        track.generateNotes(noteTimingData);

        // 키보드 포커스 설정
        guitar.requestFocusInWindow();

        // 게임 시작
        track.startGame();

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Music defaultMusic = new Music(
                    "Default Song",
                    "sound/sing/WelcomeToTheShow.wav",
                    "easy",
                    "src/img/default_image.png",
                    "src/rhythm/default_notes.txt.txt"
            );

            // 피아노 키 매핑
            Map<Integer, Character> guitarKeys = Map.of(
                    100, 'e',
                    200, 'a',
                    300, 'd',
                    400, 'g',
                    500, 'c',
                    600, 'f',
                    700, 'b',
                    800, 'k'
            );

            new GuitarGame(defaultMusic, guitarKeys).setVisible(true);
        });
    }
}
