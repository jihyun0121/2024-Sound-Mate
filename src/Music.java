package src;

public class Music {
    private String title;   // 곡 이름
    private String filePath; // 음악 파일 경로
    private String level; // 음악 파일 경로

    public Music(String title, String filePath, String level) {
        this.title = title;
        this.filePath = filePath;
        this.level = filePath;
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
}
