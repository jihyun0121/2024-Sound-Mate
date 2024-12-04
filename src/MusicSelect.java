package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicSelect extends JPanel {
    private MusicTrack musicManager;

    public MusicSelect(JFrame frame) {
        musicManager = new MusicTrack();
        setLayout(new GridLayout(0, 1)); // 곡을 세로로 나열
        setBackground(Color.WHITE);

        for (Music music : musicManager.getMusic()) {
            JButton musicButton = new JButton(music.getTitle());
            musicButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 선택된 곡에 맞는 게임 화면으로 이동
                    startGame(frame, music);
                }
            });
            add(musicButton);
        }
    }

    private void startGame(JFrame frame, Music music) {
        frame.getContentPane().removeAll();
        frame.add(new PianoGame(music)); // 선택된 곡으로 게임 실행
        frame.revalidate();
        frame.repaint();
    }
}
