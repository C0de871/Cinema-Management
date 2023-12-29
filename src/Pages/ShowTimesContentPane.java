package Pages;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

import static CustomComponent.Properties.*;
import static java.awt.Color.*;

public class ShowTimesContentPane extends JPanel {
    ShowTimesContentPane(String wrap) {
        this.setBackground(light_Gray);
        this.setLayout(new MigLayout("insets 0,gapx 15", "[][][][]15", ""));
        ShowTimesPosterPanel movie1= new ShowTimesPosterPanel();
        this.add(movie1);
        ShowTimesPosterPanel movie2= new ShowTimesPosterPanel();
        this.add(movie2);
        ShowTimesPosterPanel movie3= new ShowTimesPosterPanel();
        this.add(movie3);
        ShowTimesPosterPanel movie4= new ShowTimesPosterPanel();
        this.add(movie4, wrap);
        ShowTimesPosterPanel movie5 = new ShowTimesPosterPanel();
        this.add(movie5);
        ShowTimesPosterPanel movie6= new ShowTimesPosterPanel();
        this.add(movie6);
        ShowTimesPosterPanel movie7= new ShowTimesPosterPanel();
        this.add(movie7);
        ShowTimesPosterPanel movie8= new ShowTimesPosterPanel();
        this.add(movie8, wrap);
        ShowTimesPosterPanel movie9= new ShowTimesPosterPanel();
        this.add(movie9);
        ShowTimesPosterPanel movie10= new ShowTimesPosterPanel();
        this.add(movie10);
        ShowTimesPosterPanel movie11= new ShowTimesPosterPanel();
        this.add(movie11);
        ShowTimesPosterPanel movie12 = new ShowTimesPosterPanel();
        this.add(movie12, wrap);
        ShowTimesPosterPanel movie13 = new ShowTimesPosterPanel();
        this.add(movie13);
        ShowTimesPosterPanel movie14 = new ShowTimesPosterPanel();
        this.add(movie14);
        ShowTimesPosterPanel movie15 = new ShowTimesPosterPanel();
        this.add(movie15);

    }
}
