package src;

import javax.swing.*;
import java.awt.*;

public class menuBar extends JPanel {
    //private List<JButton> buttons = new ArrayList<>();
    Color background = new Color(0x002F47);

    public menuBar() {
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(400, 100));
        setBackground(background); // 남색 배경 설정
    }
//    private JButton recordButton;
//    private boolean isRecording;
//    private List<Character> recordedKeys; // 녹음된 키들을 저장할 리스트

//    public ControlInterface() {
//        setLayout(new FlowLayout());
//
//        // 녹음 상태 초기화
////        isRecording = false;
////        recordedKeys = new ArrayList<>();
//
//        // 녹음 버튼 생성
////        recordButton = new JButton("Record");
////        recordButton.addActionListener(e -> toggleRecording());
////        add(recordButton);
//    }

//    private void toggleRecording() {
//        isRecording = !isRecording; // 녹음 상태 전환
//        if (isRecording) {
//            recordButton.setText("Stop Recording");         // 버튼 텍스트 변경
//            recordedKeys.clear();                           // 녹음 시작 시 이전 녹음 데이터 초기화
//        } else {
//            recordButton.setText("Record");
//            System.out.println("Recorded keys: " + recordedKeys);   // 녹음된 키들 출력 (확인용)
//        }
//    }
//
//    public void recordKey(char key) {
//        if (isRecording) {
//            recordedKeys.add(key);      // 녹음 중일 때만 키를 저장
//        }
//    }
//
//    public boolean isRecording() {
//        return isRecording;
//    }
}

