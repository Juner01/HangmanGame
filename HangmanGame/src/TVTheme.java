import java.awt.Color;
import javax.swing.JPanel;

public class TVTheme implements Theme {
    private final String[] words = {"simpsons", "friends", "seinfeld", "breakingbad", "strangerthings", "theoffice"};
    private final String[] clues = {"D'oh!", "They'll be there for you", "Yada yada yada", "Say my name", "Upside down", "That's what she said"};

    @Override
    public JPanel getBackgroundPanel() {
        return new TVPanel();
    }

    @Override
    public Color getGroundColor() {
        return new Color(0, 0, 0);
    }

    @Override
    public Color getButtonColor() {
        return new Color(192, 192, 192);
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