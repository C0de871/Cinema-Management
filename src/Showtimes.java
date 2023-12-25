import java.util.Date;
public class Showtimes {
    private Date movieStartTime;
    private Date movieEndTime;

    public Showtimes(Date movieStartTime, Date movieEndTime) {
        setMovieStartTime(movieStartTime);
        setMovieEndTime(movieEndTime);
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

    @Override
    public String toString() {
        return "Showtimes{" +
                "movieStartTime=" + movieStartTime +
                ", movieEndTime=" + movieEndTime +
                '}';
    }
}