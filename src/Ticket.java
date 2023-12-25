import java.util.Date;

public class Ticket {
    private int seatNumber;
    private Date showtime;
    private double ticketPrice;

    public Ticket(int seatNumber, Date showtime, double ticketPrice) {
        this.seatNumber = seatNumber;
        this.showtime = showtime;
        this.ticketPrice = ticketPrice;
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
        return "Ticket{" +
                "seatNumber=" + seatNumber +
                ", showtime=" + showtime +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}