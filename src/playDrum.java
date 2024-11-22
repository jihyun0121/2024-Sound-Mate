package src;

import javax.swing.*;
import java.awt.*;

public class playDrum extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new playDrum().setVisible(true);
            }
        });
    }

    private JPanel sheetMusicPanel;

    public playDrum() {
        setTitle("Sound Mate - Play Drum");         // super("Sound Mate"); 같은 기능
        this.setSize(1280, 720);
        this.getContentPane().setBackground(Color.white);
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
                g.drawImage(sheetMusic.getImage(), 0, 0, this);
            }
        };
        sheetMusicPanel.setPreferredSize(new Dimension(1191, 218));
        sheetMusicPanel.setBackground(Color.WHITE);
        add(sheetMusicPanel, BorderLayout.EAST);


        // 드럼 패널
        Drum drum = new Drum();
        this.add(drum, BorderLayout.SOUTH);

        // 키보드 포커스를 Drum에 설정
        drum.requestFocusInWindow();
    }
}
