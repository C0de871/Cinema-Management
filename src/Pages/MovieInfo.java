package Pages;

import CustomComponent.MyIcon;
import CustomComponent.MyText;
import CustomComponent.PanelRound;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static CustomComponent.MyIcon.createRoundedImageIcon;
import static CustomComponent.MyIcon.originalImage;
import static CustomComponent.MyPanels.cardPanel;
import static CustomComponent.Properties.cardLayout;
import static CustomComponent.Properties.dark_Gray;

public class MovieInfo extends PanelRound {
    Border matteBorder = BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode("#373737"));

    public MovieInfo() {
        this.setRoundBottomLeft(40);
        this.setRoundBottomRight(40);
        this.setRoundTopLeft(40);
        this.setRoundTopRight(40);
        this.setBackground(dark_Gray);
        this.setLayout(new MigLayout("insets 3% 3% 3% 3%", "[]3%[]5%[]", "[]8%[]"));
        MyText name = new MyText("The Joker", 50, Color.white, 1);
        MyText rate = new MyText("7.5", 14, Color.YELLOW, 1);
        MyText duration = new MyText("01:35:00", 12, Color.WHITE, 0);
        MyText hallNumber = new MyText("1", 12, Color.WHITE, 0);
        MyText bookTicket = new MyText("Book Now", 30, Color.WHITE, 0);
        MyText price = new MyText("100$", 12, Color.white, 0);
        MyText length = new MyText("Length", 10, Color.white, 0);
        MyText hall = new MyText("Hall", 10, Color.white, 0);
        MyText pay = new MyText("Price", 10, Color.white, 0);
        MyIcon.joker = createRoundedImageIcon(originalImage, 150, 200, 320);
        JLabel poster = new JLabel(MyIcon.joker);
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
        lengthPanel.add(duration, "center");
        lengthPanel.setBorder(matteBorder);
        JPanel hallPanel = new JPanel(new MigLayout());
        hallPanel.setBackground(dark_Gray);

        hallPanel.add(hallIcon, "spany2");
        hallPanel.add(hall, "wrap,center");
        hallPanel.add(hallNumber, "center");
        hallPanel.setBorder(matteBorder);
        JPanel pricePanel = new JPanel(new MigLayout());
        pricePanel.setBackground(dark_Gray);

        pricePanel.add(priceIcon, "spany2");
        pricePanel.add(pay, "wrap,center");
        pricePanel.add(price, "center");
        this.add(poster, "spany 3");
        this.add(name, "growx,pushx");
        this.add(heartIcon, "left,wrap");
        this.add(starIcon, "split 2");
        this.add(rate, "wrap ,pushy");
        this.add(lengthPanel, "split 3,bottom,w 13%,h 13%");
        this.add(hallPanel, "bottom,w 13%,h 13%");
        this.add(pricePanel, "bottom,w 13%,h 13%");
        this.add(bookTicket, "split 2,center");
        this.add(rightArrow, "center");
    }
}
