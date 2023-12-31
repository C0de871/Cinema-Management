package BackEnd;

import java.io.Serializable;
import java.util.*;


public class Showtimes implements Serializable {
    private Date movieStartTime;
    private Date movieEndTime;
    private ArrayList<Ticket> tickets;
    public Showtimes(){}

    public Showtimes(Date movieStartTime, Date movieEndTime) {
        setMovieStartTime(movieStartTime);
        setMovieEndTime(movieEndTime);
        tickets = new ArrayList<>(Arrays.asList(new Ticket[30]));
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
        try {
            long startMillis = movieStartTime.getTime();
            long endMillis = movieEndTime.getTime();
            return (int) ((endMillis - startMillis) / (1000 * 60));
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("An error occurred while calculating movie duration. Start or end time is null.");
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An unexpected error occurred while calculating movie duration.");
            return 0;
        }
    }



    public Date getMovieEndTime() {
        return movieEndTime;
    }

    public void setMovieEndTime(Date movieEndTime) {
        if (movieEndTime != null) {
            this.movieEndTime = movieEndTime;
        } else {
            throw new IllegalArgumentException(" Movie end time cannot be null.");
        }
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    @Override
    public String toString() {
        return "BackEnd.Showtimes{" +
                "movieStartTime=" + movieStartTime +
                ", movieEndTime=" + movieEndTime +
                '}';
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public int getAvailableSeats(Showtimes showtimes) {
        try {
            int available = 0;
            ArrayList<Ticket> ticketArrayList = showtimes.getTickets();
            if (ticketArrayList != null) {
                for (Ticket t : ticketArrayList) {
                    if (t != null && !t.isActive()) {
                        available++;
                    }
                }
            }
            return available;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while calculating available seats.");
            return 0;
        }
    }

    public int bookedSeats() {
        try {
            return 30 - getAvailableSeats(this);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while calculating booked seats.");
            return 0;
        }
    }

}