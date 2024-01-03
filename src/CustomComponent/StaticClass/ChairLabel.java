package CustomComponent.StaticClass;

import BackEnd.Ticket;
import Pages.Ticketing;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static Login.PanelLoginAndRegister.user;

//import static CustomComponent.StaticClass.MyPanels.user;

public class ChairLabel extends JLabel {
    boolean booked = false;
    boolean editable = true;
    //    boolean myChair=false;
    int cnt;
    Ticketing ticketing;
    Ticket ticket;
    boolean isActive;
    int serialNum;

    public ChairLabel(int cnt, Ticketing ticketing, Ticket ticket) {
        this.ticket = ticket;
        this.isActive = ticket.isActive();
        this.serialNum = ticket.getSerialNumber();
        this.cnt = cnt;
        this.ticketing = ticketing;
        System.out.println(user.has(serialNum));
        if (user.has(serialNum)) {
            this.booked = true;
            this.setIcon(MyIcon.myChair);// red  for the current user if he click on the book
        } else if (!this.isActive)
            this.setIcon(MyIcon.chairIcon);// light blue non booked
        else {
            this.editable = false;
            this.setIcon(MyIcon.bookedChair);// Blue
        }
        MouseListener listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (editable) {
                    if (user.has(serialNum)) {
                        if (booked) {
                            ChairLabel.this.setIcon(MyIcon.chairIcon);
                            booked = true;
                            ticketing.addCancel(cnt);
                        } else {
                            ChairLabel.this.setIcon(MyIcon.myChair);
                            booked = false;
                            ticketing.minusCancel(cnt);
                        }
                    } else if (!booked) {
                        ChairLabel.this.setIcon(MyIcon.bookedChair);
                        booked = true;
                        ticketing.addChair(cnt);
                    } else {
                        ChairLabel.this.setIcon(MyIcon.chairIcon);
                        booked = false;
                        ticketing.minusChair(cnt);
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
