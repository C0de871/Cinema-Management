package CustomComponent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyIcon {
    public static ImageIcon discover = new ImageIcon("assest/play.png");
    public static ImageIcon trending = new ImageIcon("assest/trending.png");
    public static ImageIcon upcoming = new ImageIcon("assest/upcoming.png");
    public static ImageIcon heart = new ImageIcon("assest/heart.png");
    public static ImageIcon dot = new ImageIcon("assest/dot.png");
    public static ImageIcon search = new ImageIcon("assest/search.png");
    static ImageIcon clickedsearch= new ImageIcon("assest/clickedsearch.png");
    static ImageIcon clickeddiscover = new ImageIcon("assest/clickedplay.png");
    static ImageIcon clickedtrending = new ImageIcon("assest/clickedtrending.png");
    static ImageIcon clickedupcoming = new ImageIcon("assest/clickedupcoming.png");
    static ImageIcon clickedheart = new ImageIcon("assest/clickedheart.png");
    static ImageIcon clickeddot = new ImageIcon("assest/clickeddot.png");
    public static ImageIcon logo = new ImageIcon("assest/videocam.png");
    static ImageIcon logo2 = new ImageIcon("assest/videocam (1).png");
    static BufferedImage originalImage;

    static {
        try {
            originalImage = ImageIO.read(new File("D:/Second Year Project/Cinema-Management/assest/joker.jpg"));
        } catch (IOException e) {
            System.out.println("im");
        }
    }

    public static ImageIcon joker = createRoundedImageIcon(originalImage, 150);


    // Method to resize the image
    private static ImageIcon createRoundedImageIcon(BufferedImage originalImage, int cornerRadius) {
        // Create a rounded BufferedImage
        BufferedImage roundedImage = createRoundedImage(originalImage, cornerRadius);

        // Convert the BufferedImage to ImageIcon
        Image scaledImage = roundedImage.getScaledInstance(180, 250, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    private static BufferedImage createRoundedImage(BufferedImage originalImage, int cornerRadius) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        // Create a new BufferedImage for the rounded image
        BufferedImage roundedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = roundedImage.createGraphics();

        // Create a rounded shape using RoundRectangle2D
        RoundRectangle2D roundedRect = new RoundRectangle2D.Float(0, 0, width, height, cornerRadius * 2, cornerRadius * 2);

        // Clip the graphics to the rounded shape
        g2.setClip(roundedRect);

        // Draw the image onto the rounded shape
        g2.drawImage(originalImage, 0, 0, width, height, null);

        g2.dispose();
        return roundedImage;
    }
}
