package Pages;

import CustomComponent.MyIcon;
import CustomComponent.MyText;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

import static CustomComponent.Properties.*;
import static java.awt.Color.*;

public class ShowTimesContentPane extends JPanel {
    ShowTimesContentPane(String wrap) {
        this.setBackground(light_Gray);
        this.setLayout(new MigLayout("insets 0,gapx 15", "[][][][]15", ""));
        ShowTimesPosterPanel movie1 = new ShowTimesPosterPanel(new JLabel(MyIcon.joker), new MyText("Joker", 13, light_Blue, 1), new MyText("120 min", 10, blue_, 0), new MyText("Action/Adventure/Drama", 13, gray_, 0));
        this.add(movie1);
        ShowTimesPosterPanel movie2 = new ShowTimesPosterPanel(new JLabel(MyIcon.joker), new MyText("Joker", 13, light_Blue, 1), new MyText("120 min", 10, blue_, 0), new MyText("Action/Adventure/Drama", 13, gray_, 0));
        this.add(movie2);
        ShowTimesPosterPanel movie3 = new ShowTimesPosterPanel(new JLabel(MyIcon.joker), new MyText("Joker", 13, light_Blue, 1), new MyText("120 min", 10, blue_, 0), new MyText("Action/Adventure/Drama", 13, gray_, 0));
        this.add(movie3);
        ShowTimesPosterPanel movie4 = new ShowTimesPosterPanel(new JLabel(MyIcon.joker), new MyText("Joker", 13, light_Blue, 1), new MyText("120 min", 10, blue_, 0), new MyText("Action/Adventure/Drama", 13, gray_, 0));
        this.add(movie4, wrap);
        ShowTimesPosterPanel movie5 = new ShowTimesPosterPanel(new JLabel(MyIcon.joker), new MyText("Joker", 13, light_Blue, 1), new MyText("120 min", 10, blue_, 0), new MyText("Action/Adventure/Drama", 13, gray_, 0));
        this.add(movie5);
        ShowTimesPosterPanel movie6 = new ShowTimesPosterPanel(new JLabel(MyIcon.joker), new MyText("Joker", 13, light_Blue, 1), new MyText("120 min", 10, blue_, 0), new MyText("Action/Adventure/Drama", 13, gray_, 0));
        this.add(movie6);
        ShowTimesPosterPanel movie7 = new ShowTimesPosterPanel(new JLabel(MyIcon.joker), new MyText("Joker", 13, light_Blue, 1), new MyText("120 min", 10, blue_, 0), new MyText("Action/Adventure/Drama", 13, gray_, 0));
        this.add(movie7);
        ShowTimesPosterPanel movie8 = new ShowTimesPosterPanel(new JLabel(MyIcon.joker), new MyText("Joker", 13, light_Blue, 1), new MyText("120 min", 10, blue_, 0), new MyText("Action/Adventure/Drama", 13, gray_, 0));
        this.add(movie8, wrap);
        ShowTimesPosterPanel movie9 = new ShowTimesPosterPanel(new JLabel(MyIcon.joker), new MyText("Joker", 13, light_Blue, 1), new MyText("120 min", 10, blue_, 0), new MyText("Action/Adventure/Drama", 13, gray_, 0));
        this.add(movie9);
        ShowTimesPosterPanel movie10 = new ShowTimesPosterPanel(new JLabel(MyIcon.joker), new MyText("Joker", 13, light_Blue, 1), new MyText("120 min", 10, blue_, 0), new MyText("Action/Adventure/Drama", 13, gray_, 0));
        this.add(movie10);
        ShowTimesPosterPanel movie11 = new ShowTimesPosterPanel(new JLabel(MyIcon.joker), new MyText("Joker", 13, light_Blue, 1), new MyText("120 min", 10, blue_, 0), new MyText("Action/Adventure/Drama", 13, gray_, 0));
        this.add(movie11);
        ShowTimesPosterPanel movie12 = new ShowTimesPosterPanel(new JLabel(MyIcon.joker), new MyText("Joker", 13, light_Blue, 1), new MyText("120 min", 10, blue_, 0), new MyText("Action/Adventure/Drama", 13, gray_, 0));
        this.add(movie12, wrap);
        ShowTimesPosterPanel movie13 = new ShowTimesPosterPanel(new JLabel(MyIcon.joker), new MyText("Joker", 13, light_Blue, 1), new MyText("120 min", 10, blue_, 0), new MyText("Action/Adventure/Drama", 13, gray_, 0));
        this.add(movie13);
        ShowTimesPosterPanel movie14 = new ShowTimesPosterPanel(new JLabel(MyIcon.joker), new MyText("Joker", 13, light_Blue, 1), new MyText("120 min", 10, blue_, 0), new MyText("Action/Adventure/Drama", 13, gray_, 0));
        this.add(movie14);
        ShowTimesPosterPanel movie15 = new ShowTimesPosterPanel(new JLabel(MyIcon.joker), new MyText("Joker", 13, light_Blue, 1), new MyText("120 min", 10, blue_, 0), new MyText("Action/Adventure/Drama", 13, gray_, 0));
        this.add(movie15);

    }
}
