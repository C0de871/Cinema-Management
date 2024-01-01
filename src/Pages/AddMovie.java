package Pages;

import BackEnd.Cinema;
import CustomComponent.Button.ShadingButton;
import CustomComponent.Text.ShadingTextField;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

import static CustomComponent.StaticClass.Properties.light_Gray;
import static CustomComponent.Text.MyText.*;

public class AddMovie extends JPanel {
    public AddMovie() {
        ShadingTextField nameField = new ShadingTextField();
        ShadingTextField genreField = new ShadingTextField();
        ShadingTextField showTime1StartField = new ShadingTextField();
        ShadingTextField showTime1EndField = new ShadingTextField();
        ShadingTextField priceField = new ShadingTextField();
        ShadingTextField hallNumField = new ShadingTextField();
        ShadingTextField showTime2StartField = new ShadingTextField();
        ShadingTextField showTime2EndField = new ShadingTextField();
        ShadingTextField showTime3StartField = new ShadingTextField();
        ShadingTextField showTime3EndField = new ShadingTextField();

        ArrayList<String> time = new ArrayList<>();

        showTime1StartField.getText();
        showTime1EndField.getText();
        showTime2StartField.getText();
        showTime2EndField.getText();
        showTime3StartField.getText();
        showTime3EndField.getText();
        time.add(showTime1StartField.getText());
        time.add(showTime1EndField.getText());
        time.add(showTime2StartField.getText());
        time.add(showTime2EndField.getText());
        time.add(showTime3StartField.getText());
        time.add(showTime3EndField.getText());

   /*     Cinema c=new Cinema();
        c.addMovie("N", nameField.getText(), genreField.getText(),priceField.getText(), hallNumField.getText());*/
        this.setBackground(light_Gray);
        MigLayout layout = new MigLayout("insets 2%", "", "[]7%[]2%[]2%[]2%[]");
        this.setLayout(layout);
        this.add(addMovie, "wrap,spanx,center");
        this.add(movieTitle, "split 4 , spanx,center");
        this.add(nameField, "w 20%,gapafter 10%,center");

        this.add(movieGenre, "center");
        this.add(genreField, "w 20%,wrap,center");

        this.add(movieShowTime_1, "pushx,center");
        this.add(movieShowTime_2, "pushx,center");
        this.add(movieShowTime_3, "pushx,center,wrap");
//        layout.setLayoutConstraints(" debug ");
//        layout.setColumnConstraints("");
//        this.add(movieStart, " split 4 , spanx,gap before 13%,gap after 1%,sg 1");
        this.add(showTime1StartField, "pushx,center, w 15%");
        this.add(showTime2StartField, "pushx,center, w 15%");
        this.add(showTime3StartField, "pushx,center, w 15%,wrap");

//        this.add(movieEnd,"split 4 , spanx,gap before 13%,gap after 1%,sg 1");
        this.add(showTime1EndField, "pushx,center, w 15%");
        this.add(showTime2EndField, "pushx,center, w 15%");
        this.add(showTime3EndField, "pushx,center, w 15%,wrap");
        this.add(moviePrice, "split 4 , spanx,center");
        this.add(priceField, "w 20%,gapafter 10%,center");

        this.add(movieHallNum, "center");
        this.add(hallNumField, "w 20%,wrap,center");

        ShadingButton button = new ShadingButton();
        button.setBackground(new java.awt.Color(246, 246, 246));
        button.setShadowColor(new Color(170, 170, 170));
        button.setText("Add");
        this.add(button, "center,pushx,span,w 10%");
    }
}
