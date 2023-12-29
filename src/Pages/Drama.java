package Pages;

import CustomComponent.MyText;
import CustomComponent.ScrollPaneWin11;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import static CustomComponent.Properties.*;

public class Drama extends JPanel {
    public Drama() {
        this.setLayout(new MigLayout("inset 0,gap 0", "2%[][]", "2%[]2%[]2%[]2%[]"));
        this.setBackground(light_Gray);

        //Add Text
        this.add(new MyText("Drama", 30, white_, 1), "split 2 ,growx");

        //Add search field
        Search searchText = new Search(dark_Gray);
        this.add(searchText, "w 20%,wrap,top");

        //Add ShowTimes Panel
        ShowTimesContentPane showTimescontentPane = new ShowTimesContentPane("wrap");
        JScrollPane scrollPane1 = new ScrollPaneWin11(showTimescontentPane);
        this.add(scrollPane1, "wmax 1000");

    }
}
