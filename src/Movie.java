import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    private static int nextMovieId = 1;
    private final int movieId;
    private String title;
    private String genre;
    private List<Showtimes> showtimes;

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

    public static void setNextMovieId(int nextMovieId) {
        Movie.nextMovieId = nextMovieId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setShowtimes(List<Showtimes> showtimes) {
        this.showtimes = showtimes;
    }

    void printShowtimes() {
        for (int i = 0; i < this.showtimes.size(); i++) {
            System.out.println((i + 1) + ". " + this.showtimes.get(i));
        }
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