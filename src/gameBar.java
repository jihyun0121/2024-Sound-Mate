package src;

import javax.swing.*;
import java.awt.*;

public class gameBar extends JPanel {
    private JLabel scoreLabel;
    private JLabel comboLabel;
    private JLabel feedbackLabel; // 판정 라벨 추가

    // 버튼 관련 변수
    Color background = new Color(0x002F47);
    private JButton homeButton;
    ImageIcon homeImg = new ImageIcon("src/img/home.png");
    ImageIcon pressHomeImg = new ImageIcon("src/img/pre-home.png");
    private JButton stopButton;
    ImageIcon stopImg = new ImageIcon("src/img/stop.png");

    public gameBar() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS)); // 가로로 정렬
        setPreferredSize(new Dimension(1280, 70));
        setBackground(background); // 남색 배경 설정

        // 홈 버튼 초기화
        homeButton = new JButton(homeImg);
        customizeButton(homeButton, pressHomeImg);

        // 정지 버튼 초기화
        stopButton = new JButton(stopImg);
        customizeButton(stopButton, null);

        // 점수 라벨 초기화
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // 콤보 라벨 초기화
        comboLabel = new JLabel("Combo: 0");
        comboLabel.setForeground(Color.YELLOW);
        comboLabel.setFont(new Font("Arial", Font.BOLD, 24));


        feedbackLabel = new JLabel(" ");
        feedbackLabel.setForeground(Color.WHITE);
        feedbackLabel.setFont(new Font("Arial", Font.BOLD, 20));


        // 구성 요소 추가
        add(Box.createHorizontalStrut(10)); // 좌측 여백
        add(homeButton); // 홈 버튼 추가
        add(Box.createHorizontalStrut(30)); // 홈 버튼과 점수 간격
        add(scoreLabel); // 점수 라벨 추가
        add(Box.createHorizontalGlue()); // 점수와 콤보 사이 공간 균등 분배
        add(feedbackLabel);
        add(Box.createHorizontalGlue()); // 점수와 콤보 사이 공간 균등 분배
        add(comboLabel); // 콤보 라벨 추가
        add(Box.createHorizontalStrut(30)); // 콤보와 정지 버튼 간격
        add(stopButton); // 정지 버튼 추가
        add(Box.createHorizontalStrut(10)); // 우측 여백
    }

    public void setScore(int score) {
        scoreLabel.setText("Score: " + score);
        revalidate();
        repaint();
    }

    public void setCombo(int combo) {
        comboLabel.setText("Combo: " + combo);
        revalidate();
        repaint();
    }

    public void setFeedback(String feedback) {
        feedbackLabel.setText(feedback);
        revalidate();
        repaint();
    }

    private void customizeButton(JButton button, ImageIcon rolloverIcon) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        if (rolloverIcon != null) {
            button.setRolloverIcon(rolloverIcon);
        }
    }

}