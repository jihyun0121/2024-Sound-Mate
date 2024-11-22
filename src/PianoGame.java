package src;

import javax.swing.*;
import java.awt.*;

public class PianoGame extends JFrame
{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PianoGame().setVisible(true);
            }
        });
    }

    private JPanel sheetMusicPanel;

    public PianoGame() {
        setTitle("Sound Mate - Game_Piano");         // super("Sound Mate"); 같은 기능
        this.setSize(1280, 720);
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);       // 창을 가운데에 띄움
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        // 컨트롤러 패널
        src.gameBar gameBar = new gameBar();
        this.add(gameBar, BorderLayout.NORTH);

        // 피아노 키보드 패널
        Piano piano = new Piano();
        //PianoInterface pianoInterface = new PianoInterface(controlInterface);
        this.add(piano, BorderLayout.SOUTH);

        // 키보드 포커스를 PianoInterface에 설정
        piano.requestFocusInWindow();


    }

}
