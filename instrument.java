import javax.swing.*;

public class instrument extends JFrame
{
    public static void main(String[] args) {
        new instrument();
    }

    public instrument() {
        setTitle("Sound Mate 악기 연주");         // super("Sound Mate"); 같은 기능
        this.setSize(1280,720);
        this.setLocationRelativeTo(null);       // 창을 가운데에 띄움
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
