import javax.swing.*;
import java.awt.*;

public class TVPanel extends JPanel {
    public TVPanel() {
        setBackground(new Color(25, 25, 112)); // Midnight Blue
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw the TV
        int tvWidth = 400;
        int tvHeight = 250;
        int tvX = (getWidth() - tvWidth) / 2;
        int tvY = (getHeight() - tvHeight) / 2 - 50;

        // TV body
        g2d.setColor(Color.BLACK);
        g2d.fillRect(tvX, tvY, tvWidth, tvHeight);
        
        // TV screen
        g2d.setColor(Color.WHITE);
        g2d.fillRect(tvX + 20, tvY + 20, tvWidth - 40, tvHeight - 40);

        // TV Stand
        g2d.setColor(Color.BLACK);
        g2d.fillRect(tvX + tvWidth / 2 - 50, tvY + tvHeight, 100, 30);
    }
}