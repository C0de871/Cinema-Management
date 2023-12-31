package Pages;

import BackEnd.Cinema;
import BackEnd.Movie;
import CustomComponent.StaticClass.MyIcon;
import CustomComponent.Text.MyText;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.ArrayList;

import static CustomComponent.StaticClass.Properties.*;

public class ShowTimesContentPane extends JPanel {
    ShowTimesContentPane(String wrap,String genreInput) {
        this.setBackground(light_Gray);
        this.setLayout(new MigLayout("insets 0,gapx 15", "[][][][]15", ""));
        Cinema c = new Cinema();
        ArrayList<Movie> moviesGenre=c.getAllMoviesGenre(genreInput);
        for(int i=0;i<moviesGenre.size();i++)
        {
            String title = moviesGenre.get(i).getTitle();
            String genre = moviesGenre.get(i).getGenre();
            String duration = String.valueOf(moviesGenre.get(i).getMinutesOfMovie());
            String rate = String.valueOf(moviesGenre.get(i).getAverageRating(moviesGenre.get(i)));
            ShowTimesPosterPanel movieP = new ShowTimesPosterPanel(new JLabel(MyIcon.joker), new MyText(title, 13, light_Blue, 1), new MyText(duration+" min", 10, blue_, 0), new MyText(genre, 13, gray_, 0), rate);
            if ((i + 1) % 4 == 0)
                this.add(movieP, wrap);
            else
                this.add(movieP);
        }
    }
}
