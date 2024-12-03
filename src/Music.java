package src;

public class Music {
    private String title;   // 곡 이름
    private String filePath; // 음악 파일 경로
    private String level;    // 난이도
    private String imagePath; // 곡 이미지 파일 경로
    private String instrument; // 선택된 악기
    private String noteFilePath; // 노트 파일 경로

    public Music(String title, String filePath, String level, String imagePath, String noteFilePath) {
        this.title = title;
        this.filePath = filePath;
        this.level = level;
        this.imagePath = imagePath;
        this.instrument = null; // 기본값은 null
        this.noteFilePath = noteFilePath;
    }

    public String getTitle() {
        return title;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getLevel() {
        return level;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getNoteFilePath() {
        return noteFilePath;
    }
}
