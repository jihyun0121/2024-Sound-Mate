package src;

import javax.swing.*;
import java.awt.*;

public class Note extends JPanel {
    private int xPosition;
    private int yPosition;
    private boolean active;
    private int time;
    private char key; // 노트와 연결된 키

    public Note(int xPosition, int time, char key) {
        this.xPosition = xPosition;
        this.yPosition = 0;
        this.time = time;
        this.active = true;
        this.key = key;
        setSize(50, 50); // 노트 크기
    }

    public void moveDown(int speed) {
        yPosition += speed;
        if (yPosition > 700) {
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

    public int getTime() {
        return time;
    }

    public char getKey() {
        return key;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillOval(0, 0, getWidth(), getHeight());
    }
}
