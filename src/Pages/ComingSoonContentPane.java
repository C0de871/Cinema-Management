package Pages;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class ComingSoonContentPane extends JPanel {
    ComingSoonContentPane() {
        this.setBackground(Color.decode("#222631"));
        this.setLayout(new MigLayout("insets 0,gapx 1.5%","[][]",""));
        ComingSoonPosterPanel movie1= new ComingSoonPosterPanel();
        this.add(movie1);
        ComingSoonPosterPanel movie2= new ComingSoonPosterPanel();
        this.add(movie2);
        ComingSoonPosterPanel movie3= new ComingSoonPosterPanel();
        this.add(movie3);
        ComingSoonPosterPanel movie4= new ComingSoonPosterPanel();
        this.add(movie4);
        ComingSoonPosterPanel movie6= new ComingSoonPosterPanel();
        this.add(movie6);
        ComingSoonPosterPanel movie7= new ComingSoonPosterPanel();
        this.add(movie7);
        ComingSoonPosterPanel movie8= new ComingSoonPosterPanel();
        this.add(movie8);
        ComingSoonPosterPanel movie9= new ComingSoonPosterPanel();
        this.add(movie9);
        ComingSoonPosterPanel movie10= new ComingSoonPosterPanel();
        this.add(movie10);
        ComingSoonPosterPanel movie11= new ComingSoonPosterPanel();
        this.add(movie11);
    }
}
