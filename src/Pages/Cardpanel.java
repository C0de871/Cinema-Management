package Pages;

import BackEnd.Movie;

import javax.swing.*;

import static CustomComponent.StaticClass.MyPanels.*;
import static CustomComponent.StaticClass.Properties.cardLayout;
import static CustomComponent.StaticClass.Properties.currentPage;


public class Cardpanel extends JPanel {
    //    CardLayout cardLayout = new CardLayout();
    String s = currentPage;

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    MovieInfoPanel movieInfoPanel;

//    public void setMovieInfoPanel(MovieInfoPanel movieInfoPanel) {
//        this.movieInfoPanel = movieInfoPanel;
//        cardLayout.show(this, "MovieInfo");
//    }


    public Cardpanel(String s) {
        Profile profile = new Profile();
        this.setLayout(cardLayout);
        this.add(bodyPanel, "Discover");
        this.add(trendingBody, "Trending");
        this.add(upcomingBody, "Upcoming");
        this.add(action, "Action");
        this.add(drama, "Drama");
        this.add(comedy, "Comedy");
        this.add(adventure, "Adventure");
        this.add(documentary, "Documentary");
        this.add(addMovie, "movieAdd");
        this.add(profile, "profile");
//        this.add(new MovieInfoPanel(),"MovieInfo");
//        this.add(movieInfo,"movieInfo"); // temp
        cardLayout.show(this, s);
    }

    public void m(Movie movie) {
        MovieInfoPanel movieInfoPanel1 = new MovieInfoPanel(movie);
        System.out.println(movie);
        this.add(movieInfoPanel1, "MovieInfo");
        cardLayout.show(this, "MovieInfo");
    }
}
