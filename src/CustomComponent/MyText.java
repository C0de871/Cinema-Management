package CustomComponent;

import javax.swing.*;
import java.awt.*;

import static CustomComponent.Properties.*;
import static CustomComponent.Properties.gray_;

public class MyText extends JLabel {
    public MyText(String text, int size, Color color, int fontStyle) {
        this.setText(text);
        this.setFont(new Font("sansserif",fontStyle, size));
        this.setForeground(color);
    }

    public static MyText appName = new MyText("Tr", 20, blue_, 1);
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
    public static MyText documentaryText = new MyText("documentary", 9, light_Blue, 1);
    public static MyText movieName = new MyText("Joker", 13, light_Blue, 1);
    public static MyText movieDuration = new MyText("120 min", 10, blue_, 0);
    public static MyText movieGenre = new MyText("Action/Adventure/Drama", 13, gray_, 0);
    public static MyText showTimes = new MyText("Show Times", 30, white_, 1);
    public static MyText comingSoon = new MyText("comingSoon", 30, white_, 1);



    void changeColor(String color){
        this.setForeground(Color.decode(color));
    }
}
