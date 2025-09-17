import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreManager {
    private static final String SCORE_FILE = "scores.txt";

    public static void saveScore(String playerName, String score) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SCORE_FILE, true))) {
            writer.println(playerName + " - " + score);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> loadScores() {
        List<String> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(line);
            }
        } catch (IOException e) {
            // File might not exist yet, which is fine
        }
        Collections.sort(scores, Collections.reverseOrder());
        return scores;
    }
}