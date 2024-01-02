package CustomComponent.Text;

import javax.swing.*;
import java.awt.*;

import static CustomComponent.StaticClass.Properties.*;
import static CustomComponent.StaticClass.Properties.gray_;

public class MyText extends JLabel {
    public MyText(String text, int size, Color color, int fontStyle) {
        this.setText(text);
        this.setFont(new Font("sansserif",fontStyle, size));
        this.setForeground(color);
    }

    public static MyText appName = new MyText("Tr", 20, blue_, 1);
    public static MyText addMovie = new MyText("Add Movie", 50, new java.awt.Color(29, 162, 253), 1);

    public static MyText movieTitle = new MyText("Title : ", 20, Color.decode("#7f7f82"), 1);
    public static MyText movieGenre = new MyText("Genre : ", 20, Color.decode("#7f7f82"), 1);
    public static MyText movieStart = new MyText("Start : ", 20, Color.BLACK, 1);
    public static MyText movieEnd = new MyText("End : ", 20, Color.BLACK, 1);
    public static MyText moviePrice = new MyText("Price : ", 20, Color.decode("#7f7f82"), 1);
    public static MyText movieHallNum = new MyText("Hall Number : ", 20, Color.decode("#7f7f82"), 1);
    public static MyText movieShowTime_1 = new MyText("First Show Time ", 20, Color.decode("#7f7f82"), 1);
    public static MyText movieShowTime_2 = new MyText("Second Show Time", 20, Color.decode("#7f7f82"), 1);
    public static MyText movieShowTime_3 = new MyText("Third Show Time", 20, Color.decode("#7f7f82"), 1);

    public static MyText appName1 = new MyText("inkey", 20, light_Blue, 1);

    public static MyText discoverText = new MyText("Discover", 13, light_Blue, 1);
    public static MyText trendingText = new MyText("Trending", 13, light_Blue, 1);
    public static MyText upcomingText = new MyText("Upcoming", 13, light_Blue, 1);
    public static MyText heartText = new MyText("My Collection", 13, light_Blue, 1);
    public static MyText mainText = new MyText("Main", 11, light_Blue, 1);
    public static MyText genreText = new MyText("Genre", 11, light_Blue, 1);
    public static MyText actionText = new MyText("Action", 9, light_Blue, 1);
    public static MyText dramaText = new MyText("Drama", 9, light_Blue, 1);
    public static MyText comedyText = new MyText("Comedy", 9, light_Blue, 1);
    public static MyText adventureText = new MyText("Adventure", 9, light_Blue, 1);
    public static MyText documentaryText = new MyText("Documentary", 9, light_Blue, 1);
    public static MyText showTimes = new MyText("Show Times", 30, white_, 1);
    public static MyText comingSoon = new MyText("comingSoon", 30, white_, 1);


    public void changeColor(String color) {
        this.setForeground(Color.decode(color));
    }
}
