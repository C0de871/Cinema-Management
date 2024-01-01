package Pages;

import CustomComponent.Button.ShadingButton;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static CustomComponent.StaticClass.MyIcon.plus;
import static CustomComponent.StaticClass.MyLabels.*;
import static CustomComponent.StaticClass.MyPanels.*;
import static CustomComponent.StaticClass.Properties.*;
import static CustomComponent.Text.MyText.*;

public class LeftSideMenu extends JPanel{
    public LeftSideMenu() {

        this.setLayout(new MigLayout("wrap,gapy 0,gapx 0,insets 0"));
        Border matteBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#373737"));

        //add the parents panels to the left side menu panel
        this.add(headPanel,"growx,growy,pushx,pushy, h 15%,wrap");
        this.add(middlePanel,"growx,growy,pushx,pushy, h 35%,wrap");
        this.add(lowerPanel,"growx,growy,pushx,pushy, h 35%,wrap");
        this.add(freePanel,"growx,growy,pushx,pushy, h 15%,wrap");

        ShadingButton add = new ShadingButton();
        add.setRound(400);
        add.setBackground(light_Gray);
        add.setForeground(light_Blue);
        add.setFocusable(false);
        add.setIcon(plus);
        add.setRippleColor(new java.awt.Color(255, 255, 255));
        add.setShadowColor(dark_Gray);

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

        freePanel.add(add, ", h 50%,w 30%");
        MouseListener listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "movieAdd");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        add.addMouseListener(listener);
    }

}
