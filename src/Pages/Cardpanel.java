package Pages;

import javax.swing.*;
import java.awt.*;

import static CustomComponent.MyPanels.bodyPanel;
import static CustomComponent.MyPanels.trendingBody;


public class Cardpanel extends JPanel {
    CardLayout cardLayout = new CardLayout();

    public Cardpanel() {
        this.setLayout(cardLayout);
        this.add(bodyPanel, "Discover");
        this.add(trendingBody, "Trending");
        cardLayout.show(this, "Discover");

    }
}
