package Pages;

import CustomComponent.MyIcon;
import CustomComponent.MyText;
import CustomComponent.Oval;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

import static CustomComponent.Properties.*;

public class ShowTimesPosterPanel extends JLayeredPane {
    Oval rate = new Oval();
    ShowTimesPosterPanel(){
        this.setLayout(new MigLayout("insets 0,gap 0"));
        this.setBackground(light_Gray);

        this.add(rate,"w 50,h 50,pos 155 170");
        this.add(new JLabel(MyIcon.joker), "span 2,wrap, endgroupx grp1");
        this.add(new MyText("Joker", 13, light_Blue, 1), "left");
        this.add(new MyText("120 min", 10, blue_, 0), "wrap, endgroupx grp1");
        this.add(new MyText("Action/Adventure/Drama", 13, gray_, 0));



    }
}
