package src;

import javax.swing.*;
import java.awt.*;

public class Note extends JPanel {
    private int xPosition; // 노트 X 위치
    private int yPosition; // 노트 Y 위치 (계속 변경됨)
    private boolean active; // 노트 활성화 여부

    public Note(int xPosition) {
        this.xPosition = xPosition;
        this.yPosition = 0; // 초기 Y 위치
        this.active = true;
        setSize(50, 50); // 노트 크기
    }

    public void moveDown() {
        yPosition += 5; // 떨어지는 속도
        if (yPosition > 700) { // 화면 끝에 도달 시 비활성화
            active = false;
        }
    }

    public boolean isActive() {
        return active;
    }

    public int getYPosition() {
        return yPosition;
    }

    public int getXPosition() {
        return xPosition;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE); // 노트 색상
        g.fillOval(0, 0, getWidth(), getHeight());
    }
}
