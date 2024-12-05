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
    private List<Character> recordedKeys = new ArrayList<>(); // 키 저장
    private Piano pianoPanel;

    ImageIcon homeImg = new ImageIcon("src/img/home.png");
    ImageIcon pressHomeImg = new ImageIcon("src/img/pre-home.png");
    ImageIcon recordImg = new ImageIcon("src/img/record.png");
    ImageIcon pressRecordImg = new ImageIcon("src/img/pre-record.png");
    ImageIcon menuImg = new ImageIcon("src/img/menu.png");
    ImageIcon pressmenuImg = new ImageIcon("src/img/pre-menu.png");

    public menuBar(Piano pianoPanel) {
        this.pianoPanel = pianoPanel;

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
                pianoPanel.requestFocusInWindow();  // Piano 클래스에 포커스를 줌
            }
        });

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pianoPanel.requestFocusInWindow();  // Piano 클래스에 포커스를 줌
            }
        });

        // 녹음 버튼
        recordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isRecording) {
                    // 녹음 시작
                    isRecording = true;
                    pianoPanel.setRecording(true);
                    recordedKeys.clear();
                    JOptionPane.showMessageDialog(null, "녹음을 시작합니다.");
                } else {
                    // 녹음 멈춤
                    isRecording = false;
                    pianoPanel.setRecording(false);

                    ArrayList<Character> recordedKeys = pianoPanel.getRecordedKeys();
                    int response = JOptionPane.showConfirmDialog(null,
                            "저장하시겠습니까?",
                            "저장",
                            JOptionPane.YES_NO_OPTION);

                    if (response == JOptionPane.YES_OPTION) {
                        String userId = .getLoggedInUsername();
                        boolean saveSuccess = DatabaseConnection.saveRecording("testuser", recordedKeys);

                        if (saveSuccess) {
                            JOptionPane.showMessageDialog(null, "녹음이 성공적으로 저장되었습니다.");
                        } else {
                            JOptionPane.showMessageDialog(null, "녹음 저장 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                pianoPanel.requestFocusInWindow();
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

    public void recordKey(char key) {
        if (isRecording) {
            recordedKeys.add(key);
        }
    }

    // 녹음 버튼
//        recordButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (isRecording) {
//                    // 녹음 종료
//                    isRecording = false;
//                    int response = JOptionPane.showConfirmDialog(null, "저장하시겠습니까?", "저장", JOptionPane.YES_NO_OPTION);
//                    if (response == JOptionPane.YES_OPTION) {
//                        saveToDatabase(recordedKeys);
//                    }
//                } else {
//                    // 녹음 시작
//                    isRecording = true;
//                    recordedKeys.clear();
//                    JOptionPane.showMessageDialog(null, "녹음을 시작하시겠습니까?");
//                }
//            }
//        });
    //  }

    // 녹음된 키를 리스트에 추가
//    public void recordKey(char key) {
//        if (isRecording) {
//            recordedKeys.add(key); // 키가 눌리면 녹음
//        }
//    }



//    private void saveToDatabase(List<Character> keys) {
//        // DB에 저장할 쿼리
//        String insertQuery = "INSERT INTO Recordings (user_id, recorded_data) VALUES (?, ?)";
//
//        try (Connection conn = DatabaseConnection.getConnection()) {
//            // 연결 성공 시 쿼리 준비
//            try (PreparedStatement ps = conn.prepareStatement(insertQuery)) {
//                // 로그인된 사용자의 ID 가져오기
//                String loggedInUsername = Login.getLoggedInUsername();
//                if (loggedInUsername == null) {
//                    JOptionPane.showMessageDialog(null, "로그인되지 않았습니다.");
//                    return;
//                }
//
//                // 사용자의 ID를 user_id로 변환하는 로직 (아이디에 맞는 user_id 조회)
//                String getUserIdQuery = "SELECT user_id FROM users WHERE username = ?";
//                try (PreparedStatement pstmt = conn.prepareStatement(getUserIdQuery)) {
//                    pstmt.setString(1, loggedInUsername);
//                    ResultSet rs = pstmt.executeQuery();
//                    int userId = -1;
//                    if (rs.next()) {
//                        userId = rs.getInt("user_id");
//                    }
//
//                    // user_id가 없으면 종료
//                    if (userId == -1) {
//                        JOptionPane.showMessageDialog(null, "사용자 ID를 찾을 수 없습니다.");
//                        return;
//                    }
//
//                    // List<Character>를 String으로 변환하여 녹음된 키 데이터를 저장
//                    StringBuilder recordedData = new StringBuilder();
//                    for (Character key : keys) {
//                        recordedData.append(key);
//                    }
//
//                    // 파라미터 설정
//                    ps.setInt(1, userId); // user_id
//                    ps.setString(2, recordedData.toString()); // recorded_data (키 시퀀스)
//
//                    // 쿼리 실행
//                    ps.executeUpdate();
//                    JOptionPane.showMessageDialog(null, "녹음이 DB에 저장되었습니다.");
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        } catch (SQLException e) {
//            System.out.println("DB 연결 실패: " + e.getMessage());
//            JOptionPane.showMessageDialog(null, "DB 연결 실패. 다시 시도해주세요.");
//        }
//    }
}
