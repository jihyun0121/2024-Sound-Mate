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
    private int combo; // 콤보 변수 추가
    private Map<Integer, Character> keyMapping; // 키 매핑
    private gameBar gameBarPanel; // 점수 및 콤보 업데이트를 위한 gameBar 객체

    public Track(Map<Integer, Character> keyMapping, gameBar gameBarPanel) {
        notes = new ArrayList<>();
        pendingNotes = new ArrayList<>();
        this.keyMapping = keyMapping;
        this.gameBarPanel = gameBarPanel;
        setLayout(null);
        setBackground(Color.BLACK);
        score = 0;
        combo = 0; // 콤보 초기화
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

            // 노트가 라인을 지나쳤으면 콤보 초기화
            if (note.isLineReached() && note.isActive() && note.getY() > 720) {
                combo = 0; // 콤보 초기화
                gameBarPanel.setCombo(combo); // 콤보 업데이트
                this.remove(note);
                iterator.remove();
            }

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

            // 노트가 활성 상태이며, 키가 일치할 때 처리
            if (note.isActive() && note.getKey() == key) {
                int yPos = note.getY();

                // 라인에 도달했는지 확인
                if (yPos >= 650 && yPos <= 670) {
                    long currentTime = System.currentTimeMillis();
                    long diff = Math.abs(currentTime - note.getReachTime());

                    // 판정 로직
                    if (diff <= 50) {
                        score += 300; // Perfect
                        combo++;
                        gameBarPanel.setFeedback("Perfect!");
                    } else if (diff <= 100) {
                        score += 200; // Great
                        combo++;
                        gameBarPanel.setFeedback("Great!");
                    } else if (diff <= 200) {
                        score += 150; // Good
                        combo++;
                        gameBarPanel.setFeedback("Good!");
                    } else {
                        combo = 0; // Miss
                        gameBarPanel.setFeedback("Miss!");
                    }

                    // 점수와 콤보 업데이트
                    gameBarPanel.setScore(score);
                    gameBarPanel.setCombo(combo);

                    // 노트 제거
                    this.remove(note);
                    iterator.remove();
                    break; // 한 번에 하나의 노트만 처리
                }
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
