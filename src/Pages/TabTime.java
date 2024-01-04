package Pages;

import BackEnd.Movie;
import BackEnd.Showtimes;
import CustomComponent.CustomPanel.MaterialTabbed;

import java.util.ArrayList;
import java.util.List;

import static CustomComponent.StaticClass.Properties.blue_;

public class TabTime extends MaterialTabbed {
    Movie movie;
    List<Showtimes> showTimes;

    TabTime(Movie movie) {
        this.movie = movie;
        this.showTimes = movie.getShowtimes();
        this.setForeground(blue_);
        for (int i = 0; i < showTimes.size(); i++) {
            Ticketing ticketing = new Ticketing(showTimes.get(i), this.movie);
            String start = showTimes.get(i).getTimeHour(showTimes.get(i).getMovieStartTime());
            String end = showTimes.get(i).getTimeHour(showTimes.get(i).getMovieStartTime());
            this.addTab(start + "-" + end, ticketing);
        }
    }
}
