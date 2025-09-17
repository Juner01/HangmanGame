import javax.swing.*;
import java.awt.*;

public class HangmanCanvas extends JPanel {
    private int incorrectGuesses;
    private String wordToDisplay;

    public HangmanCanvas() {
        setOpaque(false);
    }

    public void setIncorrectGuesses(int incorrectGuesses) {
        this.incorrectGuesses = incorrectGuesses;
        repaint();
    }

    public void setWordToDisplay(String word) {
        this.wordToDisplay = word;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4));
        g2d.setColor(Color.WHITE);

        int width = getWidth();
        int height = getHeight();

        // Draw the gallows
        g2d.drawLine(50, height - 50, width - 50, height - 50);
        g2d.drawLine(100, height - 50, 100, 100);
        g2d.drawLine(100, 100, 250, 100);
        g2d.drawLine(250, 100, 250, 150);

        // Draw the hangman parts based on incorrect guesses
        g2d.setColor(Color.WHITE);
        if (incorrectGuesses >= 1) { // Head
            g2d.drawOval(225, 150, 50, 50);
        }
        if (incorrectGuesses >= 2) { // Body
            g2d.drawLine(250, 200, 250, 300);
        }
        if (incorrectGuesses >= 3) { // Left arm
            g2d.drawLine(250, 220, 200, 250);
        }
        if (incorrectGuesses >= 4) { // Right arm
            g2d.drawLine(250, 220, 300, 250);
        }
        if (incorrectGuesses >= 5) { // Left leg
            g2d.drawLine(250, 300, 200, 350);
        }
        if (incorrectGuesses >= 6) { // Right leg
            g2d.drawLine(250, 300, 300, 350);
        }

        // Draw the word to guess. The 'wordToDisplay' field is now used here.
        if (wordToDisplay != null) {
            g2d.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
            FontMetrics metrics = g2d.getFontMetrics();
            int x = (getWidth() - metrics.stringWidth(wordToDisplay)) / 2;
            int y = getHeight() - 100; // Position above the keyboard
            g2d.drawString(wordToDisplay, x, y);
        }
    }
}