package Pages;

import CustomComponent.MyIcon;
import CustomComponent.MyText;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

import static CustomComponent.Properties.*;
import static java.awt.Color.*;

public class ComingSoonContentPane extends JPanel {
    ComingSoonContentPane() {
        this.setBackground(light_Gray);
        this.setLayout(new MigLayout("insets 0,gapx 1.5%","[][]",""));
        ComingSoonPosterPanel movie1 = new ComingSoonPosterPanel(new JLabel(MyIcon.joker), new MyText("Joker", 13, light_Blue, 1), new MyText("120 min", 10, blue_, 0), new MyText("Action/Adventure/Drama", 13, gray_, 0));
        this.add(movie1);
        ComingSoonPosterPanel movie2 = new ComingSoonPosterPanel(new JLabel(MyIcon.joker), new MyText("Joker", 13, light_Blue, 1), new MyText("120 min", 10, blue_, 0), new MyText("Action/Adventure/Drama", 13, gray_, 0));
        this.add(movie2);
        ComingSoonPosterPanel movie3 = new ComingSoonPosterPanel(new JLabel(MyIcon.joker), new MyText("Joker", 13, light_Blue, 1), new MyText("120 min", 10, blue_, 0), new MyText("Action/Adventure/Drama", 13, gray_, 0));
        this.add(movie3);
        ComingSoonPosterPanel movie4 = new ComingSoonPosterPanel(new JLabel(MyIcon.joker), new MyText("Joker", 13, light_Blue, 1), new MyText("120 min", 10, blue_, 0), new MyText("Action/Adventure/Drama", 13, gray_, 0));
        this.add(movie4);
        ComingSoonPosterPanel movie6 = new ComingSoonPosterPanel(new JLabel(MyIcon.joker), new MyText("Joker", 13, light_Blue, 1), new MyText("120 min", 10, blue_, 0), new MyText("Action/Adventure/Drama", 13, gray_, 0));
        this.add(movie6);
        ComingSoonPosterPanel movie7 = new ComingSoonPosterPanel(new JLabel(MyIcon.joker), new MyText("Joker", 13, light_Blue, 1), new MyText("120 min", 10, blue_, 0), new MyText("Action/Adventure/Drama", 13, gray_, 0));
        this.add(movie7);
        ComingSoonPosterPanel movie8 = new ComingSoonPosterPanel(new JLabel(MyIcon.joker), new MyText("Joker", 13, light_Blue, 1), new MyText("120 min", 10, blue_, 0), new MyText("Action/Adventure/Drama", 13, gray_, 0));
        this.add(movie8);
        ComingSoonPosterPanel movie9 = new ComingSoonPosterPanel(new JLabel(MyIcon.joker), new MyText("Joker", 13, light_Blue, 1), new MyText("120 min", 10, blue_, 0), new MyText("Action/Adventure/Drama", 13, gray_, 0));
        this.add(movie9);
        ComingSoonPosterPanel movie10 = new ComingSoonPosterPanel(new JLabel(MyIcon.joker), new MyText("Joker", 13, light_Blue, 1), new MyText("120 min", 10, blue_, 0), new MyText("Action/Adventure/Drama", 13, gray_, 0));
        this.add(movie10);
        ComingSoonPosterPanel movie11 = new ComingSoonPosterPanel(new JLabel(MyIcon.joker), new MyText("Joker", 13, light_Blue, 1), new MyText("120 min", 10, blue_, 0), new MyText("Action/Adventure/Drama", 13, gray_, 0));
        this.add(movie11);
    }
}
