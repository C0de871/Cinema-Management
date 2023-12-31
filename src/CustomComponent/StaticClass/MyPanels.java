package CustomComponent.StaticClass;

import BackEnd.User;
import CustomComponent.CustomPanel.AnimatedPanel;
import Login.LogInUp;
import Pages.*;
import Pages.Action;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

import static CustomComponent.StaticClass.MyLabels.*;
import static CustomComponent.StaticClass.MyLabels.heartIcon;
import static CustomComponent.Text.MyText.*;
import static CustomComponent.StaticClass.Properties.dark_Gray;

public class MyPanels {

    static Border matteBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#373737"));

    //The 4 Parents panels
    public static LeftSidePanels headPanel = new LeftSidePanels(new MigLayout("wrap,aligny center"), dark_Gray, matteBorder);
    public static LeftSidePanels middlePanel = new LeftSidePanels(new MigLayout("wrap, insets 0"), dark_Gray, matteBorder);
    public static LeftSidePanels lowerPanel = new LeftSidePanels(new MigLayout("wrap, insets 0"), dark_Gray);
    public static LeftSidePanels freePanel = new LeftSidePanels(new MigLayout("wrap", "push[center]push", "push[center]push"), dark_Gray);

    //The Titles Panel of each Parents panels
    public static LeftSidePanels header = new LeftSidePanels(new MigLayout("insets 0,aligny center", "15%[]0[]"), dark_Gray);
    public static LeftSidePanels main = new LeftSidePanels(new MigLayout("insets 0,aligny center", "15%[]10[]"), dark_Gray);
    public static LeftSidePanels genre = new LeftSidePanels(new MigLayout("insets 0,aligny center", "15%[]10[]"), dark_Gray);

    //The panels inside MainPanel
    public static AnimatedPanel discoverBar = new AnimatedPanel(new MigLayout("insets 0,gapx 0,aligny center", "15%[]10[]"), discoverIcon, discoverText);
    public static AnimatedPanel trendingBar = new AnimatedPanel(new MigLayout("insets 0,aligny center", "15%[]10[]"), trendingIcon, trendingText);
    public static AnimatedPanel upcomingBar = new AnimatedPanel(new MigLayout("insets 0,aligny center", "15%[]10[]"), upcomingIcon, upcomingText);
    public static AnimatedPanel collectionBar = new AnimatedPanel(new MigLayout("insets 0,aligny center", "15%[]10[]"), heartIcon, heartText);

    //the panels inside GenrePanel
    public static AnimatedPanel actionBar = new AnimatedPanel(new MigLayout("insets 0,aligny center", "15%[]10[]"), new JLabel(MyIcon.dot), actionText);
    public static AnimatedPanel dramaBar = new AnimatedPanel(new MigLayout("insets 0,aligny center", "15%[]10[]"), new JLabel(MyIcon.dot), dramaText);
    public static AnimatedPanel comedyBar = new AnimatedPanel(new MigLayout("insets 0,aligny center", "15%[]10[]"), new JLabel(MyIcon.dot), comedyText);
    public static AnimatedPanel adventureBar = new AnimatedPanel(new MigLayout("insets 0,aligny center", "15%[]10[]"), new JLabel(MyIcon.dot), adventureText);
    public static AnimatedPanel documentaryBar = new AnimatedPanel(new MigLayout("insets 0,aligny center", "15%[]10[]"), new JLabel(MyIcon.dot), documentaryText);
    public static LeftSideMenu leftSideMenu = new LeftSideMenu();
    public static DiscoverBody bodyPanel = new DiscoverBody();
    public static TrendingBody trendingBody = new TrendingBody();
    public static UpcomingBody upcomingBody = new UpcomingBody();
    public static InputShowStart show1 = new InputShowStart(movieShowTime_1);
    public static InputShowStart show2 = new InputShowStart(movieShowTime_2);
    public static InputShowStart show3 = new InputShowStart(movieShowTime_3);
    public static AddMovie addMovie = new AddMovie(DiscoverBody.showTimescontentPane);
    public static Action action = new Action();
    public static Drama drama = new Drama();
    public static Comedy comedy = new Comedy();
    public static Adventure adventure = new Adventure();
    public static Documentary documentary = new Documentary();
    //    public static MovieInfo movieInfo = new MovieInfo(); // temp
    public static Cardpanel cardPanel = new Cardpanel("Discover");
    public static ParentPanel parentPanel = new ParentPanel(new MigLayout("insets 0, gap 0"));


}
