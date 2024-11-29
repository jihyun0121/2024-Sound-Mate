package src;

import javax.swing.*;
import java.awt.*;

public class DrumGame extends JFrame
{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DrumGame().setVisible(true);
            }
        });
    }

    private JPanel sheetMusicPanel;

    public DrumGame() {
        setTitle("Sound Mate - Game_Drum");         // super("Sound Mate"); 같은 기능
        this.setSize(1280, 720);
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);       // 창을 가운데에 띄움
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        // 컨트롤러 패널
        gameBar gameBar = new gameBar();
        this.add(gameBar, BorderLayout.NORTH);

        // 피아노 키보드 패널
        Drum drum = new Drum();
        //drumInterface drumInterface = new drumInterface(controlInterface);
        this.add(drum, BorderLayout.SOUTH);

        // 키보드 포커스를 drumInterface에 설정
        drum.requestFocusInWindow();


    }

}
