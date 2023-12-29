package Pages;

import CustomComponent.ScrollPaneWin11;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import static CustomComponent.MyText.showTimes;
import static CustomComponent.Properties.dark_Gray;
import static CustomComponent.Properties.light_Gray;

public class TrendingBody extends JPanel {
    public TrendingBody() {
        this.setLayout(new MigLayout("inset 0,gap 0", "2%[][]", "2%[]2%[]2%[]2%[]"));
        this.setBackground(light_Gray);

        //Add Text
        this.add(showTimes, "split 2,growx");

        //Add search field
        Search searchText = new Search(dark_Gray);
        this.add(searchText, "w 10%,wrap,top");

        //Add ShowTimes Panel
        ShowTimesContentPane showTimescontentPane = new ShowTimesContentPane();
        JScrollPane scrollPane1 = new ScrollPaneWin11(showTimescontentPane);
        this.add(scrollPane1, "wmax 1000, wrap");

    }
}
