package MainJFrame;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class ComingSoonPosterPanel extends JLayeredPane {
    ComingSoonPosterPanel(){
        this.setLayout(new MigLayout(" insets 0,gap 0"));
        JLabel movieIcon= new JLabel(MyIcon.joker);
        MyText movieName= new MyText("Joker",13,"#B8BFF4",1);
        MyText movieDuration= new MyText("120 min",10,"#2585f8",0);
        MyText movieGenre= new MyText("Action/Adventure/Drama",13,"#B2B3B6",0);
        this.setBackground(Color.decode("#222631"));
        this.add(movieIcon,"span 2,wrap");
        this.add(movieName,"left,split 2,pushx,growx");
        this.add(movieDuration,"right, wrap");
        this.add(movieGenre);



    }
}
