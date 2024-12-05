package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Track extends JPanel {
    private static final int LINE_Y = 265; // 라인의 Y 좌표 고정
    private List<Note> notes;
    private List<Note> pendingNotes;
    private long startTime;
    private int score;
    private int combo;
    private int maxCombo; // 최대 콤보 추가
    private Map<Integer, Character> keyMapping;
    private gameBar gameBarPanel;

    private ImageIcon lineImage; // 라인 이미지
    private Timer gameTimer; // 타이머 객체
    private boolean gameOver; // 게임 종료 플래그

    public Track(Map<Integer, Character> keyMapping, gameBar gameBarPanel) {
        notes = new ArrayList<>();
        pendingNotes = new ArrayList<>();
        this.keyMapping = keyMapping;
        this.gameBarPanel = gameBarPanel;
        setLayout(null);
        setBackground(Color.BLACK);
        score = 0;
        combo = 0;
        maxCombo = 0;
        gameOver = false; // 초기값 false

        // 라인 이미지 로드
        lineImage = new ImageIcon("src/img/Line.png");
    }

    public void generateNotes(List<Note> noteTimingData) {
        pendingNotes.addAll(noteTimingData);
    }

    public void startGame() {
        startTime = System.currentTimeMillis();

        // 60FPS로 게임 루프 실행
        gameTimer = new Timer(16, e -> {
            if (!gameOver) { // 게임이 종료되지 않았을 때만 실행
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
                checkGameOver(); // 모든 노트 소진 여부 확인
            }
        });
        gameTimer.start();
    }


    private void moveNotes() {
        Iterator<Note> iterator = notes.iterator();
        while (iterator.hasNext()) {
            Note note = iterator.next();
            note.moveDown(10); // 노트 이동

            // 노트가 화면을 벗어났으면 제거 및 콤보 초기화
            if (!note.isActive()) {
                combo = 0; // 콤보 초기화
                gameBarPanel.setCombo(combo); // 콤보 업데이트

                // 노트 삭제 및 UI 업데이트
                this.remove(note);
                revalidate();
                repaint();

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

                // 라인 범위 조정 (위쪽과 아래쪽 확대)
                if (yPos >= LINE_Y - 80 && yPos <= LINE_Y + 70) {
                    long currentTime = System.currentTimeMillis();
                    long diff = Math.abs(currentTime - note.getReachTime());

                    // 판정 로직 (범위 조정)
                    if (diff <= 100) { // Perfect
                        score += 300;
                        combo++;
                        gameBarPanel.setFeedback("Perfect!");
                    } else if (diff <= 200) { // Great
                        score += 200;
                        combo++;
                        gameBarPanel.setFeedback("Great!");
                    } else if (diff <= 300) { // Good
                        score += 150;
                        combo++;
                        gameBarPanel.setFeedback("Good!");
                    } else { // Miss
                        combo = 0;
                        gameBarPanel.setFeedback("Miss!");
                    }

                    // 최대 콤보 갱신
                    maxCombo = Math.max(maxCombo, combo);

                    // 점수와 콤보 업데이트
                    gameBarPanel.setScore(score);
                    gameBarPanel.setCombo(combo);

                    // 노트 제거
                    this.remove(note);
                    iterator.remove();
                    break; // 한 번에 하나의 노트만 처리
                } else {
                    // 라인 범위 밖에서 키를 누른 경우
                    combo = 0; // 콤보 초기화
                    gameBarPanel.setFeedback("Miss!");
                }
            }
        }
    }

    private boolean allNotesFinished() {
        return notes.isEmpty() && pendingNotes.isEmpty(); // 남은 노트가 없는지 확인
    }

    private void checkGameOver() {
        if (allNotesFinished() && !gameOver) {
            gameOver = true; // 게임 종료 플래그 설정
            gameTimer.stop(); // 타이머 멈춤

            SwingUtilities.invokeLater(() -> {
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                parentFrame.dispose(); // 현재 게임 화면 닫기
                new GameOverScreen(score, maxCombo, parentFrame.getTitle()).setVisible(true);
            });
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // 라인 이미지 그리기
        int lineWidth = lineImage.getIconWidth();
        int lineHeight = lineImage.getIconHeight();
        int xPosition = (getWidth() - lineWidth) / 2; // 라인 X 좌표 (중앙 정렬)
        int yPosition = LINE_Y - (lineHeight / 2);   // 라인 Y 좌표 (중앙에 위치하도록 조정)
        g2d.drawImage(lineImage.getImage(), xPosition, yPosition, lineWidth, lineHeight, this);

        // 노트 그리기
        for (Note note : notes) {
            note.paintComponent(g);
        }
    }
}