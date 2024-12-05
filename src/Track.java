package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Track extends JPanel {
    private List<Note> notes;
    private List<Note> pendingNotes; // 아직 화면에 추가되지 않은 노트
    private long startTime;
    private int score;
    private Map<Integer, Character> keyMapping; // 키 매핑
    private gameBar gameBarPanel; // 점수 업데이트를 위한 gameBar 객체

    public Track(Map<Integer, Character> keyMapping, gameBar gameBarPanel) {
        notes = new ArrayList<>();
        pendingNotes = new ArrayList<>();
        this.keyMapping = keyMapping;
        this.gameBarPanel = gameBarPanel;
        setLayout(null);
        setBackground(Color.BLACK);
        score = 0;
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
                if (note.getTime() <= elapsedTime) {
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
            note.moveDown(10); // 노트 이동

            // 노트가 화면을 벗어나면 비활성화
            if (!note.isActive()) {
                this.remove(note);
                iterator.remove();
            }
        }
    }

    public void keyPressed(char key) {
        Iterator<Note> iterator = notes.iterator();
        while (iterator.hasNext()) {
            Note note = iterator.next();
            if (note.isActive() && note.getKey() == key) {
                // 노트가 라인에 도달했을 때만 점수 증가
                if (note.isLineReached()) {
                    score += 100; // 점수 증가 (적절한 점수로 수정 가능)
                    gameBarPanel.setScore(score); // 점수 업데이트
                }
                this.remove(note);
                iterator.remove();
                break;  // 한 번에 하나의 노트만 처리
            }
        }
    }

    public int getScore() {
        return score;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Note note : notes) {
            note.paintComponent(g);
        }
    }
}
