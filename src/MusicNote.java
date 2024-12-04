package src;

public class MusicNote {
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
