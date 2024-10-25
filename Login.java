import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Login extends JFrame
{
    public static void main(String[] args)
    {
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
        JPasswordField pw = new JPasswordField();
        pw.setBounds(100,75,100,30);

        JButton btn = new JButton("로그인");
        btn.setBounds(100,120,80,30);

        f.add(l1);  f.add(id);
        f.add(l2);  f.add(pw);  f.add(btn);
        f.setSize(1280,720);
        f.setLayout(null);
        f.setLocationRelativeTo(null);       // 창을 가운데에 띄움
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}