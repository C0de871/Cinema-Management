package Pages;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LeftSideMenu extends JPanel{
//    JPanel sideMenuPanel = new JPanel();
//        parentPanel.add(sideMenuPanel ,"left,pushy,growy,w 15%");
    String color;

    LeftSideMenu(){
        color="#B8BFF4";
        this.setLayout(new MigLayout("wrap,gapy 0,gapx 0,insets 0"));
        Border matteBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#373737"));

        JPanel headPanel = new JPanel(new MigLayout("wrap,aligny center"));
        headPanel.setBackground(Color.decode("#15151d"));
        headPanel.setBorder(matteBorder);

        JPanel middlePanel = new JPanel(new MigLayout("wrap, insets 0"));
        middlePanel.setBackground(Color.decode("#15151d"));
        middlePanel.setBorder(matteBorder);

        JPanel lowerPanel = new JPanel(new MigLayout("wrap, insets 0"));
        lowerPanel.setBackground(Color.decode("#15151d"));
//        lowerPanel.setBorder(matteBorder);

        JPanel freePanel= new JPanel(new MigLayout("wrap"));
        freePanel.setBackground(Color.decode("#15151d"));


        this.add(headPanel,"growx,growy,pushx,pushy, h 15%,wrap");
        this.add(middlePanel,"growx,growy,pushx,pushy, h 35%,wrap");
        this.add(lowerPanel,"growx,growy,pushx,pushy, h 35%,wrap");
        this.add(freePanel,"growx,growy,pushx,pushy, h 15%,wrap");

        JLabel discoverIcon = new JLabel(MyIcon.discover);
        JLabel trendingIcon = new JLabel(MyIcon.trending);
        JLabel upcomingIcon = new JLabel(MyIcon.upcoming);
        JLabel heartIcon = new JLabel(MyIcon.heart);
        JLabel logoIcon= new JLabel(MyIcon.logo);
        //
        MyText appName = new MyText("Tr",20,"#2585f8",1);
        MyText appName1 = new MyText("inkey",20,color,1);
        MyText discoverText = new MyText("Discover",13,color,1);
        MyText trendingText = new MyText("Trending",13,color,1);
        MyText upcomingText = new MyText("Upcoming",13,color,1);
        MyText heartText = new MyText("My Collection",13,color,1);
        MyText mainText= new MyText("Main", 11,color,1);
        MyText genreText= new MyText("Genre", 11,color,1);
        MyText actionText= new MyText("Action", 9,color,1);
        MyText dramaText= new MyText("Drama", 9,color,1);
        MyText comedyText= new MyText("Comedy", 9,color,1);
        MyText adventureText= new MyText("Adventure", 9,color,1);
        MyText documentaryText= new MyText("documentary", 9,color,1);



        JPanel header= new JPanel(new MigLayout( "insets 0,aligny center","15%[]0[]"));
        header.setBackground(Color.decode("#15151d"));
        header.add(logoIcon);
        header.add(appName);
        header.add(appName1);

        JPanel main = new JPanel(new MigLayout("insets 0,aligny center","15%[]10[]"));
        main.setBackground(Color.decode("#15151d"));
        main.add(mainText);
        //
        AnimatedPanel discoverBar= new AnimatedPanel(new MigLayout("insets 0,gapx 0,aligny center","15%[]10[]"), discoverIcon, discoverText);
        AnimatedPanel trendingBar= new AnimatedPanel(new MigLayout("insets 0,aligny center","15%[]10[]"), trendingIcon, trendingText);
        AnimatedPanel upcomingBar= new AnimatedPanel(new MigLayout("insets 0,aligny center","15%[]10[]"), upcomingIcon, upcomingText);
        AnimatedPanel collectionBar= new AnimatedPanel(new MigLayout("insets 0,aligny center","15%[]10[]"), heartIcon, heartText);
        //
        JPanel genre = new JPanel(new MigLayout("insets 0,aligny center","15%[]10[]"));
        genre.setBackground(Color.decode("#15151d"));
        genre.add(genreText);

        AnimatedPanel actionBar= new AnimatedPanel(new MigLayout("insets 0,aligny center","15%[]10[]"), new JLabel(MyIcon.dot), actionText);
        AnimatedPanel dramaBar= new AnimatedPanel(new MigLayout("insets 0,aligny center","15%[]10[]"), new JLabel(MyIcon.dot), dramaText);
        AnimatedPanel comedyBar= new AnimatedPanel(new MigLayout("insets 0,aligny center","15%[]10[]"), new JLabel(MyIcon.dot), comedyText);
        AnimatedPanel adventureBar= new AnimatedPanel(new MigLayout("insets 0,aligny center","15%[]10[]"), new JLabel(MyIcon.dot), adventureText);
        AnimatedPanel documentaryBar= new AnimatedPanel(new MigLayout("insets 0,aligny center","15%[]10[]"), new JLabel(MyIcon.dot), documentaryText);
//
//
        headPanel.add(header, "growx,pushx");
        middlePanel.add(main,"growx,pushx,h 15%");
        middlePanel.add(discoverBar, "growx,pushx,h 21%");
        middlePanel.add(trendingBar, "growx,pushx,h 21%");
        middlePanel.add(upcomingBar, "growx,pushx,h 21%");
        middlePanel.add(collectionBar, "growx,pushx,h 21%");
        lowerPanel.add(genre, "growx,pushx,h 15%");
        lowerPanel.add(actionBar, "growx,pushx,h 17%");
        lowerPanel.add(dramaBar, "growx,pushx,h 17%");
        lowerPanel.add(comedyBar, "growx,pushx,h 17%");
        lowerPanel.add(adventureBar, "growx,pushx,h 17%");
        lowerPanel.add(documentaryBar, "growx,pushx,h 17%");
    }

}
