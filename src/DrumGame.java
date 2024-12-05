package src;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class DrumGame extends JFrame {
    private String musicTitle;
    private Track track;

    public DrumGame(Music music, String noteFilePath, Map<Integer, Character> keyMapping) {
        this.musicTitle = music.getTitle();

        setTitle("Drum Game - " + musicTitle);
        setSize(1280, 720);
        getContentPane().setBackground(Color.white);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 노트 트랙 패널
        track = new Track();
        track.setBackground(Color.WHITE); // 배경 색 설정
        add(track, BorderLayout.CENTER);

        // 상단 게임바 패널
        gameBar gameBar = new gameBar();
        add(gameBar, BorderLayout.NORTH);

        // 노트 트랙 패널과 이미지 패널을 감싸는 새로운 패널 생성
        JPanel trackContainer = new JPanel();
        trackContainer.setLayout(new BorderLayout());

        // 하단 악기 패널
        Drum drum = new Drum();
        drum.setBackground(Color.WHITE);

        // Line.png 이미지 로드 및 크기 조정
        ImageIcon trackImageIcon = new ImageIcon("src/img/Line.png");
        Image originalImage = trackImageIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(trackImageIcon.getIconWidth(), 10, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // JLabel에 크기가 조정된 아이콘 설정
        JLabel trackImageLabel = new JLabel(resizedIcon);
        trackImageLabel.setHorizontalAlignment(SwingConstants.CENTER); // 이미지 정렬


        // trackContainer에 추가
        trackContainer.add(track, BorderLayout.CENTER); // 노트 트랙
        trackContainer.add(trackImageLabel, BorderLayout.SOUTH); // 악기 위에 이미지

        // 최종 배치
        add(trackContainer, BorderLayout.CENTER); // 전체 중앙에 trackContainer 배치
        add(drum, BorderLayout.SOUTH); // 악기 패널은 맨 아래


        // 텍스트 파일에서 노트 데이터 로드
        List<Note> noteTimingData = BeatLoader.loadNotes(noteFilePath, keyMapping, "drum");
        track.generateNotes(noteTimingData);

        // 게임 시작 전, 포커스 요청 (SwingUtilities로 래핑)
        SwingUtilities.invokeLater(() -> {
            drum.requestFocusInWindow(); // 포커스를 설정하여 키 입력을 받을 수 있도록 함
        });

        // 게임 시작
        track.startGame();

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
                    599, ' ',
                    768, 'u',
                    943, 'k',
                    1112, 'o'
            );

            String noteFilePath = defaultMusic.getDrumNoteFilePath();
            new DrumGame(defaultMusic, noteFilePath, drumKeys).setVisible(true);
        });
    }
}
