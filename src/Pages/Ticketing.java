package Pages;

import BackEnd.Movie;
import BackEnd.Showtimes;
import BackEnd.Ticket;
import CustomComponent.StaticClass.ChairLabel;
import CustomComponent.StaticClass.MyIcon;
import CustomComponent.StaticClass.MyPanels;
import CustomComponent.Text.MyText;
import CustomComponent.CustomPanel.PanelRound;
import CustomComponent.popDialgou.GlassPanePopup;
import CustomComponent.popDialgou.Message;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static CustomComponent.StaticClass.Properties.*;
import static Login.PanelLoginAndRegister.user;

public class Ticketing extends PanelRound {
    int chairNum = 0;
    ArrayList<Integer> addPos = new ArrayList<>();
    ArrayList<ChairLabel> addUpdater = new ArrayList<>();
    ArrayList<Integer> cancelPos = new ArrayList<>();
    ArrayList<ChairLabel> cancelUpdater = new ArrayList<>();

    ArrayList<Ticket> tickets;
    Showtimes showtimes;
    Movie movie;

    public void addChair(int pos, ChairLabel chairLabel) {
        chairNum++;
        this.addPos.add(pos);
        this.addUpdater.add(chairLabel);
    }

    public void minusChair(int pos, ChairLabel chairLabel) {
        chairNum--;
        this.addPos.remove((Integer) pos);
        this.addUpdater.add(chairLabel);

    }

    public void addCancel(int pos, ChairLabel chairLabel) {
        this.cancelPos.add((Integer) (pos));
        this.cancelUpdater.add(chairLabel);

    }

    public void minusCancel(int pos, ChairLabel chairLabel) {
        this.cancelPos.remove((Integer) pos);
        this.cancelUpdater.add(chairLabel);

    }


    private void book(MouseEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Message obj = new Message();
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("Click OK");
                BackEnd.Ticketing ticket = new BackEnd.Ticketing();
                ticket.bookTicket(user, addPos, movie, showtimes);
                addPos.clear();
                chairNum = 0;
                for (ChairLabel chairLabel : addUpdater) {
                    chairLabel.updateLabel();
                }
                GlassPanePopup.closePopupLast();
            }
        });
        GlassPanePopup.showPopup(obj);
    }

    private void cancel(MouseEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Message obj = new Message();
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("Click OK");
                BackEnd.Ticketing ticket = new BackEnd.Ticketing();
                ticket.cancelTicket(user, cancelPos, movie, showtimes);
                for (ChairLabel chairLabel : cancelUpdater) {
                    chairLabel.setBooked(false);
                    chairLabel.updateLabel();
                }
                cancelPos.clear();
                GlassPanePopup.closePopupLast();
            }
        });
        GlassPanePopup.showPopup(obj);
    }

    public Ticketing(Showtimes showtimes, Movie movie) {
        this.showtimes = showtimes;
        this.movie = movie;
        this.tickets = showtimes.getTickets();
        this.setRoundBottomLeft(40);
        this.setRoundBottomRight(40);
        this.setRoundTopLeft(40);
        this.setRoundTopRight(40);
        this.setBackground(dark_Gray);
        this.setLayout(new MigLayout("insets 3% 3% 3% 3%, wrap 6", "[]3%[]5%[]", "[]8%[]"));

        int cnt = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                ChairLabel chair = new ChairLabel(cnt, this, tickets.get(cnt));
                this.add(chair);
                cnt++;

            }
        }

        JLabel bookRightArrow = new JLabel(MyIcon.rightArrow);
        MyText bookTicket = new MyText("Book Now", 30, Color.WHITE, 0);
        JLabel unbookRightArrow = new JLabel(MyIcon.rightArrow);
        MyText cancelTicket = new MyText("Cancel!", 30, Color.WHITE, 0);
        this.add(bookTicket, "spanx 6, split 2,center");
        this.add(bookRightArrow, "center,wrap");
        this.add(cancelTicket, "spanx 6, split 2,center");
        this.add(unbookRightArrow, "center");
        MouseListener listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                user.viewMyTickets(user);
                book(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                bookTicket.setForeground(blue_);
                bookRightArrow.setIcon(MyIcon.clickedRightArrow);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                bookTicket.setForeground(white_);
                bookRightArrow.setIcon(MyIcon.rightArrow);
            }
        };

        bookRightArrow.addMouseListener(listener);
        bookTicket.addMouseListener(listener);

//        unBookListener

        MouseListener unBookListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cancel(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                cancelTicket.setForeground(blue_);
                unbookRightArrow.setIcon(MyIcon.clickedRightArrow);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                cancelTicket.setForeground(white_);
                unbookRightArrow.setIcon(MyIcon.rightArrow);
            }
        };
        unbookRightArrow.addMouseListener(unBookListener);
        cancelTicket.addMouseListener(unBookListener);
    }
}
