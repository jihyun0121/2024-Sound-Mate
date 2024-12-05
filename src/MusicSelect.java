package src;

import javax.swing.*;
import java.awt.*;
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

            pianoButton.addActionListener(e -> startGame(frame, music, pianoKeys, "P"));
            drumButton.addActionListener(e -> startGame(frame, music, drumKeys, "D"));
            guitarButton.addActionListener(e -> startGame(frame, music, guitarKeys, "G"));

            add(pianoButton);
            add(drumButton);
            add(guitarButton);
        }
    }

    private void startGame(JFrame frame, Music music, Map<Integer, Character> keyMapping, String instrumentCode) {
        frame.getContentPane().removeAll();

        // 노트 파일 경로 생성: 예) src/rhythm/P-LittleStar.txt
        String noteFilePath = "src/rhythm/" + instrumentCode + "-" + music.getTitle().replace(" ", "") + ".txt";

        switch (instrumentCode) {
            case "P" -> frame.add(new PianoGame(music, noteFilePath, keyMapping));
            case "D" -> frame.add(new DrumGame(music, noteFilePath, keyMapping));
            case "G" -> frame.add(new GuitarGame(music, noteFilePath, keyMapping));
            default -> throw new IllegalArgumentException("Invalid instrument: " + instrumentCode);
        }

        frame.revalidate();
        frame.repaint();
    }
}
