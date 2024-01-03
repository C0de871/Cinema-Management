package Pages;

import BackEnd.Movie;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import static CustomComponent.StaticClass.Properties.light_Gray;

public class MovieInfoPanel extends JPanel {
    Movie movie;

    MovieInfoPanel(Movie movie) {
        this.movie = movie;
        System.out.println(movie);
        movie.getComments();
        this.setLayout(new MigLayout(""));
        this.setBackground(light_Gray);
        MovieInfo movieInfo = new MovieInfo(movie);
        TabTime tabTime = new TabTime(movie);
        Comment comment = new Comment(movie.getComments(), movie);
        this.add(movieInfo, "top, pad 2% 2% 0 0 , w 60%, h 45%");
        this.add(tabTime, "pad 2% 2% 0 0 , w 36%, h 70%,wrap,spany 2");
        this.add(comment, " pad 2% 2% 0 0 , w 60%, h 45%");
//        cardPanel.setMovieInfoPanel(this);
    }
}
