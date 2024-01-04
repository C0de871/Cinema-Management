package CustomComponent.StaticClass;

import BackEnd.Ticket;
import Pages.Ticketing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static Login.PanelLoginAndRegister.user;

public class ChairLabel extends JLabel {
    private boolean booked = false;
    private boolean editable = true;
    private int cnt;

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    private Ticketing ticketing;
    private Ticket ticket;
    private boolean isActive;
    private int serialNum;

    public ChairLabel(int cnt, Ticketing ticketing, Ticket ticket) {
        this.ticket = ticket;
        this.isActive = ticket.isActive();
        this.serialNum = ticket.getSerialNumber();
        this.cnt = cnt;
        this.ticketing = ticketing;
        updateLabel();

        MouseListener listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle mouse click based on the updated label state
                handleClick();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Handle mouse enter event
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Handle mouse exit event
            }
        };

        this.addMouseListener(listener);
    }

    public void updateLabel() {
        if (user.has(serialNum)) {
            this.booked = true;
            this.setIcon(MyIcon.myChair); // Red for the current user if they click on the book
        } else if (this.isActive) {
            this.setIcon(MyIcon.bookedChair);
            this.editable = false;
        } // Light blue non-booked
        else if (booked) {
            this.setIcon(MyIcon.bookedChair);
        } else if (!booked) {
            this.setIcon(MyIcon.chairIcon);
        }
    }

    private void handleClick() {
        if (editable) {
            if (user.has(serialNum)) {
                if (booked) {
                    this.setIcon(MyIcon.chairIcon);
                    booked = true;
                    ticketing.addCancel(cnt, this);
                } else {
                    this.setIcon(MyIcon.myChair);
                    booked = false;
                    ticketing.minusCancel(cnt, this);
                }
            } else if (!booked) {
                this.setIcon(MyIcon.bookedChair);
                booked = true;
                ticketing.addChair(cnt, this);
            } else {
                this.setIcon(MyIcon.chairIcon);
                booked = false;
                ticketing.minusChair(cnt, this);
            }
        }
    }
}
