package Pages;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class MyFrame extends JFrame {
    public MyFrame(){
        //The Frame and ParentPanel
        JPanel parentPanel = new JPanel(new MigLayout("insets 0, gap 0"));
        this.add(parentPanel);

        //The Left Side Menu Bar
        LeftSideMenu leftSideMenu= new LeftSideMenu();
        parentPanel.add(leftSideMenu,"left,pushy,growy,w 15%,wmin 230 ");

        //Discover Page Body
        DiscoverBody bodyPanel = new DiscoverBody();
        parentPanel.add(bodyPanel,"push,grow");
        //Display the Frame

//        this.setUndecorated(false);
//        this.setSize(1000, 500);
        this.setUndecorated(true);
        this.setSize(1920, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
