package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BeatLoader {
    public static List<Note> loadNotes(String filePath, Map<Integer, Character> keyMapping, String instrument) {
        List<Note> notes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    int time = Integer.parseInt(parts[0].trim());
                    int x = Integer.parseInt(parts[1].trim());
                    char key = keyMapping.getOrDefault(x, ' ');
                    notes.add(new Note(x, time, key, instrument)); // instrument 전달
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return notes;
    }
}
