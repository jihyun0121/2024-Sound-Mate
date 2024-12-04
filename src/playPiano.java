package src;

import javax.swing.*;
import java.awt.*;

public class playPiano extends JFrame
{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new playPiano().setVisible(true);
            }
        });
    }

    private JPanel sheetMusicPanel;

    public playPiano() {
        setTitle("Sound Mate - Play Piano");         // super("Sound Mate"); 같은 기능
        this.setSize(1280,720);
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


        // 피아노 키보드 패널
        Piano piano = new Piano();
        this.add(piano, BorderLayout.SOUTH);

        // 키보드 포커스를 PianoInterface에 설정
        piano.requestFocusInWindow();


//        // 컨트롤 버튼 패널
//        JPanel controlPanel = new JPanel();
//        playButton = new JButton("Play");
//        pauseButton = new JButton("Pause");
//        recordButton = new JButton("Record");
//
//        controlPanel.add(playButton);
//        controlPanel.add(pauseButton);
//        controlPanel.add(recordButton);
//        add(controlPanel, BorderLayout.SOUTH);
//
//        // 버튼 이벤트 처리
//        playButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Play 기능 구현
//                System.out.println("Play clicked");
//            }
//        });
//
//        pauseButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Pause 기능 구현
//                System.out.println("Pause clicked");
//            }
//        });
//
//        recordButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Record 기능 구현
//                System.out.println("Record clicked");
//            }
//        });
    }

}
