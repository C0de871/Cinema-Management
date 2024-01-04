package Pages;

import CustomComponent.ScrollPane.ScrollPaneWin11;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static CustomComponent.StaticClass.MyLabels.profileIcon;
import static CustomComponent.StaticClass.MyPanels.cardPanel;
import static CustomComponent.Text.MyText.*;
import static CustomComponent.StaticClass.Properties.*;

public class DiscoverBody extends JPanel {
    public static ShowTimesContentPane showTimescontentPane;

    public DiscoverBody() {
        //set Layout and Background for the body
        this.setLayout(new MigLayout("inset 0,gap 0", "2%[][]1%", "2%[center]2%[]2%[]2%[]"));
        this.setBackground(light_Gray);

        //Add Text
        this.add(showTimes, "split 3,growx,pushx,center");

        //Add search field
        Search searchText = new Search(dark_Gray);
        this.add(searchText, "w 20%,center,gp 1");
        this.add(profileIcon, "center,gap before 4%,wrap");

        //Add ShowTimes Panel
        showTimescontentPane = new ShowTimesContentPane("", "general");
        JScrollPane scrollPane1 = new ScrollPaneWin11(showTimescontentPane);
        this.add(scrollPane1, "wmax 90%, wrap, gp 1");

        //Add Text
        this.add(comingSoon, "wrap");

        //Add ComingSoon panel
        ComingSoonContentPane comingSoonContentPane = new ComingSoonContentPane();
        JScrollPane scrollPane2 = new ScrollPaneWin11(comingSoonContentPane);
        this.add(scrollPane2, "wmax 90%, wrap, gp 1");
        MouseListener listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                System.out.println(user.getName());
                cardLayout.show(cardPanel, "profile");
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
        profileIcon.addMouseListener(listener);
    }
}
