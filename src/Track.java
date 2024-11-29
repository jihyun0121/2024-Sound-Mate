package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Track extends JPanel {
    private List<Note> notes;

    public Track() {
        notes = new ArrayList<>();
        setLayout(null);
        setBackground(Color.BLACK);
    }

    public void addNote(int xPosition) {
        Note note = new Note(xPosition);
        notes.add(note);
        this.add(note);
    }

    public void moveNotes() {
        for (Note note : notes) {
            if (note.isActive()) {
                note.moveDown();
                note.repaint();
            }
        }
        notes.removeIf(note -> !note.isActive());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Note note : notes) {
            note.paint(g);
        }
    }
}
