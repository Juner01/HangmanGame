import javax.swing.*;
import java.awt.*;

public class FoodPanel extends JPanel {
    public FoodPanel() {
        setBackground(new Color(255, 222, 173)); // Peach
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw checkered tablecloth pattern
        int squareSize = 25;
        g2d.setColor(new Color(220, 20, 60)); // Crimson
        for (int i = 0; i < getWidth(); i += squareSize) {
            for (int j = getHeight() - 150; j < getHeight(); j += squareSize) {
                if ((i / squareSize) % 2 != (j / squareSize) % 2) {
                    g2d.fillRect(i, j, squareSize, squareSize);
                }
            }
        }

        // Draw the menu book
        int bookX = getWidth() - 200;
        int bookY = getHeight() - 250;

        // Book cover
        g2d.setColor(new Color(139, 69, 19)); // Saddle Brown
        g2d.fillRect(bookX, bookY, 100, 150);
        
        // Book pages
        g2d.setColor(Color.WHITE);
        g2d.fillRect(bookX + 90, bookY + 5, 10, 140);
        
        // "Menu" text on the cover
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.drawString("Menu", bookX + 15, bookY + 80);
    }
}