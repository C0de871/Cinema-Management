package Pages;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import static CustomComponent.MyPanels.cardPanel;
import static CustomComponent.Properties.light_Gray;

public class MovieInfoPanel extends JPanel {
    MovieInfoPanel() {
        this.setLayout(new MigLayout());
        this.setBackground(light_Gray);
        this.add(new MovieInfo(), "top, pad 2% 2% 0 0 , w 60%, h 45%,wrap");
        this.add(new Comment(), " pad 2% 2% 0 0 , w 60%, h 45%");
//        cardPanel.setMovieInfoPanel(this);
    }
}
