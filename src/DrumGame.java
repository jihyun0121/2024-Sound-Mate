package src;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DrumGame extends JFrame {
    private String musicTitle;
    private Track track;
    private gameBar gameBarPanel;

    public DrumGame(Music music, String noteFilePath, Map<Integer, Character> keyMapping) {
        this.musicTitle = music.getTitle();

        setTitle("Drum Game - " + musicTitle);
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

        // SheetMusicPanel을 생성하고, Drum 객체에 넘겨줌
        SheetMusicPanel sheetMusicPanel = new SheetMusicPanel();
        Drum drum = new Drum(sheetMusicPanel);  // SheetMusicPanel을 전달

        // 하단 악기 패널
        drum.setBackground(Color.WHITE);

        // 중앙에 트랙과 악기 패널을 포함하는 컨테이너 생성
        JPanel trackAndInstrumentPanel = new JPanel(new BorderLayout());
        trackAndInstrumentPanel.add(track, BorderLayout.CENTER); // 트랙을 중앙에 배치
        trackAndInstrumentPanel.add(drum, BorderLayout.SOUTH);  // 악기를 아래에 배치

        // 컨테이너를 중앙에 추가
        add(trackAndInstrumentPanel, BorderLayout.CENTER);

        // 텍스트 파일에서 노트 데이터 로드
        List<Note> noteTimingData = BeatLoader.loadNotes(noteFilePath, keyMapping, "drum");
        track.generateNotes(noteTimingData);

        // 게임 시작 전, 포커스 요청
        SwingUtilities.invokeLater(() -> {
            drum.requestFocusInWindow(); // 포커스를 설정하여 키 입력을 받을 수 있도록 함
        });

        // 게임 시작
        track.startGame();

        drum.addKeyListener(new KeyAdapter() {
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
                    "src/rhythm/D-LittleStar.txt",
                    "src/rhythm/D-YeosuNightSea.txt",
                    "src/rhythm/D-WelcomeToTheShow.txt"
            );

            // 드럼 키 매핑
            Map<Integer, Character> drumKeys = Map.of(
                    0, 'a',
                    172, 'e',
                    344, 'g',
                    550, 'n',
                    768, 'u',
                    943, 'i',
                    1112, 'o'
            );

            String noteFilePath = defaultMusic.getDrumNoteFilePath();
            new DrumGame(defaultMusic, noteFilePath, drumKeys).setVisible(true);
        });
    }
}
