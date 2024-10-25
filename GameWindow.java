import java.awt.*;
import javax.swing.*;

public class GameWindow extends JFrame {
    public GameWindow() {
        setTitle("Rhythm Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
//        contentPane.setBackground(Color.ORANGE);
        contentPane.setLayout(new FlowLayout());

        contentPane.add(new JButton("반짝반짝 작은 별"));
        contentPane.add(new JButton("여수 밤바다 - 버스커 버스커"));
        contentPane.add(new JButton("Welcom To The Show - Day6"));

        setSize(1280, 720);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GameWindow();
    }
}
