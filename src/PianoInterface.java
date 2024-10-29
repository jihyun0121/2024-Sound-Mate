package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class PianoInterface extends JPanel {
    private Map<Character, JLabel> tileMap;         // 키와 타일을 매핑할 Map
    private Map<Character, Map<String, String>> tileImages; // 타일별 이미지 세트

    public PianoInterface() {
        setLayout(new GridLayout(1, 10));

        tileMap = new HashMap<>();
        tileImages = new HashMap<>();

        // 타일 생성 및 초기화
        for (char key : new char[]{'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', ';'}) {
            // 각 키에 대한 이미지 세트 설정
            Map<String, String> images = new HashMap<>();
            images.put("default", "src/img/PB-" + key + ".png");    // 기본 이미지
            images.put("pressed", "src/img/PC-" + key + ".png");    // 눌렸을 때 이미지
            tileImages.put(key, images);    // 각 키에 대해 이미지 세트 매핑

            // 타일 생성 및 기본 이미지 설정
            JLabel tile = new JLabel(new ImageIcon(images.get("default")));     // 각 키의 기본 이미지 설정
            tile.setHorizontalAlignment(JLabel.CENTER);
            tile.setVerticalAlignment(JLabel.CENTER);
            add(tile);      // 패널에 타일 추가
            tileMap.put(key, tile);     // Map에 키와 타일 매핑
        }

        // 키보드 리스너 설정
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                updateTileImage(e.getKeyChar(), "pressed"); // 키가 눌렸을 때
            }

            @Override
            public void keyReleased(KeyEvent e) {
                updateTileImage(e.getKeyChar(), "default"); // 키가 떼어졌을 때 기본 이미지로 변경
            }
        });

        setFocusable(true);  // 키 입력을 받을 수 있도록 설정
    }

    private void updateTileImage(char keyChar, String state) {
        JLabel tile = tileMap.get(keyChar);
        if (tile != null && tileImages.containsKey(keyChar)) {
            String imagePath = tileImages.get(keyChar).get(state); // 상태별 이미지 경로 가져오기
            if (imagePath != null) {
                tile.setIcon(new ImageIcon(imagePath));
            }
        }
    }

}
