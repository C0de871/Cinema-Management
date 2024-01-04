package Pages;

import BackEnd.Movie;
import CustomComponent.StaticClass.MyIcon;
import CustomComponent.Text.MyText;
import CustomComponent.CustomPanel.PanelRound;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static CustomComponent.StaticClass.MyIcon.*;
import static CustomComponent.StaticClass.Properties.dark_Gray;

public class MovieInfo extends PanelRound {
    Movie movie;
    Border matteBorder = BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode("#373737"));

    public MovieInfo(Movie movie) {
        this.setRoundBottomLeft(40);
        this.setRoundBottomRight(40);
        this.setRoundTopLeft(40);
        this.setRoundTopRight(40);
        this.setBackground(dark_Gray);
        this.movie = movie;
        String name = movie.getTitle();
        String genre = movie.getGenre();
        String duration = String.valueOf(movie.getMinutesOfMovie());
        String rate = String.valueOf(movie.getAverageRating(this.movie));
        String hallNum = String.valueOf(movie.getHallNum());
        String price = String.valueOf(movie.getPrice());
        String path = movie.getMoviePath();

        this.setLayout(new MigLayout("insets 3% 3% 3% 3%", "[]3%[]5%[]", "[]8%[]"));
        MyText name1 = new MyText(name, 50, Color.white, 1);
        MyText rate1 = new MyText(rate, 14, Color.YELLOW, 1);
        MyText duration1 = new MyText(duration, 12, Color.WHITE, 0);
        MyText hallNum1 = new MyText(hallNum, 12, Color.WHITE, 0);
        MyText bookTicket = new MyText("Book Now", 30, Color.WHITE, 0);
        MyText price1 = new MyText(price, 12, Color.white, 0);
        MyText length = new MyText("Length", 10, Color.white, 0);
        MyText hall = new MyText("Hall", 10, Color.white, 0);
        MyText pay = new MyText("Price", 10, Color.white, 0);
        ImageIcon posterImage = createRoundedImageIcon(path, 150, 200, 320);
        JLabel poster = new JLabel(posterImage);
        JLabel starIcon = new JLabel(MyIcon.star);
        JLabel clockIcon = new JLabel(MyIcon.clock);
        JLabel hallIcon = new JLabel(MyIcon.hall);
        JLabel priceIcon = new JLabel(MyIcon.ticket);
        JLabel heartIcon = new JLabel(MyIcon.like);
        JLabel rightArrow = new JLabel(MyIcon.rightArrow);
        JPanel lengthPanel = new JPanel(new MigLayout());
        lengthPanel.setBackground(dark_Gray);

        lengthPanel.add(clockIcon, "spany2");
        lengthPanel.add(length, "wrap,center");
        lengthPanel.add(duration1, "center");
        lengthPanel.setBorder(matteBorder);
        JPanel hallPanel = new JPanel(new MigLayout());
        hallPanel.setBackground(dark_Gray);

        hallPanel.add(hallIcon, "spany2");
        hallPanel.add(hall, "wrap,center");
        hallPanel.add(hallNum1, "center");
        hallPanel.setBorder(matteBorder);
        JPanel pricePanel = new JPanel(new MigLayout());
        pricePanel.setBackground(dark_Gray);

        pricePanel.add(priceIcon, "spany2");
        pricePanel.add(pay, "wrap,center");
        pricePanel.add(price1, "center");
        this.add(poster, "spany 3");
        this.add(name1, "growx,pushx");
        this.add(heartIcon, "left,wrap");
        this.add(starIcon, "split 2");
        this.add(rate1, "wrap ,pushy");
        this.add(lengthPanel, "split 3,bottom,w 13%,h 13%");
        this.add(hallPanel, "bottom,w 13%,h 13%");
        this.add(pricePanel, "bottom,w 13%,h 13%");
        MouseListener listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (heartIcon.getIcon() == like) {
                    heartIcon.setIcon(likedIt);
                } else heartIcon.setIcon(like);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        heartIcon.addMouseListener(listener);
//        this.add(bookTicket, "split 2,center");
//        this.add(rightArrow, "center");
    }
}
