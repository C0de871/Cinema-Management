package BackEnd;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Movie implements Serializable {
    private static int nextMovieId = 1;
    private final int movieId;
    private String title;
    private String genre;
    private ArrayList<Integer> Rating;
    private Map<User, ArrayList<String>> Comments;
    private int MinutesOfMovie;
    private List<Showtimes> showtimes;

    public Map<User, ArrayList<String>> getComments() {
        return Comments;
    }


    public static int getNextMovieId() {
        return nextMovieId;
    }

    public ArrayList<Integer> getRating() {
        return Rating;
    }

    public void setRating(ArrayList<Integer> rating) {
        Rating = rating;
    }

    public int getMinutesOfMovie() {
        return MinutesOfMovie;
    }

    public void setMinutesOfMovie(int minutesOfMovie) {
        MinutesOfMovie = minutesOfMovie;
    }

    public Movie(String title, String genre, List<Showtimes> showtimes) {
        this.movieId = nextMovieId++; // Assign the next movieId and increment it
        this.title = title;
        this.genre = genre;
        this.showtimes = showtimes;
        this.MinutesOfMovie = showtimes.get(0).getMovieDuration();
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

    void addRating(int Rate) {
        this.Rating.add(Rate);
    }

    public double getAverageRating(Movie movie) {
        InfoFiles f = new InfoFiles();
        Map<String, Movie> movies = f.loadFileMovie();
        Movie selectedMovie = movies.get(movie.getTitle());

        if (selectedMovie == null) {
            return 0.0;
        }

        List<Integer> ratings = selectedMovie.getRating();

        if (ratings.isEmpty()) {
            return 0.0;
        }

        int sum = ratings.stream().mapToInt(Integer::intValue).sum();
        return (double) sum / ratings.size();
    }

    private void addRatingToMovie(Movie movie, int rating, InfoFiles f) {
        Map<String, Movie> movies = f.loadFileMovie();
        Movie selectedMovie = movies.get(movie.getTitle());
        if (selectedMovie != null) {
            selectedMovie.addRating(rating);
            f.saveFileMovie(movies);
        }
    }

    private void addRatingToGenreMovies(Map<String, ArrayList<Movie>> moviesGenre, Movie movie, int rating, InfoFiles f) {
        moviesGenre.get(movie.getGenre()).stream()
                .filter(m -> m == movie)
                .findFirst()
                .ifPresent(m -> {
                    m.addRating(rating);
                    f.saveFileMovieGenre(moviesGenre);
                });
    }

    private void addRatingToCinemaMovies(ArrayList<Cinema> halls, Movie movie, int rating, InfoFiles f) {
        halls.stream()
                .flatMap(hall -> hall.getMovies().stream())
                .filter(m -> m == movie)
                .findFirst()
                .ifPresent(m -> m.addRating(rating));
        f.arrayOfObjectHallsSave(halls);
    }

    public void addRate(Movie movie) {
        InfoFiles f = new InfoFiles();
        System.out.println("Enter your Rate from 10");
        try (Scanner scanner = new Scanner(System.in)) {
            int rating = scanner.nextInt();
            addRatingToMovie(movie, rating, f);
            Map<String, ArrayList<Movie>> moviesGenre = f.loadFileMovieGenre();
            addRatingToGenreMovies(moviesGenre, movie, rating, f);

            ArrayList<Cinema> halls = f.arrayOfObjectHallsLoad();
            addRatingToCinemaMovies(halls, movie, rating, f);
        }
    }

    public void displayComments(Movie movie) {
        InfoFiles f = new InfoFiles();
        Map<String, Movie> movieMap = f.loadFileMovie();
        Movie selectedMovie = movieMap.get(movie.getTitle());
        if (selectedMovie != null) {
            Map<User, ArrayList<String>> comments = selectedMovie.getComments();
            for (Map.Entry<User, ArrayList<String>> entry : comments.entrySet()) {
                System.out.println(entry.getKey().getName() + " :");
                ArrayList<String> commentList = entry.getValue();
                for (String comment : commentList) {
                    System.out.println(comment);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", Rating=" + Rating +
                ", Comments=" + Comments +
                ", MinutesOfMovie=" + MinutesOfMovie +
                ", showtimes=" + showtimes +
                '}';
    }

}