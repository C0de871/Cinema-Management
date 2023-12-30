package BackEnd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Showtimes implements Serializable {
    private Date movieStartTime;
    private Date movieEndTime;
    private ArrayList<ArrayList<Ticket>> tickets;

    void PrintAvailableSeats() {
        for (ArrayList<Ticket> row : tickets) {
            for (Ticket seat : row) {
                if (seat.isActive()) {
                    System.out.println(seat);
                }
            }
        }
    }
    public int bookedSeats() {
        int booked = 0;
        for (ArrayList<Ticket> row : tickets) {
            for (Ticket seat : row) {
                if (seat.isActive()) {
                   booked++;
                }
            }
        }
        return booked ;
    }
    public boolean hasAvailableSeats(int numTickets) {
        int available = 0;
        for (ArrayList<Ticket> row : tickets) {
            for (Ticket seat : row) {
                if (seat.isActive()) {
                    available++;
                }
            }
        }
        return available >= numTickets;
    }
    public boolean cancelSeat(int row, int colm) {
        if (row >= 0 && row < tickets.size()) {
            ArrayList<Ticket> rowSeats = tickets.get(row);

            if (colm >= 1 && colm <= rowSeats.size()) {
                Ticket seat = rowSeats.get(colm - 1);

                if (seat.isActive()) {
                    seat.setActive(false);
                    return true;
                } else {
                    System.out.println("The Ticket is already not booked.");
                    return false;
                }
            } else {
                System.out.println("Incvalid seat number.");
                return false;
            }
        } else {
            System.out.println("Invalid row number.");
            return false;
        }
    }

    public ArrayList<Ticket> bookSeats(int numTickets) {
        ArrayList<Ticket> bookingSeats = new ArrayList<>();

        for (ArrayList<Ticket> rowSeats : tickets) {
            for (Ticket seat : rowSeats) {
                if (numTickets == 0) {
                    break;
                }

                if (seat.isActive()) {
                    bookingSeats.add(seat);
                    seat.setActive(false);
                    numTickets--;
                }
            }
        }

        return bookingSeats;
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
            throw new IllegalArgumentException("BackEnd.Movie start time cannot be null.");
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
            throw new IllegalArgumentException("BackEnd.Movie end time cannot be null.");
        }
    }

    public ArrayList<ArrayList<Ticket>> getTickets() {
        return tickets;
    }

    @Override
    public String toString() {
        return "BackEnd.Showtimes{" +
                "movieStartTime=" + movieStartTime +
                ", movieEndTime=" + movieEndTime +
                '}';
    }

}