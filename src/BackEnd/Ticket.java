package BackEnd;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Ticket implements Serializable {
    private static int nextSerialNumber = 1;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket ticket)) return false;
        return getSeatNumber() == ticket.getSeatNumber() && Double.compare(getTicketPrice(), ticket.getTicketPrice()) == 0 && Objects.equals(getShowtime(), ticket.getShowtime()) && Objects.equals(getMovie(), ticket.getMovie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSeatNumber(), getShowtime(), getMovie(), getTicketPrice());
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "serialNumber=" + serialNumber +
                ", seatNumber=" + seatNumber +
                ", showtime=" + showtime +
                ", movie=" + movie +
                ", active=" + active +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
