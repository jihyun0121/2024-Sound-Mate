package src;

import src.db.DatabaseConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame {
    public static void main(String[] args) {
        new Login();
    }

    public Login() {
        JFrame f = new JFrame("로그인 창");

        JLabel l1 = new JLabel("아이디 : ");    // 아이디 라벨
        l1.setBounds(20,20,80,30);
        JTextField id = new JTextField();         // 아이디 입력창
        id.setBounds(100,20,100,30);

        JLabel l2 = new JLabel("비밀번호 : ");      // 비밀번호 라벨
        l2.setBounds(20,75,80,30);
        JPasswordField pw = new JPasswordField(); // 비밀번호 입력창
        pw.setBounds(100,75,100,30);

        JButton btn = new JButton("로그인");
        btn.setBounds(100,120,80,30);

        // 버튼 클릭 이벤트
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = id.getText();          // 입력된 아이디
                String password = String.valueOf(pw.getPassword()); // 입력된 비밀번호

                if (authenticateUser(username, password)) {
                    JOptionPane.showMessageDialog(f, "로그인 성공!");
                } else {
                    JOptionPane.showMessageDialog(f, "로그인 실패. 아이디 또는 비밀번호를 확인하세요.");
                }
            }
        });

        f.add(l1);  f.add(id);
        f.add(l2);  f.add(pw);  f.add(btn);
        f.setSize(1280,720);
        f.setLayout(null);
        f.setLocationRelativeTo(null);       // 창을 가운데에 띄움
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    // 사용자 인증 메서드
    private boolean authenticateUser(String username, String password) {
        boolean isAuthenticated = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();
            isAuthenticated = rs.next(); // 결과가 있으면 로그인 성공
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isAuthenticated;
    }
}
