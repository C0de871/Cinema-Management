package CustomComponent.StaticClass;

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
    static ImageIcon clickedsearch = new ImageIcon("assest/clickedsearch.png");
    public static ImageIcon myChair = new ImageIcon("assest/my chair.png");
    public static ImageIcon plus = new ImageIcon("assest/plus.png");
    public static ImageIcon plus2 = new ImageIcon("assest/plus2.png");

    public static ImageIcon clickeddiscover = new ImageIcon("assest/clickedplay.png");
    public static ImageIcon clickedtrending = new ImageIcon("assest/clickedtrending.png");
    public static ImageIcon clickedupcoming = new ImageIcon("assest/clickedupcoming.png");
    public static ImageIcon clickedRightArrow = new ImageIcon("assest/clicked right arrow.png");
    public static ImageIcon clickedheart = new ImageIcon("assest/clickedheart.png");
    public static ImageIcon clickeddot = new ImageIcon("assest/clickeddot.png");
    public static ImageIcon clock = new ImageIcon("assest/clock.png");
    public static ImageIcon chairIcon = new ImageIcon("assest/chair.png");

    public static ImageIcon bookedChair = new ImageIcon("assest/booked up chair.png");

    public static ImageIcon hall = new ImageIcon("assest/hall.png");

    public static ImageIcon ticket = new ImageIcon("assest/ticket.png");
    public static ImageIcon rightArrow = new ImageIcon("assest/right arrow.png");
    public static ImageIcon like = new ImageIcon("assest/like.png");
    public static ImageIcon likedIt = new ImageIcon("assest/liked it.png");
    public static ImageIcon star = new ImageIcon("assest/star.png");

    public static ImageIcon logo = new ImageIcon("assest/videocam.png");
    static ImageIcon logo2 = new ImageIcon("assest/videocam (1).png");
    public static BufferedImage originalImage;

    static {
        try {
            originalImage = ImageIO.read(new File("D:/TheFinalProject/assest/joker.jpg"));
        } catch (IOException e) {
            System.out.println("im");
        }
    }

    public static ImageIcon joker = createRoundedImageIcon(originalImage, 150, 180, 250);

    public static ImageIcon createRoundedImageIcon(BufferedImage originalImage, int cornerRadius, int width, int height) {
        // Create a rounded BufferedImage
        BufferedImage roundedImage = createRoundedImage(originalImage, cornerRadius);

        // Convert the BufferedImage to ImageIcon
        Image scaledImage = roundedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    private static BufferedImage createRoundedImage(BufferedImage originalImage, int cornerRadius) {
        if (originalImage == null) {
            // Handle the case where originalImage is null
            // You might want to throw an exception or return a default image
            throw new IllegalArgumentException("Original image cannot be null");
        }
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
