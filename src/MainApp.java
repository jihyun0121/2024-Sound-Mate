package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private String loggedInUser; // 로그인된 사용자

    public MainApp(String username) {
        this.loggedInUser = username;

        // 프레임 설정
        setTitle("Sound Mate - Main");
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // CardLayout 설정
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        add(cardPanel, BorderLayout.CENTER);

        // 화면 추가
        cardPanel.add(createMainMenuScreen(), "MainMenu");      // 기능 선택 - 악기연주, 리듬게임
        cardPanel.add(createInstrumentSelectionScreen(), "InstrumentSelection");        // 악기 선택

        setVisible(true);
    }

    // 메인 메뉴 화면
    private JPanel createMainMenuScreen() {
        JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBackground(Color.WHITE);

        JButton playMusicButton = new JButton("악기 연주");
        JButton rhythmGameButton = new JButton("리듬 게임");

        playMusicButton.addActionListener(e -> cardLayout.show(cardPanel, "InstrumentSelection"));
        rhythmGameButton.addActionListener(e -> cardLayout.show(cardPanel, "InstrumentSelection"));

        menuPanel.add(playMusicButton);
        menuPanel.add(rhythmGameButton);

        return menuPanel;
    }

    // 악기 선택 화면
    private JPanel createInstrumentSelectionScreen() {
        JPanel instrumentPanel = new JPanel(new GridLayout(1, 3, 20, 20));
        instrumentPanel.setBackground(Color.WHITE);

        JButton pianoButton = new JButton("피아노");
        JButton guitarButton = new JButton("기타");
        JButton drumButton = new JButton("드럼");

        pianoButton.addActionListener(e -> new playPiano());
        guitarButton.addActionListener(e -> new playGuitar());
        drumButton.addActionListener(e -> new playDrum());

        instrumentPanel.add(pianoButton);
        instrumentPanel.add(guitarButton);
        instrumentPanel.add(drumButton);

        return instrumentPanel;
    }
}
