package src;

import javax.swing.*;
import java.awt.*;

public class gameBar extends JPanel {
    // 기존 코드 그대로 사용
    Color background = new Color(0x002F47);
    private JButton homeButton;
    ImageIcon homeImg = new ImageIcon("src/img/home.png");
    ImageIcon pressHomeImg = new ImageIcon("src/img/pre-home.png");
    private JButton stopButton;
    ImageIcon stopImg = new ImageIcon("src/img/stop.png");

    // 점수를 표시할 라벨 추가
    private JLabel scoreLabel;

    public gameBar() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(new Dimension(400, 70));
        setBackground(background); // 남색 배경 설정

        // 버튼들 초기화
        homeButton = new JButton(homeImg);
        customizeButton(homeButton, pressHomeImg);

        stopButton = new JButton(stopImg);
        customizeButton(stopButton, null);

        // 점수 라벨 초기화 (임시로 0으로 설정)
        scoreLabel = new JLabel("0");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // 레이아웃에 버튼과 점수 추가
        add(Box.createHorizontalStrut(20)); // 왼쪽 아이콘과 간격
        add(homeButton);

        add(Box.createHorizontalGlue()); // 가운데 여백을 확장

        // 점수를 가운데에 배치
        add(scoreLabel);

        add(Box.createHorizontalGlue()); // 가운데 여백을 확장

        add(Box.createHorizontalStrut(20)); // 오른쪽 아이콘과 간격
        add(stopButton);

        // 오른쪽에 여백 추가
        add(Box.createHorizontalGlue());
    }

    // 점수 업데이트 메서드 추가
    public void setScore(int score) {
        scoreLabel.setText(String.valueOf(score)); // 점수 텍스트 업데이트
        revalidate(); // 컴포넌트 레이아웃 다시 계산
        repaint(); // 화면 갱신
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
