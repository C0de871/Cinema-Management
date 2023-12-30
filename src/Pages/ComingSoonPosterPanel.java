package Pages;

import CustomComponent.MyIcon;
import CustomComponent.MyText;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static CustomComponent.Properties.*;

public class ComingSoonPosterPanel extends JLayeredPane {
    ComingSoonPosterPanel(JLabel label, MyText name, MyText duration, MyText genre) {
        this.setLayout(new MigLayout(" insets 0,gap 0"));
        this.setBackground(light_Gray);

        this.add(label, "span 2,wrap");
        this.add(name, "left,split 2,pushx,growx");
        this.add(duration, "right, wrap");
        this.add(genre);
        MouseListener labelListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

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
