package src;

import javax.swing.*;
import java.awt.*;

public class Note extends JPanel {
    private int xPosition; // 노트의 x 좌표
    private int yPosition; // 노트의 y 좌표
    private boolean active; // 노트가 활성 상태인지 여부
    private int time; // 노트의 등장 시간 (밀리초)
    private char key; // 노트와 연결된 키
    private String instrumentType; // 악기 타입

    public Note(int xPosition, int time, char key, String instrumentType) {
        this.xPosition = xPosition;
        this.yPosition = 0;
        this.time = time;
        this.active = true;
        this.key = key;
        this.instrumentType = instrumentType;

        // 악기별 크기 설정
        int width, height;
        switch (instrumentType.toLowerCase()) {
            case "piano":
                width = 128;
                height = 50;
                break;
            case "guitar":
                width = 114;
                height = 40;
                break;
            case "drum":
                width = 157;
                height = 80;
                break;
            default:
                width = 130; // 기본 크기
                height = 50;
                break;
        }

        setBounds(xPosition, yPosition, width, height); // 크기와 초기 위치 설정
    }

    public void moveDown(int speed) {
        yPosition += speed; // 일정 속도로 이동
        setLocation(xPosition, yPosition);
        if (yPosition > 720) { // 화면 아래로 벗어나면 비활성화
            active = false;
        }
    }

    public boolean isActive() {
        return active;
    }

    public int getTime() {
        return time; // 등장 시간 반환
    }

    public char getKey() {
        return key;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
