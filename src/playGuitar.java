package src;

import javax.swing.*;
import java.awt.*;

public class playGuitar extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new playGuitar().setVisible(true));
    }

    public playGuitar() {
        setTitle("Sound Mate - Play Guitar");         // super("Sound Mate"); 같은 기능
        this.setSize(1280, 720);
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);       // 창을 가운데에 띄움
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        // 악보 패널
        SheetMusicPanel sheetMusicPanel = new SheetMusicPanel();
        sheetMusicPanel.setPreferredSize(new Dimension(1190, 100));
        sheetMusicPanel.setBackground(Color.WHITE);
        this.add(sheetMusicPanel, BorderLayout.EAST);

        // 기타 자판 패널
        Guitar guitar = new Guitar(sheetMusicPanel);
        this.add(guitar, BorderLayout.SOUTH);

        // 컨트롤러 패널
        menuBar menuBar = new menuBar(guitar);
        this.add(menuBar, BorderLayout.NORTH);

        // 키보드 포커스를 Guitar에 설정
        guitar.setFocusable(true);
        guitar.requestFocusInWindow();
    }
}