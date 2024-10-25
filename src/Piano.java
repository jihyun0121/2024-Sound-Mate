package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Piano extends JFrame
{
//    public static void main(String[] args) {
//        new src.piano();
//    }

    private JPanel sheetMusicPanel;
    private JPanel pianoPanel;
    private JButton playButton;
    private JButton pauseButton;
    private JButton recordButton;

    public Piano() {
        setTitle("Sound Mate - Piano");         // super("Sound Mate"); 같은 기능
        this.setSize(1280,720);
        this.setLocationRelativeTo(null);       // 창을 가운데에 띄움
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        // 악보 패널
        sheetMusicPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // 간단한 악보 그리기
                g.drawLine(10, 50, 750, 50); // 오선
                g.drawLine(10, 70, 750, 70);
                g.drawLine(10, 90, 750, 90);
                g.drawLine(10, 110, 750, 110);
                g.drawLine(10, 130, 750, 130);
                // 샘플 음표
                g.fillOval(100, 100, 20, 20);
                g.drawLine(120, 110, 120, 50); // 음표 꼬리
            }
        };
        sheetMusicPanel.setPreferredSize(new Dimension(800, 150));
        add(sheetMusicPanel, BorderLayout.NORTH);

        // 피아노 키보드 패널 (이미지로 대체 가능)
        pianoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon pianoImage = new ImageIcon("piano_image.png"); // 피아노 이미지 경로 설정
                g.drawImage(pianoImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        pianoPanel.setPreferredSize(new Dimension(800, 150));
        add(pianoPanel, BorderLayout.CENTER);

        // 컨트롤 버튼 패널
        JPanel controlPanel = new JPanel();
        playButton = new JButton("Play");
        pauseButton = new JButton("Pause");
        recordButton = new JButton("Record");

        controlPanel.add(playButton);
        controlPanel.add(pauseButton);
        controlPanel.add(recordButton);
        add(controlPanel, BorderLayout.SOUTH);

        // 버튼 이벤트 처리
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Play 기능 구현
                System.out.println("Play clicked");
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pause 기능 구현
                System.out.println("Pause clicked");
            }
        });

        recordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Record 기능 구현
                System.out.println("Record clicked");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Piano().setVisible(true);
            }
        });

    }
}
