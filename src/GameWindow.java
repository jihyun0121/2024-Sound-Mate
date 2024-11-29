package src;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public GameWindow() {
        setTitle("Rhythm Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());

        // 곡 버튼 추가
        JButton btnMusic1 = new JButton("반짝반짝 작은 별");
        JButton btnMusic2 = new JButton("여수 밤바다 - 버스커 버스커");
        JButton btnMusic3 = new JButton("Welcome To The Show - Day6");

        // 버튼 이벤트 추가
        btnMusic1.addActionListener(e -> startGame(new Music("Twinkle Twinkle Little Star", "sound/sing/WelcomToTheShow", "easy")));
        btnMusic2.addActionListener(e -> startGame(new Music("Yeosu Night Sea - Busker Busker", "sound/sing/WelcomToTheShow", "nomal")));
        btnMusic3.addActionListener(e -> startGame(new Music("Welcome To The Show - Day6", "sound/sing/WelcomToTheShow", "hard")));

        // 버튼을 패널에 추가
        contentPane.add(btnMusic1);
        contentPane.add(btnMusic2);
        contentPane.add(btnMusic3);

        setSize(1280, 720);
        setLocationRelativeTo(null); // 창을 화면 가운데 배치
        setVisible(true);
    }

    private void startGame(Music music) {
        dispose();
        SwingUtilities.invokeLater(() -> new PianoGame(music).setVisible(true));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameWindow::new);
    }
}
