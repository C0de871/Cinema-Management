package BackEnd;

import java.io.Serializable;
import java.util.Date;

public class Ticket implements Serializable {
    private int seatNumber;
    private Date showtime;
    private boolean active=false;
    private double ticketPrice;

    public Ticket(int seatNumber, Date showtime, double ticketPrice) {
        this.seatNumber = seatNumber;
        this.showtime = showtime;
        this.ticketPrice = ticketPrice;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Date getShowtime() {
        return showtime;
    }

    public void setShowtime(Date showtime) {
        this.showtime = showtime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "BackEnd.Ticket{" +
                "seatNumber=" + seatNumber +
                ", showtime=" + showtime +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}