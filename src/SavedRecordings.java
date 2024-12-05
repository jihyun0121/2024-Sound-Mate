package src;

import src.db.DatabaseConnection;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SavedRecordings extends JFrame {
    private JTable savedRecordingsTable;
    private JButton homeButton;
    private JPanel panel;
    private String currentInstrumentType;

    // 재생 버튼 렌더러 및 에디터
    class PlayButtonRenderer extends DefaultTableCellRenderer {
        private JButton playButton;

        public PlayButtonRenderer() {
            playButton = new JButton("재생");
            playButton.setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            return playButton;
        }
    }

    class PlayButtonEditor extends DefaultCellEditor {
        private JButton playButton;
        private List<Character> recordedKeys = new ArrayList<>();  // 기본값으로 빈 리스트 초기화
        private String recordingName;

        public PlayButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            playButton = new JButton("재생");
            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playRecording();
                    stopCellEditing();
                }
            });
        }

        private void playRecording() {
            // recordedKeys가 비어있는지 확인
            if (recordedKeys == null || recordedKeys.isEmpty()) {
                JOptionPane.showMessageDialog(null, "재생할 녹음 데이터가 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            try {
                // 각 키에 대해 사운드 재생
                for (Character key : recordedKeys) {
                    String soundPath = getSoundPath(key);
                    playSound(soundPath);
                    // 키 사이에 약간의 딜레이 추가 (필요에 따라 조절 가능)
                    Thread.sleep(300);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "재생 중 오류 발생", "오류", JOptionPane.ERROR_MESSAGE);
            }
        }

        private String getSoundPath(Character key) {
            // 악기 타입에 따라 사운드 경로 생성
            return String.format("src/sound/%s/%s.wav",
                    currentInstrumentType.toLowerCase(), key);
        }

        private void playSound(String soundPath) {
            try {
                File soundFile = new File(soundPath);
                if (!soundFile.exists()) {
                    System.out.println("Sound file not found: " + soundPath);
                    return;
                }

                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            // recordedKeys 초기화
            recordedKeys.clear();

            // 현재 선택된 행의 데이터 가져오기
            recordingName = (String) table.getValueAt(row, 0);
            currentInstrumentType = (String) table.getValueAt(row, 1);

            // 선택된 녹음의 키 값 가져오기
            try {
                recordedKeys = DatabaseConnection.getRecordedKeys(
                        Login.getLoggedInUsername(),
                        recordingName,
                        currentInstrumentType
                );
                // recordedKeys가 null이라면 빈 리스트로 설정
                if (recordedKeys == null) {
                    recordedKeys = new ArrayList<>();
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "녹음 데이터를 불러올 수 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            }

            return playButton;
        }

        @Override
        public Object getCellEditorValue() {
            return "재생";
        }
    }

    public SavedRecordings() {
        setTitle("Sound Mate - My playlist");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panel = new JPanel(new BorderLayout());

        homeButton = new JButton(new ImageIcon("src/img/home.png"));
        homeButton.setBorderPainted(false); // 버튼 테두리 없애기
        homeButton.setContentAreaFilled(false); // 버튼 배경 없애기
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainApp(Login.getLoggedInUsername());
            }
        });

        JPanel homePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        homePanel.add(homeButton);
        panel.add(homePanel, BorderLayout.NORTH);

        // 컬럼 헤더
        String[] columns = {"제목", "악기 종류", "재생"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        savedRecordingsTable = new JTable(model);

        // 테이블 크기 조정
        savedRecordingsTable.setPreferredScrollableViewportSize(new Dimension(1000, 500)); // 테이블 크기 설정
        savedRecordingsTable.setFillsViewportHeight(true); // 화면 꽉 차게 설정

        // 테이블 행 간격 조정
        savedRecordingsTable.setRowHeight(40); // 행 높이 설정

        // 재생 버튼 컬럼 설정
        savedRecordingsTable.getColumn("재생").setCellRenderer(new PlayButtonRenderer());
        savedRecordingsTable.getColumn("재생").setCellEditor(new PlayButtonEditor(new JCheckBox()));

        // 저장된 녹음 데이터 로딩
        loadSavedRecordings(model);

        // 테이블 스크롤 팬에 추가
        JScrollPane scrollPane = new JScrollPane(savedRecordingsTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(homeButton, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    // 저장된 녹음 데이터 로딩
    private void loadSavedRecordings(DefaultTableModel model) {
        try {
            // DatabaseConnection에서 현재 로그인된 사용자의 녹음 가져오기
            List<Recording> recordings = DatabaseConnection.getSavedRecordings(Login.getLoggedInUsername());

            // 테이블 모델에 데이터 추가
            for (Recording recording : recordings) {
                model.addRow(new Object[]{
                        recording.getName(),
                        recording.getInstrumentType(),
                        "재생"
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "저장된 녹음 데이터를 불러오는 데 오류가 발생했습니다.",
                    "오류",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // 녹음 정보를 저장할 내부 클래스
    public static class Recording {
        private String name;
        private String instrumentType;

        public Recording(String name, String instrumentType) {
            this.name = name;
            this.instrumentType = instrumentType;
        }

        public String getName() { return name; }
        public String getInstrumentType() { return instrumentType; }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SavedRecordings();
        });
    }
}