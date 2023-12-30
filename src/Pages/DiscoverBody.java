package Pages;

import CustomComponent.MyIcon;
import CustomComponent.MyText;
import CustomComponent.MyTextField;
import CustomComponent.ScrollPaneWin11;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

import static CustomComponent.MyText.*;
import static CustomComponent.Properties.*;
import static java.awt.Color.*;

public class DiscoverBody extends JPanel {
    public DiscoverBody() {
        //set Layout and Background for the body
        this.setLayout(new MigLayout("inset 0,gap 0", "2%[][]9%", "2%[]2%[]2%[]2%[]"));
        this.setBackground(light_Gray);

        //Add Text
        this.add(showTimes, "split 2,growx,pushx");

        //Add search field
        Search searchText = new Search(dark_Gray);
        this.add(searchText, "w 20%,wrap,top,gp 1");

        //Add ShowTimes Panel
        ShowTimesContentPane showTimescontentPane = new ShowTimesContentPane("","general");
        JScrollPane scrollPane1 = new ScrollPaneWin11(showTimescontentPane);
        this.add(scrollPane1, "wmax 90%, wrap, gp 1");

        //Add Text
        this.add(comingSoon,"wrap");

        //Add ComingSoon panel
        ComingSoonContentPane comingSoonContentPane= new ComingSoonContentPane();
        JScrollPane scrollPane2 = new ScrollPaneWin11(comingSoonContentPane);
        this.add(scrollPane2, "wmax 90%, wrap, gp 1");
    }
}
