import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class CloudyPanel extends JPanel {
    private final Color TEAL = new Color(0, 128, 128);
    private final Color CLOUD_WHITE = new Color(255, 255, 255, 200);
    private final int[] cloudX;
    private final int[] cloudY;
    private final int[] cloudSize;
    private final int numClouds = 5;

    public CloudyPanel() {
        // Sets the background color of the panel
        setBackground(TEAL);
        
        // Initializes arrays to store cloud properties
        Random rand = new Random();
        cloudX = new int[numClouds];
        cloudY = new int[numClouds];
        cloudSize = new int[numClouds];
        
        // Generates random positions and sizes for each cloud
        for (int i = 0; i < numClouds; i++) {
            cloudX[i] = rand.nextInt(800);
            cloudY[i] = rand.nextInt(300);
            cloudSize[i] = rand.nextInt(40) + 60;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(CLOUD_WHITE);
        
        // Draws each cloud using the stored properties
        for (int i = 0; i < numClouds; i++) {
            drawCloud(g2d, cloudX[i], cloudY[i], cloudSize[i]);
        }
    }

    private void drawCloud(Graphics2D g2d, int x, int y, int size) {
        // A cloud is drawn as a cluster of overlapping ovals
        g2d.fillOval(x, y, size, size);
        g2d.fillOval(x + size / 2, y - size / 4, size, size);
        g2d.fillOval(x + size, y, size, size);
        g2d.fillOval(x + size / 4, y + size / 4, size, size);
    }
}