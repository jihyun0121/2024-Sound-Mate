package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.*;

public class Piano extends JPanel {
    //private ControlInterface controlInterface;
    private Map<Character, JLabel> tileMap;         // 키와 타일을 매핑할 Map
    private Map<Character, Map<String, String>> tileImages; // 타일별 이미지 세트

    public Piano() {
        setLayout(new GridLayout(1, 10));

        tileMap = new HashMap<>();
        tileImages = new HashMap<>();

        // 타일 생성 및 초기화
        for (char key : new char[]{'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', ';'}) {
            // 각 키에 대한 이미지 세트 설정
            Map<String, String> images = new HashMap<>();
            images.put("default", "src/img/PB-" + key + ".png");    // 기본 이미지
            images.put("pressed", "src/img/PC-" + key + ".png");    // 눌렸을 때 이미지
            tileImages.put(key, images);    // 각 키에 대한 이미지 세트

            // 타일 생성 및 기본 이미지 설정
            JLabel tile = new JLabel(new ImageIcon(images.get("default")));     // 각 키의 기본 이미지 설정
            tile.setHorizontalAlignment(JLabel.CENTER);
            tile.setVerticalAlignment(JLabel.CENTER);
            add(tile);                  // 패널에 타일 추가
            tileMap.put(key, tile);     // Map에 키와 타일
        }

        // 키보드 리스너 설정
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char keyChar = e.getKeyChar();
                updateTileImage(keyChar, "pressed");    // 키가 눌렸을 때
                playSound(keyChar); // 피아노 소리 재생
                //PianoInterface.this.controlInterface.recordKey(keyChar);          // 녹음 중이면 키 저장
            }
            @Override
            public void keyReleased(KeyEvent e) {
                updateTileImage(e.getKeyChar(), "default");     // 키가 떼어졌을 때 기본 이미지로 변경
            }
        });

        setFocusable(true);  // 키 입력을 받을 수 있도록 설정
    }

    private void updateTileImage(char keyChar, String state) {
        // 해당 키가 타일 맵과 이미지 맵에 존재하는지 확인
        if (tileMap.containsKey(keyChar) && tileImages.containsKey(keyChar)) {
            JLabel tile = tileMap.get(keyChar);
            String imagePath = tileImages.get(keyChar).get(state);

            // 이미지 경로가 null이 아닌 경우에만 업데이트
            if (imagePath != null) {
                tile.setIcon(new ImageIcon(imagePath));
            }
        } else {
            System.out.println("Key not mapped or image path missing for key: " + keyChar);
        }
    }

    private void playSound(char keyChar) {
        String soundPath = "src/sounds/" + keyChar + ".wav";
        try {
            File soundFile = new File(soundPath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error playing sound for key: " + keyChar);
            e.printStackTrace();
        }
    }


}
