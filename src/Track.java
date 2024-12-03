package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Track extends JPanel {
    private List<Note> notes;
    private long startTime;

    public Track() {
        notes = new ArrayList<>();
        setLayout(null);
        setBackground(Color.BLACK);
    }

    public void addNote(Note note) {
        notes.add(note);
        this.add(note);
    }

    public void moveNotes() {
        Iterator<Note> iterator = notes.iterator();
        while (iterator.hasNext()) {
            Note note = iterator.next();
            if (note.isActive()) {
                note.moveDown(5);
                note.repaint();
            } else {
                this.remove(note);
                iterator.remove();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Note note : notes) {
            note.paintComponent(g);
        }
    }

    public void generateNotes(List<Note> noteTimingData) {
        new Timer(10, e -> {
            long elapsedTime = System.currentTimeMillis() - startTime;
            for (Note note : noteTimingData) {
                if (note.getTime() <= elapsedTime) {
                    addNote(note);
                }
            }
            moveNotes();
            repaint();
        }).start();
    }

    public void startGame() {
        startTime = System.currentTimeMillis();
    }
}
