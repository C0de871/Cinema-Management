package Pages;

import CustomComponent.Text.ShadingTextField;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import static CustomComponent.StaticClass.Properties.light_Gray;
import static CustomComponent.Text.MyText.*;

public class AddMovie extends JPanel {
    public AddMovie() {
        ShadingTextField nameField = new ShadingTextField();
        ShadingTextField genreField = new ShadingTextField();
        ShadingTextField startTime1StartField = new ShadingTextField();
        ShadingTextField showTime1EndField = new ShadingTextField();
        ShadingTextField priceField = new ShadingTextField();
        ShadingTextField hallNumField = new ShadingTextField();
        ShadingTextField startTime2StartField = new ShadingTextField();
        ShadingTextField showTime2EndField = new ShadingTextField();
        ShadingTextField startTime3StartField = new ShadingTextField();
        ShadingTextField showTime3EndField = new ShadingTextField();
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
        this.add(startTime1StartField, "pushx,center, w 15%");
        this.add(startTime2StartField, "pushx,center, w 15%");
        this.add(startTime3StartField, "pushx,center, w 15%,wrap");

//        this.add(movieEnd,"split 4 , spanx,gap before 13%,gap after 1%,sg 1");
        this.add(showTime1EndField, "pushx,center, w 15%");
        this.add(showTime2EndField, "pushx,center, w 15%");
        this.add(showTime3EndField, "pushx,center, w 15%,wrap");
        this.add(moviePrice, "split 4 , spanx,center");
        this.add(priceField, "w 20%,gapafter 10%,center");

        this.add(movieHallNum, "center");
        this.add(hallNumField, "w 20%,wrap,center");


    }
}
