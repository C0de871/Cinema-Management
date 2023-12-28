package Pages;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class ShowTimesContentPane extends JPanel {
    ShowTimesContentPane() {
        this.setBackground(Color.decode("#222631"));
        this.setLayout(new MigLayout("insets 0,gapx 1.5%","[][]",""));
        ShowTimesPosterPanel movie1= new ShowTimesPosterPanel();
        this.add(movie1);
        ShowTimesPosterPanel movie2= new ShowTimesPosterPanel();
        this.add(movie2);
        ShowTimesPosterPanel movie3= new ShowTimesPosterPanel();
        this.add(movie3);
        ShowTimesPosterPanel movie4= new ShowTimesPosterPanel();
        this.add(movie4);
        ShowTimesPosterPanel movie6= new ShowTimesPosterPanel();
        this.add(movie6);
        ShowTimesPosterPanel movie7= new ShowTimesPosterPanel();
        this.add(movie7);
        ShowTimesPosterPanel movie8= new ShowTimesPosterPanel();
        this.add(movie8);
        ShowTimesPosterPanel movie9= new ShowTimesPosterPanel();
        this.add(movie9);
        ShowTimesPosterPanel movie10= new ShowTimesPosterPanel();
        this.add(movie10);
        ShowTimesPosterPanel movie11= new ShowTimesPosterPanel();
        this.add(movie11);
    }
}
