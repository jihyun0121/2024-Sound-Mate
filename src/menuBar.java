package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class menuBar extends JPanel {
    Color background = new Color(0x002F47);

    private JButton homeButton;
    private JButton recordButton;
    private JButton stopButton;
    private JButton menuButton;

    private boolean isRecording = false; // 녹음 상태 확인
    private List<Character> recordedKeys = new ArrayList<>(); // 키 저장

    ImageIcon homeImg = new ImageIcon("src/img/home.png");
    ImageIcon pressHomeImg = new ImageIcon("src/img/pre-home.png");
    ImageIcon recordImg = new ImageIcon("src/img/record.png");
    ImageIcon pressRecordImg = new ImageIcon("src/img/pre-record.png");
    ImageIcon stopImg = new ImageIcon("src/img/stop.png");
    ImageIcon menuImg = new ImageIcon("src/img/menu.png");
    ImageIcon pressmenuImg = new ImageIcon("src/img/pre-menu.png");

    public menuBar() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(new Dimension(400, 100));
        setBackground(background); // 남색 배경 설정

        homeButton = new JButton(homeImg);
        customizeButton(homeButton, pressHomeImg);

        menuButton = new JButton(menuImg);
        customizeButton(menuButton, pressmenuImg);

        stopButton = new JButton(stopImg);
        customizeButton(stopButton, null);

        recordButton = new JButton(recordImg);
        customizeButton(recordButton, pressRecordImg);

        add(Box.createHorizontalStrut(20)); // 버튼 간격
        add(homeButton);
        add(Box.createHorizontalGlue());

        // 버튼 추가 및 간격 설정
        add(menuButton);
        add(Box.createHorizontalStrut(-35));
        add(stopButton);
        add(recordButton);
        add(Box.createHorizontalStrut(150));

        // 오른쪽에 여백 추가
        add(Box.createHorizontalGlue());

        // 버튼 동작 추가
        recordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isRecording) {
                    isRecording = true;
                    recordedKeys.clear(); // 이전 키 기록 삭제
                    JOptionPane.showMessageDialog(null, "녹음이 시작됩니다. 3, 2, 1");
                }
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRecording) {
                    isRecording = false;
                    int response = JOptionPane.showConfirmDialog(null, "저장하시겠습니까?", "녹음 종료", JOptionPane.YES_NO_OPTION);
                    String currentUserId = Login.getLoggedInUsername();
                    if (currentUserId != null) {
                        System.out.println("현재 로그인된 사용자 ID: " + currentUserId);
                    } else {
                        System.out.println("로그인된 사용자가 없습니다.");
                    }
                }
            }
        });
    }

    // 이미지 바꾸는 메서드
    private void customizeButton(JButton button, ImageIcon rolloverIcon) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        if (rolloverIcon != null) {
            button.setRolloverIcon(rolloverIcon);
        }
    }

    // DB에 녹음 데이터를 저장하는 메서드
    private void saveToDatabase(List<Character> keys) {
        // DB 저장 로직 구현 (예: JDBC 사용)
        System.out.println("DB에 저장된 키: " + keys);
    }

    // 외부에서 키를 추가할 수 있도록 하는 메서드
    public void recordKey(char key) {
        if (isRecording) {
            recordedKeys.add(key);
        }
    }
}
