package src;

import javax.swing.*;
import java.awt.*;

public class playGuitar extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new playGuitar().setVisible(true);
            }
        });
    }

    private JPanel sheetMusicPanel;

    public playGuitar() {
        setTitle("Sound Mate - Play Guitar");         // super("Sound Mate"); 같은 기능
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);       // 창을 가운데에 띄움
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());


        // 컨트롤러 패널
        src.menuBar menuBar = new menuBar();
        this.add(menuBar, BorderLayout.NORTH);


        // 악보 패널
        sheetMusicPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon sheetMusic = new ImageIcon("src/img/오선지.png");
                g.drawImage(sheetMusic.getImage(), 0, 20, this);
            }
        };
        sheetMusicPanel.setPreferredSize(new Dimension(1191, 218));
        add(sheetMusicPanel, BorderLayout.EAST);


        // 기타 자판 패널
        Guitar guitar = new Guitar();
        this.add(guitar, BorderLayout.SOUTH);

        // 키보드 포커스를 GuitarInterface에 설정
        guitar.requestFocusInWindow();
    }
}