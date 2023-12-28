package CustomComponent;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

import static CustomComponent.Properties.*;
import static java.awt.Color.*;

public class Oval extends JPanel {

    public Oval() {
        setLayout(new MigLayout("center","push[center]push","push[center]push"));
        setOpaque(false);
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setBackground(blue_);
//        setCursor(new Cursor(Cursor.HAND_CURSOR));
        MyText rate = new MyText("7.5", 15, white_, 0);
        this.add(rate,"dock center");
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        int width = getWidth();
        int height = getHeight();
        BufferedImage img = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillOval(0, 0, 50, 50);
        g2.dispose();
        grphcs.drawImage(img, 0, 0, null);
        super.paintComponent(grphcs);
    }
}
