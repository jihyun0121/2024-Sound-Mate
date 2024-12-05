package src;

import src.db.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class menuBar extends JPanel {
    Color background = new Color(0x002F47);

    private JButton homeButton;
    private JButton recordButton;
    private JButton menuButton;

    private boolean isRecording = false; // 녹음 상태 확인
    private ArrayList<Character> recordedKeys = new ArrayList<>(); // 키 저장
    private InstrumentPanel instrumentPanel;

    private CardLayout cardLayout; // 화면 전환을 위한 CardLayout
    private JPanel cardPanel; // CardLayout에 추가할 패널

    ImageIcon homeImg = new ImageIcon("src/img/home.png");
    ImageIcon pressHomeImg = new ImageIcon("src/img/pre-home.png");
    ImageIcon recordImg = new ImageIcon("src/img/record.png");
    ImageIcon pressRecordImg = new ImageIcon("src/img/pre-record.png");
    ImageIcon menuImg = new ImageIcon("src/img/menu.png");
    ImageIcon pressmenuImg = new ImageIcon("src/img/pre-menu.png");

    public menuBar(InstrumentPanel instrumentPanel) {
        this.instrumentPanel = instrumentPanel;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(new Dimension(1280, 100));
        setBackground(background); // 남색 배경 설정

        homeButton = new JButton(homeImg);
        customizeButton(homeButton, pressHomeImg);

        menuButton = new JButton(menuImg);
        customizeButton(menuButton, pressmenuImg);

        recordButton = new JButton(recordImg);
        customizeButton(recordButton, pressRecordImg);

        add(Box.createHorizontalStrut(20));
        add(homeButton);
        add(Box.createHorizontalStrut(980));
        add(recordButton);
        add(Box.createHorizontalStrut(-40));
        add(menuButton);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window parentWindow = SwingUtilities.getWindowAncestor(menuBar.this);

                if (parentWindow != null) {
                    parentWindow.dispose();
                }

                String username = Login.getLoggedInUsername();
                if (username != null) {
                    new MainApp(username);
                } else {
                    new Login();
                }
            }
        });

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "SavedFiles"); // 저장된 파일 화면으로 이동
            }
        });

        // 녹음 버튼
        recordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isRecording) {
                    // 녹음 시작
                    isRecording = true;
                    instrumentPanel.setRecording(true);
                    recordedKeys.clear();
                    JOptionPane.showMessageDialog(null, "녹음을 시작합니다.");
                } else {
                    // 녹음 멈춤
                    isRecording = false;
                    instrumentPanel.setRecording(false);

                    recordedKeys = instrumentPanel.getRecordedKeys();
                    int response = JOptionPane.showConfirmDialog(null,
                            "저장하시겠습니까?",
                            "저장",
                            JOptionPane.YES_NO_OPTION);

                    if (response == JOptionPane.YES_OPTION) {
                        String userId = Login.getLoggedInUsername();

                        if (userId == null || userId.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "로그인되지 않았습니다. 녹음을 저장하려면 로그인해 주세요.", "로그인 필요", JOptionPane.WARNING_MESSAGE);
                        } else {
                            // 사용자 ID가 유효한 경우 녹음 데이터를 저장합니다.
                            String instrument = instrumentPanel.getInstrumentType();
                            boolean saveSuccess = DatabaseConnection.saveRecording(userId, recordedKeys, instrument);

                            if (saveSuccess) {
                                JOptionPane.showMessageDialog(null, "녹음이 성공적으로 저장되었습니다.");
                            } else {
                                JOptionPane.showMessageDialog(null, "녹음 저장 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
                instrumentPanel.requestFocusInWindow();
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

}
