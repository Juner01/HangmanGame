import java.awt.Color;
import javax.swing.JPanel;

public class FoodTheme implements Theme {
    private final String[] words = {"pizza", "spaghetti", "hamburger", "sushi", "taco", "chocolate"};
    private final String[] clues = {"Italian classic with cheese", "Long pasta noodles", "A patty in a bun", "Japanese raw fish delicacy", "Mexican food in a shell", "Sweet treat from cocoa beans"};

    @Override
    public JPanel getBackgroundPanel() {
        return new FoodPanel();
    }

    @Override
    public Color getGroundColor() {
        return new Color(139, 0, 0);
    }

    @Override
    public Color getButtonColor() {
        return new Color(255, 165, 0);
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