package MainJFrame;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class DiscoverBody extends JPanel {
    DiscoverBody(){
        this.setLayout(new MigLayout("inset 0,gap 0","2%[][]","2%[]2%[]2%[]2%[]"));
        this.setBackground(Color.decode("#222631"));
        MyText showTimes = new MyText("Show Times",30,"#FFFFFF",1);
        this.add(showTimes,"split 2,growx");
        MyTextField searchText=new MyTextField();
        searchText.setPrefixIcon(MyIcon.search);
        searchText.setHint("Search Movies");
        this.add(searchText,"w 10%,wrap,top");
        ShowTimesContentPane ShowTimescontentPane= new ShowTimesContentPane();
        JScrollPane scrollPane1 = new ScrollPaneWin11();
        scrollPane1.setViewportView(ShowTimescontentPane);
        scrollPane1.setBackground(Color.decode("#222631"));
        scrollPane1.setForeground(Color.decode("#222631"));
        scrollPane1.setMinimumSize(new Dimension(0,320));
        scrollPane1.setBorder(BorderFactory.createLineBorder(Color.decode("#222631"), 2));
        this.add(scrollPane1,"wmax 1000, wrap");
        MyText comingSoon = new MyText("comingSoon",30,"#FFFFFF",1);
        this.add(comingSoon,"wrap");
        ComingSoonContentPane comingSoonContentPane= new ComingSoonContentPane();
        JScrollPane scrollPane2 = new ScrollPaneWin11();
        scrollPane2.setViewportView(comingSoonContentPane);
        scrollPane2.setBackground(Color.decode("#222631"));
        scrollPane2.setForeground(Color.decode("#222631"));
        scrollPane2.setMinimumSize(new Dimension(0,320));
        scrollPane2.setBorder(BorderFactory.createLineBorder(Color.decode("#222631"), 2));
        this.add(scrollPane2,"wmax 1000, wrap");

    }
}
