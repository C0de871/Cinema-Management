package Pages;

import CustomComponent.ScrollPane.ScrollPaneWin11;

import javax.swing.*;

import java.awt.*;

import static CustomComponent.StaticClass.MyPanels.*;
import static CustomComponent.StaticClass.Properties.dark_Gray;
import static CustomComponent.StaticClass.Properties.light_Gray;

public class ParentPanel extends JPanel {
    public ParentPanel(LayoutManager layoutManager) {
        this.setLayout(layoutManager);
        this.setBackground(light_Gray);
        JScrollPane scrollPane = new ScrollPaneWin11(leftSideMenu);
        scrollPane.setBackground(dark_Gray);
        scrollPane.setBorder(BorderFactory.createLineBorder(dark_Gray, 2));
        this.add(scrollPane, "left,pushy,growy,w 15%,wmin 230 ");
        this.add(cardPanel, "push,grow");
    }
}
