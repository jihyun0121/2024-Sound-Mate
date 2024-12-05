package src;

import javax.swing.*;
import java.awt.*;

public class playDrum extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new playDrum().setVisible(true));
    }

    public playDrum() {
        setTitle("Sound Mate - Play Drum");         // super("Sound Mate"); 같은 기능
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

        // 드럼 패널
        Drum drum = new Drum(sheetMusicPanel);
        this.add(drum, BorderLayout.SOUTH);

        // 컨트롤러 패널
        InstrumentPanel drumPanel = new Drum();  // 드럼
        menuBar menuBar = new menuBar(drum);
        this.add(menuBar, BorderLayout.NORTH);

        // 키보드 포커스를 Drum에 설정
        drum.setFocusable(true);
        drum.requestFocusInWindow();
    }
}
