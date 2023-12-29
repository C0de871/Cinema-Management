package Pages;

import javax.swing.*;
import java.awt.*;

import static CustomComponent.MyPanels.*;
import static CustomComponent.Properties.cardLayout;


public class Cardpanel extends JPanel {
    //    CardLayout cardLayout = new CardLayout();
    String s = "Discover";

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public Cardpanel(String s) {
        this.setLayout(cardLayout);
        this.add(bodyPanel, "Discover");
        this.add(trendingBody, "Trending");
        this.add(upcomingBody, "Upcoming");
        this.add(action, "Action");
        this.add(drama, "Drama");
        this.add(comedy, "Comedy");
        this.add(adventure, "Adventure");
        this.add(documentary, "Documentary");
        cardLayout.show(this, s);
    }
}
