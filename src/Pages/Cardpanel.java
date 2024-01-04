package Pages;

import BackEnd.Movie;

import javax.swing.*;

import static CustomComponent.StaticClass.MyPanels.*;
import static CustomComponent.StaticClass.Properties.cardLayout;
import static CustomComponent.StaticClass.Properties.currentPage;


public class Cardpanel extends JPanel {
    String s = currentPage;


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
        cardLayout.show(this, s);
    }

    public void m(Movie movie) {
        MovieInfoPanel movieInfoPanel1 = new MovieInfoPanel(movie);
        this.add(movieInfoPanel1, "MovieInfo");
        cardLayout.show(this, "MovieInfo");
    }
}
