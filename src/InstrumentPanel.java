package src;

import javax.swing.*;
import java.util.ArrayList;

public interface InstrumentPanel {
    void setRecording(boolean isRecording);
    ArrayList<Character> getRecordedKeys();
    boolean requestFocusInWindow();

    String getInstrumentType();
}

