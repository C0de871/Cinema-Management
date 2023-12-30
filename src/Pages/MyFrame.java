package Pages;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

import static CustomComponent.MyPanels.parentPanel;

public class MyFrame extends JFrame {

    public MyFrame(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.add(parentPanel);
        this.setUndecorated(true);
        this.setSize(screenSize.width, screenSize.height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
