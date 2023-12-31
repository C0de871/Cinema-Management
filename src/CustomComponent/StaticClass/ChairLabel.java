package CustomComponent.StaticClass;

import Pages.Ticketing;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChairLabel extends JLabel {
    boolean booked = false;
    boolean editable = true;
    int cnt;
    Ticketing ticketing;

    public ChairLabel(int cnt, Ticketing ticketing) {
        this.cnt = cnt;
        this.ticketing = ticketing;
        this.setIcon(MyIcon.chairIcon);
        MouseListener listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (editable) {
                    if (!booked) {
                        ChairLabel.this.setIcon(MyIcon.bookedChair);
                        booked = true;
                        ticketing.addChair();
                    } else {
                        ChairLabel.this.setIcon(MyIcon.chairIcon);
                        booked = false;
                        ticketing.minusChair();
                    }
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
//                ChairLabel.this.setIcon(MyIcon.bookedChair);
            }

            @Override
            public void mouseExited(MouseEvent e) {
//                ChairLabel.this.setIcon(MyIcon.chairIcon);

            }
        };
        this.addMouseListener(listener);
    }
}
