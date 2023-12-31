package Pages;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import static CustomComponent.StaticClass.Properties.light_Gray;

public class MovieInfoPanel extends JPanel {
    MovieInfoPanel() {
        this.setLayout(new MigLayout(""));
        this.setBackground(light_Gray);
        MovieInfo movieInfo = new MovieInfo();
        TabTime tabTime = new TabTime();
        Comment comment = new Comment();
        this.add(movieInfo, "top, pad 2% 2% 0 0 , w 60%, h 45%");
        this.add(tabTime, "pad 2% 2% 0 0 , w 36%, h 70%,wrap,spany 2");
        this.add(comment, " pad 2% 2% 0 0 , w 60%, h 45%");
//        cardPanel.setMovieInfoPanel(this);
    }
}
