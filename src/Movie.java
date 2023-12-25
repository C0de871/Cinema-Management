import java.util.List;

public class Movie {
    private static int nextMovieId = 1;
    private final int movieId;
    private final String title;
    private final String genre;
    private final List<Showtimes> showtimes;

    public Movie(String title, String genre, List<Showtimes> showtimes) {
        this.movieId = nextMovieId++; // Assign the next movieId and increment it
        this.title = title;
        this.genre = genre;
        this.showtimes = showtimes;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public List<Showtimes> getShowtimes() {
        return showtimes;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", showtimes=" + showtimes +
                '}';
    }
}