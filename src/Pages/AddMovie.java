package Pages;

import BackEnd.Cinema;
import CustomComponent.Button.ShadingButton;
import CustomComponent.Text.ShadingTextField;
import jnafilechooser.api.JnaFileChooser;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static CustomComponent.StaticClass.MyPanels.*;
import static CustomComponent.StaticClass.Properties.light_Gray;
import static CustomComponent.Text.MyText.*;
import static CustomComponent.Text.MyText.addMovie;

import CustomComponent.CustomComboBox.Combobox;

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


        this.setBackground(light_Gray);
        MigLayout layout = new MigLayout("insets 2%", "", "[]7%[]7%[]7%[]14%[]");
        this.setLayout(layout);
        this.add(addMovie, "wrap,spanx,center,pushx");
        this.add(movieTitle, "split 4 , spanx,center");
        this.add(nameField, "w 20%,gapafter 10%,center");

        Combobox genre = new Combobox();
        genre.setRenderer(new DefaultListCellRenderer() {
            @Override
            public void setBackground(Color c) {

                super.setBackground(new Color(0, 0, 0, 0));
            }
        });
        genre.setModel(new DefaultComboBoxModel(new String[]{"action", "drama", "comedy", "adventure", "documentary"}));
        genre.setSelectedIndex(-1);
        genre.setLabeText("Genre");
        genre.setBackground(light_Gray);

//        genre.set
        this.add(genre, "center,wrap,w 20%");
//        this.add(genreField, "w 20%,wrap,center");
        this.add(show1, "center,w 20%,spanx,split 3,gap after 5%");
        this.add(show2, "center,w 20%,gap after 5%");
        this.add(show3, "center,w 20%,wrap");
        this.add(moviePrice, "split 4 , spanx,center");
        this.add(priceField, "w 20%,gapafter 10%,center");

        this.add(movieHallNum, "center");
        this.add(hallNumField, "w 20%,wrap,center");

        ShadingButton button = new ShadingButton();
        button.setBackground(new java.awt.Color(29, 162, 253));
        button.setForeground(new java.awt.Color(245, 245, 245));
        button.setRippleColor(new java.awt.Color(255, 255, 255));
        button.setShadowColor(new java.awt.Color(29, 162, 253));
        button.setText("Add");
        this.add(button, "center,pushx,span,w 10%");
        MouseListener listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ArrayList<String> showtimes = new ArrayList<>();
                showtimes.addAll(show1.showTimeInfo());
                showtimes.addAll(show2.showTimeInfo());
                showtimes.addAll(show3.showTimeInfo());
                String path = null;
                JnaFileChooser chooser = new JnaFileChooser();
                boolean action = chooser.showOpenDialog(null);
                if (action) {
                    path = String.valueOf(chooser.getSelectedFile());
                }
                Cinema cinema = new Cinema();
                if (nameField.getText() != null && genre.getSelectedItem() != null && priceField.getText() != null && hallNumField.getText() != null) {
                    cinema.addMovie("N", nameField.getText(), (String) genre.getSelectedItem(), Double.parseDouble(priceField.getText()), Integer.parseInt(hallNumField.getText()), showtimes, path);
                }

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
        button.addMouseListener(listener);
    }
}
