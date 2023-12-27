import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Showtimes implements Serializable {
    private Date movieStartTime;
    private Date movieEndTime;
    private ArrayList<Ticket> tickets;

    void PrintAvailableSeats() {
        for (Ticket seat : tickets) {
            if (seat.isActive())
                System.out.println(seat);
        }
    }

    public boolean hasAvailableSeats(int numTickets) {
        int available = 0;
        for (Ticket seat : tickets) {
            if (seat.isActive())
                available++;
        }
        return available >= numTickets;
    }
    public void printAvailableSeats() {
        for (Ticket seat : tickets) {
            if (seat.isActive()) {
                System.out.println(seat);
            }
        }
    }
    public boolean cancelSeat(int seatNum) {
        if (tickets.get(seatNum-1).isActive()) {
            tickets.get(seatNum-1).setActive(false);
            return true;
        } else {
            System.out.println(" The Ticket is already not booked.");
            return false;
        }
    }
    public ArrayList<Ticket> bookSeats(int numTickets) {
        ArrayList<Ticket> bookingSeat = new ArrayList<>();
        for (Ticket seat : tickets) {
            if (numTickets == 0)
                break;
            if (seat.isActive()) {
                bookingSeat.add(seat);
                seat.setActive(false);
            }
        }
        return bookingSeat;
    }

    public Showtimes(Date movieStartTime, Date movieEndTime) {
        setMovieStartTime(movieStartTime);
        setMovieEndTime(movieEndTime);
        this.tickets = new ArrayList<>();
    }

    public Date getMovieStartTime() {
        return movieStartTime;
    }

    public void setMovieStartTime(Date movieStartTime) {
        if (movieStartTime != null) {
            this.movieStartTime = movieStartTime;
        } else {
            throw new IllegalArgumentException("Movie start time cannot be null.");
        }
    }

    public int getMovieDuration() {
        long startMillis = movieStartTime.getTime();
        long endMillis = movieEndTime.getTime();
        return (int) ((endMillis - startMillis) / (1000 * 60));
    }

    public boolean overlapsWith(Showtimes otherShowtimes) {
        long thisStartMillis = movieStartTime.getTime();
        long thisEndMillis = movieEndTime.getTime();
        long otherStartMillis = otherShowtimes.getMovieStartTime().getTime();
        long otherEndMillis = otherShowtimes.getMovieEndTime().getTime();

        return thisStartMillis < otherEndMillis && otherStartMillis < thisEndMillis;
    }

    public Date getMovieEndTime() {
        return movieEndTime;
    }

    public void setMovieEndTime(Date movieEndTime) {
        if (movieEndTime != null) {
            this.movieEndTime = movieEndTime;
        } else {
            throw new IllegalArgumentException("Movie end time cannot be null.");
        }
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    @Override
    public String toString() {
        return "Showtimes{" +
                "movieStartTime=" + movieStartTime +
                ", movieEndTime=" + movieEndTime +
                '}';
    }


}