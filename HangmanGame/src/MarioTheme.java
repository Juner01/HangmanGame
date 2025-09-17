import java.awt.Color;
import javax.swing.JPanel;

public class MarioTheme implements Theme {
    private final String[] words = {"mario", "princess", "bowser", "toad", "yoshi", "luigi"};
    private final String[] clues = {"A famous plumber", "A royal damsel", "The king of koopas", "A friendly mushroom", "A green dinosaur", "Mario's brother"};

    @Override
    public JPanel getBackgroundPanel() {
        return new MarioPanel();
    }

    @Override
    public Color getGroundColor() {
        return new Color(139, 69, 19); // Saddle Brown
    }

    @Override
    public Color getButtonColor() {
        return new Color(255, 215, 0); // Gold
    }

    @Override
    public String[] getWords() {
        return words;
    }

    @Override
    public String[] getClues() {
        return clues;
    }
}