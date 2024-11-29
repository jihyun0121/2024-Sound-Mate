package src;

import src.db.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Signup extends JFrame {
    public static void main(String[] args) {
        new Signup();
    }

    public Signup() {
        JFrame f = new JFrame("Sound Mate - Register");
        f.setSize(1280, 720);
        f.getContentPane().setBackground(Color.white);
        f.setLocationRelativeTo(null);       // 창을 화면 가운데에 위치
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 패널 설정
        JPanel panel = new JPanel();
        panel.setLayout(null);  // 레이아웃을 null로 설정
        panel.setBackground(Color.white);

        JLabel l1 = new JLabel("아이디 : ");    // 아이디 라벨
        l1.setBounds(520, 220, 100, 30);
        JTextField id = new JTextField();       // 아이디 입력창
        id.setBounds(620, 220, 150, 30);

        JLabel l2 = new JLabel("비밀번호 : ");   // 비밀번호 라벨
        l2.setBounds(520, 260, 100, 30);
        JPasswordField pw = new JPasswordField(); // 비밀번호 입력창
        pw.setBounds(620, 260, 150, 30);

        JLabel l3 = new JLabel("비밀번호 확인 : "); // 비밀번호 확인 라벨
        l3.setBounds(520, 300, 100, 30);
        JPasswordField pwConfirm = new JPasswordField(); // 비밀번호 확인 입력창
        pwConfirm.setBounds(620, 300, 150, 30);

        JButton btn = new JButton("회원가입");
        btn.setBounds(620, 340, 150, 30);

        // 버튼 클릭 이벤트
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = id.getText();
                String password = String.valueOf(pw.getPassword());
                String confirmPassword = String.valueOf(pwConfirm.getPassword());

                if (password.equals(confirmPassword)) {
                    if (registerUser(username, password)) {
                        JOptionPane.showMessageDialog(f, "회원가입 성공!");
                        f.dispose(); // 회원가입 창 닫기
                        new Login(); // 로그인 창 열기
                    } else {
                        JOptionPane.showMessageDialog(f, "회원가입 실패. 아이디가 이미 존재합니다.");
                    }
                } else {
                    JOptionPane.showMessageDialog(f, "비밀번호가 일치하지 않습니다.");
                }
            }
        });

        // 패널에 컴포넌트 추가
        panel.add(l1);  panel.add(id);
        panel.add(l2);  panel.add(pw);
        panel.add(l3);  panel.add(pwConfirm);
        panel.add(btn);

        // 프레임에 패널 추가
        f.add(panel);
        f.setVisible(true);
    }

    // 회원가입 메서드
    private boolean registerUser(String username, String password) {
        boolean isRegistered = false;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            int rowsInserted = pstmt.executeUpdate();
            isRegistered = rowsInserted > 0; // 삽입된 행이 있으면 성공
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isRegistered;
    }
}
