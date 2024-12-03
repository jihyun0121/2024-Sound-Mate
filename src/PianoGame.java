package src;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class PianoGame extends JFrame {
    private String musicTitle;
    private Track track;

    public PianoGame(Music music, Map<Integer, Character> keyMapping) {
        this.musicTitle = music.getTitle();

        setTitle("Piano Game - " + musicTitle);
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
        Piano piano = new Piano();
        piano.setBackground(Color.white); // 배경 색 설정
        add(piano, BorderLayout.SOUTH);

        // 텍스트 파일에서 노트 데이터 로드
        String noteFilePath = music.getNoteFilePath();
        List<Note> noteTimingData = BeatLoader.loadNotes(noteFilePath, keyMapping);
        track.generateNotes(noteTimingData);

        // 키보드 포커스 설정
        piano.requestFocusInWindow();

        // 게임 시작
        track.startGame();

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Music defaultMusic = new Music(
                    "Default Song",
                    "sound/sing/default.wav",
                    "easy",
                    "src/img/default_image.png",
                    "rhythm/default_notes.txt"
            );

            // 피아노 키 매핑
            Map<Integer, Character> pianoKeys = Map.of(
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

            new PianoGame(defaultMusic, pianoKeys).setVisible(true);
        });
    }
}
