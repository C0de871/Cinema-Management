package Pages;

import BackEnd.Cinema;
import BackEnd.Movie;
import CustomComponent.StaticClass.MyIcon;
import CustomComponent.Text.MyText;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.ArrayList;

import static CustomComponent.StaticClass.MyIcon.createRoundedImageIcon;
import static CustomComponent.StaticClass.Properties.*;

public class ShowTimesContentPane extends JPanel {
    private static final int REFRESH_INTERVAL_MS = 5000; // Set the refresh interval to 5000 milliseconds (5 seconds)
    private String wrap;
    private String genreInput;

    public ShowTimesContentPane(String wrap, String genreInput) {
        this.wrap = wrap;
        this.genreInput = genreInput;
        initUI();

        // Create a thread to refresh the content periodically
//        Thread refreshThread = new Thread(() -> {
//            while (true) {
//                try {
//                    Thread.sleep(100000);
//                    SwingUtilities.invokeLater(() -> refreshMovies());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        refreshThread.start();
    }

    private void initUI() {
        this.setBackground(light_Gray);
        this.setLayout(new MigLayout("insets 0,gapx 15", "[][][][]15", ""));
        refreshMovies(); // Initial load of movies
    }

    public void refreshMovies() {
        removeAll(); // Clear existing components

        Cinema c = new Cinema();
        ArrayList<Movie> moviesGenre = c.getAllMovies();
        for (int i = 0; i < moviesGenre.size(); i++) {
            Movie movie = moviesGenre.get(i);

            String title = movie.getTitle();
            String genre = movie.getGenre();
            String duration = String.valueOf(movie.getMinutesOfMovie());
            String rate = String.valueOf(movie.getAverageRating(movie));
            String path = movie.getMoviePath();
            ImageIcon posterImage = createRoundedImageIcon(path, 150, 180, 250);

            ShowTimesPosterPanel movieP = new ShowTimesPosterPanel(
                    movie,
                    new JLabel(posterImage),
                    new MyText(title, 13, light_Blue, 1),
                    new MyText(duration + " min", 10, blue_, 0),
                    new MyText(genre, 13, gray_, 0),
                    rate
            );

            if ((i + 1) % 4 == 0)
                add(movieP, wrap);
            else
                add(movieP);
        }

        revalidate(); // Refresh the layout
        repaint(); // Repaint the panel
    }
}
