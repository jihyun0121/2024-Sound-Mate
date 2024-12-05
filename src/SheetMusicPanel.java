package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SheetMusicPanel extends JPanel {
    private List<MusicNote> notes;

    public SheetMusicPanel() {
        notes = new ArrayList<>();
        setBackground(Color.WHITE);
    }

    public void addNoteToSheet(char keyChar) {
        // 음표 이미지 경로 설정
        String noteImage;
        int yPosition;

        try {
            if ("asdfgh".indexOf(keyChar) != -1) {
                noteImage = "/img/row-note.png"; // 낮은 음표 이미지
                yPosition = 100 + "asdfgh".indexOf(keyChar) * 20; // 적절한 Y 위치 계산
            } else if ("jkl;".indexOf(keyChar) != -1) {
                noteImage = "/img/high-note.png"; // 높은 음표 이미지
                yPosition = 200 + "jkl;".indexOf(keyChar) * 20;
            } else {
                System.out.println("Invalid key: " + keyChar);
                return; // 유효하지 않은 키는 무시
            }

            MusicNote musicNote = new MusicNote(yPosition, noteImage);
            notes.add(musicNote);
            repaint(); // 음표를 추가한 후 다시 그리기
        } catch (Exception e) {
            System.err.println("음표 추가 중 오류 발생: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 악보 배경 그리기
        try {
            ImageIcon sheetMusic = new ImageIcon("src/img/오선지.png");
            g.drawImage(sheetMusic.getImage(), 0, 0, getWidth(), getHeight(), this);
        } catch (Exception e) {
            System.out.println("오선지 이미지 로드 실패: " + e.getMessage());
        }

        // 음표 출력하기
        try {
            int noteSpacing = 50; // 음표 간 간격
            int xPosition = 0;

            for (int i = 0; i < notes.size(); i++) {
                MusicNote musicNote = notes.get(i);
                ImageIcon noteImage = new ImageIcon(musicNote.getImagePath());

                if (noteImage.getImage() == null) {
                    throw new Exception("음표 이미지를 찾을 수 없습니다: " + musicNote.getImagePath());
                }

                // x 좌표: 음표 간 간격 * 순서
                xPosition = i * noteSpacing;

                // y 좌표: 패널 높이를 기준으로 음표 레벨 설정
                int yPosition = getHeight() - musicNote.getYPosition();

                g.drawImage(noteImage.getImage(), xPosition, yPosition, this);
            }
        } catch (Exception e) {
            System.err.println("음표 렌더링 중 오류 발생: " + e.getMessage());
        }

    }
}

class MusicNote {
    private static int yPosition;
    private static String imagePath;

    public MusicNote(int yPosition, String imagePath) {
        this.yPosition = yPosition;
        this.imagePath = imagePath;
    }

    public static int getYPosition() {
        return yPosition;
    }

    public static String getImagePath() {
        return imagePath;
    }
}