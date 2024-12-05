package src;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GuitarGame extends JFrame {
    private String musicTitle;
    private Track track;
    private gameBar gameBarPanel;

    public GuitarGame(Music music, String noteFilePath, Map<Integer, Character> keyMapping) {
        this.musicTitle = music.getTitle();

        setTitle("Guitar Game - " + musicTitle);
        setSize(1280, 720);
        getContentPane().setBackground(Color.white);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // gameBar 객체 초기화
        gameBarPanel = new gameBar();
        add(gameBarPanel, BorderLayout.NORTH); // 상단에 배치

        // 노트 트랙 패널
        track = new Track(keyMapping, gameBarPanel);
        track.setBackground(Color.WHITE);

        // SheetMusicPanel을 생성하고, Guitar 객체에 넘겨줌
        SheetMusicPanel sheetMusicPanel = new SheetMusicPanel();
        Guitar guitar = new Guitar(sheetMusicPanel);  // SheetMusicPanel을 전달

        // 하단 악기 패널
        guitar.setBackground(Color.WHITE);

        // 중앙에 트랙과 악기 패널을 포함하는 컨테이너 생성
        JPanel trackAndInstrumentPanel = new JPanel(new BorderLayout());
        trackAndInstrumentPanel.add(track, BorderLayout.CENTER); // 트랙을 중앙에 배치
        trackAndInstrumentPanel.add(guitar, BorderLayout.SOUTH);  // 악기를 아래에 배치

        // 컨테이너를 중앙에 추가
        add(trackAndInstrumentPanel, BorderLayout.CENTER);

        // 텍스트 파일에서 노트 데이터 로드
        List<Note> noteTimingData = BeatLoader.loadNotes(noteFilePath, keyMapping, "guitar");
        track.generateNotes(noteTimingData);

        // 게임 시작 전, 포커스 요청
        SwingUtilities.invokeLater(() -> {
            guitar.requestFocusInWindow(); // 포커스를 설정하여 키 입력을 받을 수 있도록 함
        });

        // 게임 시작
        track.startGame();

        guitar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char keyPressed = Character.toLowerCase(e.getKeyChar());
                track.keyPressed(keyPressed); // 키가 눌리면 Track에서 처리
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Music defaultMusic = new Music(
                    "Twinkle Twinkle Little Star",
                    "sound/sing/LittleStar.wav",
                    "easy",
                    "src/img/STAR.png",
                    "src/rhythm/G-LittleStar.txt",
                    "src/rhythm/G-YeosuNightSea.txt",
                    "src/rhythm/G-WelcomeToTheShow.txt"
            );

            // 기타 키 매핑
            Map<Integer, Character> guitarKeys = Map.of(
                    0, '1',
                    142, 'e',
                    270, 'a',
                    398, 'd',
                    526, 'g',
                    644, 'c',
                    772, 'f',
                    900, 'b',
                    1024, 'k',
                    1152, '2'
            );

            String noteFilePath = defaultMusic.getGuitarNoteFilePath();
            new GuitarGame(defaultMusic, noteFilePath, guitarKeys).setVisible(true);
        });
    }
}
