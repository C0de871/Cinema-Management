package Pages;

import CustomComponent.popDialgou.GlassPanePopup;

import javax.swing.*;
import java.awt.*;

import static CustomComponent.StaticClass.MyPanels.parentPanel;

public class AdminFrame extends JFrame {

    public AdminFrame() {
        GlassPanePopup.install(this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.add(parentPanel);
        this.setUndecorated(true);
        this.setSize(screenSize.width, screenSize.height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
