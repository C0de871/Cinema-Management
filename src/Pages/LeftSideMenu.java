package Pages;

import CustomComponent.AnimatedPanel;
import CustomComponent.MyIcon;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static CustomComponent.MyLabels.*;
import static CustomComponent.MyPanels.*;
import static CustomComponent.MyText.*;

public class LeftSideMenu extends JPanel{
    LeftSideMenu(){
        this.setLayout(new MigLayout("wrap,gapy 0,gapx 0,insets 0"));
        Border matteBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#373737"));

        //add the parents panels to the left side menu panel
        this.add(headPanel,"growx,growy,pushx,pushy, h 15%,wrap");
        this.add(middlePanel,"growx,growy,pushx,pushy, h 35%,wrap");
        this.add(lowerPanel,"growx,growy,pushx,pushy, h 35%,wrap");
        this.add(freePanel,"growx,growy,pushx,pushy, h 15%,wrap");

        header.add(logoIcon);
        header.add(appName);
        header.add(appName1);
        main.add(mainText);
        genre.add(genreText);

        headPanel.add(header, "growx,pushx");
        middlePanel.add(main,"growx,pushx,h 15%");
        middlePanel.add(discoverBar, "growx,pushx,h 21%");
        middlePanel.add(trendingBar, "growx,pushx,h 21%");
        middlePanel.add(upcomingBar, "growx,pushx,h 21%");
        middlePanel.add(collectionBar, "growx,pushx,h 21%");
        lowerPanel.add(genre, "growx,pushx,h 15%");
        lowerPanel.add(actionBar, "growx,pushx,h 17%");
        lowerPanel.add(dramaBar, "growx,pushx,h 17%");
        lowerPanel.add(comedyBar, "growx,pushx,h 17%");
        lowerPanel.add(adventureBar, "growx,pushx,h 17%");
        lowerPanel.add(documentaryBar, "growx,pushx,h 17%");
    }

}
