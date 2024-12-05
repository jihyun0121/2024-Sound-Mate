package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Track extends JPanel {
    private List<Note> notes;
    private List<Note> pendingNotes; // 아직 화면에 추가되지 않은 노트
    private long startTime;

    public Track() {
        notes = new ArrayList<>();
        pendingNotes = new ArrayList<>();
        setLayout(null);
        setBackground(Color.BLACK);
    }

    public void generateNotes(List<Note> noteTimingData) {
        pendingNotes.addAll(noteTimingData);
    }

    public void startGame() {
        startTime = System.currentTimeMillis();

        // 60FPS로 게임 루프 실행
        new Timer(16, e -> {
            long elapsedTime = System.currentTimeMillis() - startTime;

            // 시간에 따라 노트 추가
            Iterator<Note> iterator = pendingNotes.iterator();
            while (iterator.hasNext()) {
                Note note = iterator.next();
                if (note.getTime() <= elapsedTime) { // 등장 시간에 따라 추가
                    notes.add(note);
                    this.add(note);
                    iterator.remove();
                }
            }

            // 노트 이동 및 삭제
            moveNotes();
            repaint();
        }).start();
    }

    private void moveNotes() {
        Iterator<Note> iterator = notes.iterator();
        while (iterator.hasNext()) {
            Note note = iterator.next();
            note.moveDown(5); // 고정된 속도로 이동
            note.repaint();

            if (!note.isActive()) {
                this.remove(note);
                iterator.remove();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Note note : notes) {
            note.paintComponent(g);
        }
    }
}
