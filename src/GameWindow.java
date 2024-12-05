package src;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class GameWindow extends JFrame {

    private JLabel songTitleLabel; // 노래 제목 레이블
    private JLabel songImageLabel; // 노래 이미지 레이블
    private JLabel levelLabel; // 레벨 레이블
    private JPanel instrumentPanel; // 악기 선택 패널
    private JButton leftArrowButton, rightArrowButton; // 화살표 버튼
    private MusicTrack musicTrack;
    private int currentSongIndex = 0; // 현재 선택된 곡 인덱스

    public GameWindow() {
        setTitle("Rhythm Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null); // 화면 중앙에 배치
        setLayout(new BorderLayout());

        // 음악 트랙 리스트
        musicTrack = new MusicTrack();

        // 노래 제목 레이블
        songTitleLabel = new JLabel(musicTrack.getMusic().get(currentSongIndex).getTitle(), SwingConstants.CENTER);
        songTitleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        // 노래 이미지
        songImageLabel = new JLabel(new ImageIcon(musicTrack.getMusic().get(currentSongIndex).getImagePath()), SwingConstants.CENTER);

        // 레벨 레이블 (곡에 맞는 레벨을 고정 표시)
        levelLabel = new JLabel("Level: " + musicTrack.getMusic().get(currentSongIndex).getLevel(), SwingConstants.CENTER);
        levelLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // 악기 선택 패널
        instrumentPanel = new JPanel(new FlowLayout());
        JButton pianoButton = new JButton("Piano");
        JButton drumButton = new JButton("Drum");
        JButton guitarButton = new JButton("Guitar");

        pianoButton.addActionListener(e -> startGame("Piano"));
        drumButton.addActionListener(e -> startGame("Drum"));
        guitarButton.addActionListener(e -> startGame("Guitar"));

        instrumentPanel.add(pianoButton);
        instrumentPanel.add(drumButton);
        instrumentPanel.add(guitarButton);

        // 화살표 버튼
        leftArrowButton = new JButton("<");
        rightArrowButton = new JButton(">");

        leftArrowButton.addActionListener(e -> changeSong(-1)); // 왼쪽 화살표 클릭 시 이전 곡
        rightArrowButton.addActionListener(e -> changeSong(1)); // 오른쪽 화살표 클릭 시 다음 곡

        // 상단: 노래 제목
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(songTitleLabel, BorderLayout.CENTER);

        // 중간: 노래 이미지 + 화살표 버튼
        JPanel imagePanel = new JPanel(new BorderLayout());
        JPanel arrowPanel = new JPanel(new FlowLayout());
        arrowPanel.add(leftArrowButton);
        arrowPanel.add(rightArrowButton);
        imagePanel.add(songImageLabel, BorderLayout.CENTER);
        imagePanel.add(arrowPanel, BorderLayout.SOUTH);

        // 하단: 레벨 표시 + 악기 선택
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(levelLabel, BorderLayout.NORTH); // 레벨 표시
        bottomPanel.add(instrumentPanel, BorderLayout.CENTER); // 악기 선택 패널

        // 전체 레이아웃
        add(titlePanel, BorderLayout.NORTH);
        add(imagePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // 곡을 변경하는 메서드
    private void changeSong(int direction) {
        currentSongIndex += direction;
        if (currentSongIndex < 0) currentSongIndex = musicTrack.getMusic().size() - 1; // 마지막 곡으로 돌아가게
        if (currentSongIndex >= musicTrack.getMusic().size()) currentSongIndex = 0; // 첫 번째 곡으로 돌아가게

        // 곡 정보 업데이트
        songTitleLabel.setText(musicTrack.getMusic().get(currentSongIndex).getTitle());
        // 곡에 맞는 이미지로 업데이트
        songImageLabel.setIcon(new ImageIcon(musicTrack.getMusic().get(currentSongIndex).getImagePath()));
        // 레벨 정보 업데이트
        levelLabel.setText("Level: " + musicTrack.getMusic().get(currentSongIndex).getLevel());
    }

    // 게임을 시작하는 메서드 (악기 선택)
    private void startGame(String instrument) {
        Music selectedMusic = musicTrack.getMusic().get(currentSongIndex);
        selectedMusic.setInstrument(instrument);

        String noteFilePath;
        Map<Integer, Character> keyMapping;
        if (instrument.equals("Piano")) {
            keyMapping = Map.of(
                    0, 'a',
                    128, 's',
                    256, 'd',
                    384, 'f',
                    512, 'g',
                    640, 'h',
                    768, 'j',
                    896, 'k',
                    1024, 'l',
                    1152, ';'
            );
            noteFilePath = selectedMusic.getPianoNoteFilePath();
            SwingUtilities.invokeLater(() -> new PianoGame(selectedMusic, noteFilePath, keyMapping).setVisible(true));
        } else if (instrument.equals("Drum")) {
            keyMapping = Map.of(
                    0, 'a',
                    172, 'e',
                    344, 'g',
                    550, 'n',
                    768, 'u',
                    943, 'i',
                    1112, 'o'
            );
            noteFilePath = selectedMusic.getDrumNoteFilePath();
            SwingUtilities.invokeLater(() -> new DrumGame(selectedMusic, noteFilePath, keyMapping).setVisible(true));
        } else { // Guitar
            keyMapping = Map.of(
                    0, '1',
                    128, 'e',
                    256, 'a',
                    384, 'd',
                    512, 'g',
                    644, 'c',
                    772, 'f',
                    896, 'b',
                    1024, 'k',
                    1152, '2'
            );
            noteFilePath = selectedMusic.getGuitarNoteFilePath();
            SwingUtilities.invokeLater(() -> new GuitarGame(selectedMusic, noteFilePath, keyMapping).setVisible(true));
        }

        dispose(); // 현재 GameWindow 닫기
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameWindow::new);
    }
}
