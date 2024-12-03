package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class MusicSelect extends JPanel {
    private MusicTrack musicManager;
    private Map<Integer, Character> pianoKeys;
    private Map<Integer, Character> drumKeys;
    private Map<Integer, Character> guitarKeys;

    public MusicSelect(JFrame frame, Map<Integer, Character> pianoKeys, Map<Integer, Character> drumKeys, Map<Integer, Character> guitarKeys) {
        this.musicManager = new MusicTrack();
        this.pianoKeys = pianoKeys;
        this.drumKeys = drumKeys;
        this.guitarKeys = guitarKeys;

        setLayout(new GridLayout(0, 1)); // 곡을 세로로 나열
        setBackground(Color.WHITE);

        for (Music music : musicManager.getMusic()) {
            JButton pianoButton = new JButton("Piano: " + music.getTitle());
            JButton drumButton = new JButton("Drum: " + music.getTitle());
            JButton guitarButton = new JButton("Guitar: " + music.getTitle());

            pianoButton.addActionListener(e -> startGame(frame, music, pianoKeys));
            drumButton.addActionListener(e -> startGame(frame, music, drumKeys));
            guitarButton.addActionListener(e -> startGame(frame, music, guitarKeys));

            add(pianoButton);
            add(drumButton);
            add(guitarButton);
        }
    }

    private void startGame(JFrame frame, Music music, Map<Integer, Character> keyMapping) {
        frame.getContentPane().removeAll();
        frame.add(new PianoGame(music, keyMapping)); // 선택된 곡과 키 매핑으로 게임 실행
        frame.revalidate();
        frame.repaint();
    }
}