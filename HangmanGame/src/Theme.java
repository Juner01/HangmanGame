import java.awt.Color;
import javax.swing.JPanel;

public interface Theme {
    JPanel getBackgroundPanel();
    Color getGroundColor();
    Color getButtonColor();
    String[] getWords();
    String[] getClues();
}