package src;

import javax.swing.*;
import java.awt.*;

public class PianoGame extends JFrame {
    private String musicTitle;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Music defaultMusic = new Music("Default Song", "sound/sing/WelcomToTheShow", "easy");
            new PianoGame(defaultMusic).setVisible(true);
        });
    }

    // Music 객체를 매개변수로 받는 생성자
    public PianoGame(Music music) {
        this.musicTitle = music.getTitle();

        setTitle("PianoGame - " + musicTitle);
        this.setSize(1280, 720);
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);       // 창을 가운데에 띄움
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());


//        // 곡 정보를 표시
//        JLabel songLabel = new JLabel("Now Playing: " + musicTitle + SwingConstants.CENTER);
//        songLabel.setFont(new Font("Arial", Font.BOLD, 24));
//        add(songLabel, BorderLayout.NORTH);

        // 노트 트랙 패널
        Track track = new Track();
        add(track, BorderLayout.CENTER);

        // 상단 게임바 패널
        gameBar gameBar = new gameBar();
        add(gameBar, BorderLayout.NORTH);

        // 하단 피아노 키보드 패널
        Piano piano = new Piano();
        add(piano, BorderLayout.SOUTH);

        // 키보드 포커스 설정
        piano.requestFocusInWindow();

        setVisible(true);
    }
}
