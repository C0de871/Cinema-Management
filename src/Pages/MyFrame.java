package Pages;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

import static CustomComponent.MyPanels.parentPanel;

public class MyFrame extends JFrame {

    public MyFrame(){
        this.add(parentPanel);
        this.setUndecorated(false);
        this.setSize(1920, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
