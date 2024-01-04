package BackEnd;

import java.io.Serializable;
import java.util.*;

import static Pages.Main.halls;

public class Movie implements Serializable {

    private String title;
    private String genre;
    private ArrayList<Integer> Rating;
    private Map<User, ArrayList<String>> comments = new HashMap<>();
    private String moviePath;
    private int MinutesOfMovie;
    private List<Showtimes> showtimes;

    public String getMoviePath() {
        return moviePath;
    }

    public void setMoviePath(String moviePath) {
        this.moviePath = moviePath;
    }

    private int hallNum;
    private int Booked;
    private double price;

    public double getPrice() {
        return price;
    }

    public int getHallNum() {
        return hallNum;
    }

    public Map<User, ArrayList<String>> getComments() {
        return comments;
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

    public Movie() {
    }

    public Movie(String title, String genre, List<Showtimes> showtimes, String moviePath, int hallNum, double price) {
        this.price = price;
        this.hallNum = hallNum;
        this.title = title;
        this.genre = genre;
        this.showtimes = showtimes;
        this.MinutesOfMovie = showtimes.get(0).getMovieDuration();
        this.moviePath = moviePath;
        Rating = new ArrayList<>();

        comments = new HashMap<>();

    }

    public int popularity(Movie movie) {
        try {
            InfoFiles f = new InfoFiles();
            //Map<String, Movie> moviesTitle = f.loadFileMovie();
            Movie selectedMovie = null;
            outer:
            for (Cinema hall : halls) {
                ArrayList<Movie> movies = (ArrayList<Movie>) hall.getMovies();
                for (Movie m : movies) {
                    if (m == movie) {
                        selectedMovie = m;
                        break outer;
                    }
                }
            }
            if (selectedMovie == null) {
                return 0;
            }

            return selectedMovie.getShowtimes()
                    .stream()
                    .mapToInt(Showtimes::bookedSeats)
                    .sum();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
        }
    }


    public double getAverageRating(Movie movie) {
        try {
            InfoFiles f = new InfoFiles();
            Movie selectedMovie = null;
            outer:
            for (Cinema hall : halls) {
                ArrayList<Movie> movies = (ArrayList<Movie>) hall.getMovies();
                for (Movie m : movies) {
                    if (m == movie) {
                        selectedMovie = m;
                        break outer;
                    }
                }
            }
            if (selectedMovie == null) {
                return 0.0;
            }

            ArrayList<Integer> ratings = selectedMovie.getRating();

            if (ratings == null || ratings.isEmpty()) {
                return 0.0;
            }

            int sum = ratings.stream().mapToInt(Integer::intValue).sum();
            return (double) sum / ratings.size();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while calculating the average rating.");
            return 0.0;
        }
    }

 /*   private void addRatingToMovie(Movie movie, int rating, InfoFiles f) {
        try {
            Map<String, Movie> movies = f.loadFileMovie();
            Movie selectedMovie = movies.get(movie.getTitle());
            if (selectedMovie != null) {
                selectedMovie.getRating().add(rating);
                f.saveFileMovie(movies);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while adding a rating to the movie.");
        }
    }*/

 /*   private void addRatingToGenreMovies(Map<String, ArrayList<Movie>> moviesGenre, Movie movie, int rating, InfoFiles f) {
        try {
            moviesGenre.get(movie.getGenre()).stream()
                    .filter(m -> m == movie)
                    .findFirst()
                    .ifPresent(m -> {
                        m.getRating().add(rating);
                        f.saveFileMovieGenre(moviesGenre);
                    });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while adding a rating to the genre movies.");
        }
    }*/

    private void addRatingToCinemaMovies(ArrayList<Cinema> halls, Movie movie, int rating, InfoFiles f) {
        try {
            halls.stream()
                    .flatMap(hall -> hall.getMovies().stream())
                    .filter(m -> m == movie)
                    .findFirst()
                    .ifPresent(m -> m.getRating().add(rating));
            f.arrayOfObjectHallsSave(halls);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while adding a rating to the cinema movies.");
        }
    }

    public void addRate(Movie movie) {
        InfoFiles f = new InfoFiles();
        System.out.println("Enter your Rate from 10");
        try (Scanner scanner = new Scanner(System.in)) {
            int rating = scanner.nextInt();
            // addRatingToMovie(movie, rating, f);
           /* Map<String, ArrayList<Movie>> moviesGenre = f.loadFileMovieGenre();
            addRatingToGenreMovies(moviesGenre, movie, rating, f);*/
            addRatingToCinemaMovies(halls, movie, rating, f);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while adding a rating.");
        }
    }


    public void displayComments(Movie movie) {
        InfoFiles f = new InfoFiles();
       /* Map<String, Movie> movieMap = f.loadFileMovie();
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
        }*/
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return MinutesOfMovie == movie.MinutesOfMovie && hallNum == movie.hallNum && Booked == movie.Booked && Double.compare(price, movie.price) == 0 && Objects.equals(title, movie.title) && Objects.equals(genre, movie.genre) && Objects.equals(comments, movie.comments) && Objects.equals(moviePath, movie.moviePath) && Objects.equals(showtimes, movie.showtimes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genre, comments, moviePath, MinutesOfMovie, showtimes, hallNum, Booked, price);
    }

    @Override
    public String toString() {
        return super.toString() + "Movie{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", MinutesOfMovie=" + MinutesOfMovie +
                ", hallNum=" + hallNum +
                ", price=" + price +
                '}';
    }
}