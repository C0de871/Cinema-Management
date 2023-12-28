package Pages;

import CustomComponent.MyIcon;
import CustomComponent.MyText;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

import static CustomComponent.Properties.*;

public class ComingSoonPosterPanel extends JLayeredPane {
    ComingSoonPosterPanel(){
        this.setLayout(new MigLayout(" insets 0,gap 0"));
        this.setBackground(light_Gray);

        this.add(new JLabel(MyIcon.joker), "span 2,wrap");
        this.add(new MyText("Joker", 13, light_Blue, 1), "left,split 2,pushx,growx");
        this.add(new MyText("120 min", 10, blue_, 0), "right, wrap");
        this.add(new MyText("Action/Adventure/Drama", 13, gray_, 0));



    }
}
