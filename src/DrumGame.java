package src;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class DrumGame extends JFrame {
    private String musicTitle;
    private Track track;

    public DrumGame(Music music, Map<Integer, Character> keyMapping) {
        this.musicTitle = music.getTitle();

        setTitle("Drum Game - " + musicTitle);
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
        Drum drum = new Drum();
        drum.setBackground(Color.white); // 배경 색 설정
        add(drum, BorderLayout.SOUTH);

        // 텍스트 파일에서 노트 데이터 로드
        String noteFilePath = music.getNoteFilePath();
        List<Note> noteTimingData = BeatLoader.loadNotes(noteFilePath, keyMapping);
        track.generateNotes(noteTimingData);

        // 키보드 포커스 설정
        drum.requestFocusInWindow();

        // 게임 시작
        track.startGame();

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Music defaultMusic = new Music(
                    "Default Song",
                    "src/sound/sing/default.wav",
                    "easy",
                    "src/img/default_image.png",
                    "src/rhythm/default_notes.txt"
            );

            // 피아노 키 매핑
            Map<Integer, Character> drumKeys = Map.of(
                    100, 'a',
                    200, 's',
                    300, 'd',
                    400, 'f',
                    500, 'g',
                    600, 'h',
                    700, 'j',
                    800, 'k',
                    900, 'l',
                    1000, ';'
            );

            new DrumGame(defaultMusic, drumKeys).setVisible(true);
        });
    }
}
