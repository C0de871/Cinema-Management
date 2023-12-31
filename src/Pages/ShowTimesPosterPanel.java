package Pages;

import CustomComponent.Text.MyText;
import CustomComponent.CustomPanel.Oval;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static CustomComponent.StaticClass.MyPanels.cardPanel;
import static CustomComponent.StaticClass.Properties.*;

public class ShowTimesPosterPanel extends JLayeredPane {
    Oval rate;

    ShowTimesPosterPanel(JLabel label, MyText name, MyText duration, MyText genre,String rate) {
        this.setLayout(new MigLayout("insets 0,gap 0"));
        this.rate = new Oval(rate);
        this.setBackground(light_Gray);

        this.add(this.rate, "w 50,h 50,pos 155 170");
        this.add(label, "span 2,wrap, endgroupx grp1");
        this.add(name, "left");
        this.add(duration, "wrap, endgroupx grp1,right");
        this.add(genre);


        MouseListener labelListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardPanel.m();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        };
        MouseListener nameListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardPanel.m();


            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                name.changeColor("#2585f8");
                setCursor(new Cursor(Cursor.HAND_CURSOR));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                name.changeColor("#B8BFF4");
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        };
        label.addMouseListener(labelListener);
        name.addMouseListener(nameListener);


    }
}
