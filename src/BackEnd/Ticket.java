package BackEnd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import static Pages.Main.halls;

public class Ticket implements Serializable {
    private static int nextSerialNumber;
    private int serialNumber;
    private int seatNumber;
    private Showtimes showtime;
    private Movie movie;
    private boolean active;
    private double ticketPrice;


    public Ticket() {
        this.serialNumber = nextSerialNumber++;
        this.active = false;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
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

    public Showtimes getShowtime() {
        return showtime;
    }

    public void setShowtime(Showtimes showtime) {
        this.showtime = showtime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getPosition() {
        return this.seatNumber;
    }

    @Override
    public String toString() {
        return super.toString() + "Ticket{" +
                "serialNumber=" + serialNumber +
                ", seatNumber=" + seatNumber +
                ", showtime=" + showtime +
                ", movie=" + movie +
                ", active=" + active +
                ", ticketPrice=" + ticketPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return serialNumber == ticket.serialNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, seatNumber, showtime, movie, active, ticketPrice);
    }
}
