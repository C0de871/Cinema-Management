package CustomComponent.Button;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static CustomComponent.StaticClass.Properties.blue_;

public class ButtonOutLine extends JButton {

    public ButtonOutLine() {
        setFocusable(false);
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(5, 0, 5, 0));
        setBackground(blue_);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        int width = getWidth();
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(blue_);
        g2.drawRoundRect(0, 0, width - 1, height - 1, height, height);
        super.paintComponent(grphcs);
    }
}
