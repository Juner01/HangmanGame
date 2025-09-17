import javax.swing.*;
import java.awt.*;

public class MarioPanel extends JPanel {
    public MarioPanel() {
        setBackground(new Color(0, 128, 128)); // Teal Sky
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw the ground (brown bricks)
        g2d.setColor(new Color(139, 69, 19));
        int brickHeight = 30;
        int brickWidth = 60;
        for (int i = 0; i < getWidth(); i += brickWidth) {
            for (int j = getHeight() - 100; j < getHeight(); j += brickHeight) {
                g2d.fillRect(i, j, brickWidth, brickHeight);
                g2d.setColor(new Color(150, 75, 0)); // Darker brown for lines
                g2d.drawRect(i, j, brickWidth, brickHeight);
                g2d.setColor(new Color(139, 69, 19));
            }
        }

        // Draw green bushes
        g2d.setColor(new Color(34, 139, 34)); // Forest Green
        g2d.fillOval(50, getHeight() - 150, 100, 70);
        g2d.fillOval(80, getHeight() - 170, 100, 70);
        g2d.fillOval(110, getHeight() - 150, 100, 70);
        g2d.fillOval(400, getHeight() - 120, 120, 80);
        g2d.fillOval(450, getHeight() - 140, 120, 80);
        g2d.fillOval(500, getHeight() - 120, 120, 80);
    }
}