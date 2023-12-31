package Pages;

import BackEnd.Cinema;
import BackEnd.Movie;
import CustomComponent.MyText;
import CustomComponent.ScrollPaneWin11;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import java.util.ArrayList;

import static CustomComponent.Properties.*;

public class Action extends JPanel {
    public Action() {
        this.setLayout(new MigLayout("inset 0,gap 0", "2%[][]9%", "2%[]2%[]2%[]2%[]"));
        this.setBackground(light_Gray);

        //Add Text
        this.add(new MyText("Action", 30, white_, 1), "split 2 ,pushx,growx");

        //Add search field
        Search searchText = new Search(dark_Gray);
        this.add(searchText, "w 20%,wrap,top");

        //Add ShowTimes Panel
        ShowTimesContentPane showTimescontentPane = new ShowTimesContentPane("wrap","action");
        JScrollPane scrollPane1 = new ScrollPaneWin11(showTimescontentPane);
        this.add(scrollPane1, "wmax 1000");

    }
}
