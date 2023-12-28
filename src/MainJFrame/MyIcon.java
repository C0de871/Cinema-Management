package MainJFrame;

import javax.swing.*;
import java.awt.*;

public class MyIcon {
    static ImageIcon discover = new ImageIcon("assest/play.png");
    static ImageIcon trending = new ImageIcon("assest/trending.png");
    static ImageIcon upcoming = new ImageIcon("assest/upcoming.png");
    static ImageIcon heart = new ImageIcon("assest/heart.png");
    static ImageIcon dot = new ImageIcon("assest/dot.png");
    static ImageIcon search= new ImageIcon("assest/search.png");
    static ImageIcon clickedsearch= new ImageIcon("assest/clickedsearch.png");
    static ImageIcon clickeddiscover = new ImageIcon("assest/clickedplay.png");
    static ImageIcon clickedtrending = new ImageIcon("assest/clickedtrending.png");
    static ImageIcon clickedupcoming = new ImageIcon("assest/clickedupcoming.png");
    static ImageIcon clickedheart = new ImageIcon("assest/clickedheart.png");
    static ImageIcon clickeddot = new ImageIcon("assest/clickeddot.png");
    static ImageIcon logo = new ImageIcon("assest/videocam.png");
    static ImageIcon logo2 = new ImageIcon("assest/videocam (1).png");
    static  ImageIcon joker = createImageIcon("assest/joker.jpg");


    // Method to resize the image
    private static ImageIcon createImageIcon(String fileName) {
        try {
            Image image = new ImageIcon(fileName).getImage();
            // You can scale the image if needed
            Image scaledImage = image.getScaledInstance(180, 250, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
