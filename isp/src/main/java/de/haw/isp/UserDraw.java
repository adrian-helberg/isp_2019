package de.haw.isp;

import java.lang.Math;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 * Intelligente Systeme Praktikum, Aufgabe 2
 * Thema: Lernen im künstlichem neuronalem Netz (KNN)
 * <p>
 * User Eingabe
 *
 * @author Adrian Helberg
 * @author Rodrigo Ehlers
 */
class UserDraw extends JPanel implements MouseMotionListener {
    private final static int scale = 10;
    private final static int pxCount = 28;
    private final static int width = pxCount * scale;
    private final static int height = pxCount * scale;
    private BufferedImage img;

    UserDraw() {
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB_PRE);
        setPreferredSize(new Dimension(width, height));
        this.addMouseMotionListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Graphics g = img.getGraphics();

        g.setColor(Color.black);
        Point p = e.getPoint();
        g.fillOval(p.x, p.y, 15, 15);

        g.dispose();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    void clear() {
        Graphics g = img.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, img.getWidth(null), img.getHeight(null));
        g.dispose();
        repaint();
    }

    String getImageString() {
        String mnistString = "";
        for (int i = 0; i < this.width; i += scale) {
            for (int j = 0; j < this.height; j += scale) {
                mnistString += ",";
                mnistString += String.valueOf(scalePixel(j, i));

            }
        }
        return mnistString.substring(1);
    }

    private int scalePixel(int startHeight, int startWidth) {
        int totalRed = 0;
        int totalGreen = 0;
        int totalBlue = 0;

        for (int i = startWidth; i < startWidth + scale; i++) {
            for (int j = startHeight; j < startHeight + scale; j++) {
                Color color = new Color(img.getRGB(j, i));
                totalRed += color.getRed();
                totalGreen += color.getGreen();
                totalBlue += color.getBlue();

            }
        }
        return (Math.abs((((totalRed + totalGreen + totalBlue) / 100) / 3) - 255));
    }
}
