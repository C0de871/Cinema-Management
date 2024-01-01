package BackEnd;

public class ComingSoon {
    private String title;
    private String genre;
    private String moviePath;
    private int MinutesOfMovie;

    public ComingSoon(String title, String genre, String moviePath, int minutesOfMovie) {
        this.title = title;
        this.genre = genre;
        this.moviePath = moviePath;
        MinutesOfMovie = minutesOfMovie;
    }
    public ComingSoon(String title, String genre, String moviePath) {
        this.title = title;
        this.genre = genre;
        this.moviePath = moviePath;
        this.MinutesOfMovie = 0;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getMoviePath() {
        return moviePath;
    }

    public void setMoviePath(String moviePath) {
        this.moviePath = moviePath;
    }

    public int getMinutesOfMovie() {
        return MinutesOfMovie;
    }

    public void setMinutesOfMovie(int minutesOfMovie) {
        MinutesOfMovie = minutesOfMovie;
    }
}

