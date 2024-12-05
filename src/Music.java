package src;

public class Music {
    private String title;
    private String filePath;
    private String level;
    private String imagePath;
    private String instrument;
    private String pianoNoteFilePath; // 피아노 노트 경로
    private String drumNoteFilePath;  // 드럼 노트 경로
    private String guitarNoteFilePath; // 기타 노트 경로

    public Music(String title, String filePath, String level, String imagePath,
                 String pianoNoteFilePath, String drumNoteFilePath, String guitarNoteFilePath) {
        this.title = title;
        this.filePath = filePath;
        this.level = level;
        this.imagePath = imagePath;
        this.pianoNoteFilePath = pianoNoteFilePath;
        this.drumNoteFilePath = drumNoteFilePath;
        this.guitarNoteFilePath = guitarNoteFilePath;
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

    public String getPianoNoteFilePath() {
        return pianoNoteFilePath;
    }

    public String getDrumNoteFilePath() {
        return drumNoteFilePath;
    }

    public String getGuitarNoteFilePath() {
        return guitarNoteFilePath;
    }
}
