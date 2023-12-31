package Pages;

import javax.swing.*;

import java.awt.*;

import static CustomComponent.MyPanels.*;
import static CustomComponent.Properties.light_Gray;

public class ParentPanel extends JPanel {
    public ParentPanel(LayoutManager layoutManager) {
        this.setLayout(layoutManager);
        this.setBackground(light_Gray);
        this.add(leftSideMenu, "left,pushy,growy,w 15%,wmin 230 ");
        this.add(cardPanel, "push,grow");
    }
}
