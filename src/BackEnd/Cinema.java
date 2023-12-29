package BackEnd;

import java.io.*;
import java.util.*;

class Cinema implements Serializable {
    private final int hallNum;
    private static int nexthallnum = 1;
    private List<Movie> movies;
// comment

    public Cinema() {
        this.hallNum = nexthallnum++;
        this.movies = new ArrayList<>();
        Map<String, Movie> movieMap = new HashMap<>();
    }

    public int getHallNum() {
        return hallNum;
    }


    public List<Movie> getMovies() {
        return movies;
    }

    void add(int hallNumToadd) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the number of showing times for the movie");
            int x = scanner.nextInt();
            ArrayList<Showtimes> show = new ArrayList<>();

            // Loop to get the start and end showtimes for the movie
            for (int i = 1; i <= x; i++) {
                System.out.println("Enter the " + i + " Start showtime of the movie");
                Date tS = Main.getUserDateTime();

                System.out.println("Enter the " + i + " End showtime of the movie");
                Date tE = Main.getUserDateTime();

                Showtimes s = new Showtimes(tS, tE);
                show.add(s);
            }
            System.out.println("Enter the title of the movie");
            String name = scanner.next();
            System.out.println("Enter the genre of the movie");
            String g = scanner.next();
            // Create a new BackEnd.Movie object with the provided details
            Movie m = new Movie(name, g, show);
            // Add the movie to the specified hall in the halls ArrayList
            InfoFiles f = new InfoFiles();
            Map<String, ArrayList<Movie>> moviesGenre;
            if (f.fileGenre.length() == 0) {
                moviesGenre = new HashMap<>();
                moviesGenre.put("action", new ArrayList<>());
                moviesGenre.put("Drama", new ArrayList<>());
                moviesGenre.put("comedy", new ArrayList<>());
                moviesGenre.put("adventure", new ArrayList<>());
                moviesGenre.put("documentary", new ArrayList<>());
                f.saveFileMovieGenre(moviesGenre);
            }
            moviesGenre = f.loadFileMovieGenre();
            moviesGenre.computeIfAbsent(g, k -> new ArrayList<>());
            moviesGenre.get(g).add(m);
            f.saveFileMovieGenre(moviesGenre);
            f.appendToFile(name, m);
            ArrayList<Cinema> hall = f.arrayOfObjectHallsLoad();
            if (hall.isEmpty()) {
                for (int i = 0; i < 5; i++) {
                    hall.add(new Cinema());
                }
            }

            // Load hall data
            hall.get(hallNumToadd - 1).getMovies().add(m);
            f.arrayOfObjectHallsSave(hall);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void deleteMovie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the title of the movie you want to delete");
        String title = scanner.next();

        InfoFiles f = new InfoFiles();
        ArrayList<Cinema> hall = f.arrayOfObjectHallsLoad();
        hall.forEach(h -> h.getMovies().removeIf(m -> m.getTitle().equals(title)));
        f.arrayOfObjectHallsSave(hall);
        Map<String, Movie> movies = f.loadFileMovie();
        String removedValue = String.valueOf(movies.remove(title));
        if (removedValue != null) {
            System.out.println(title + "BackEnd.Movie was removed");
        } else {
            System.out.println("there is no movie in this title");
        }
        f.saveFileMovie(movies);
    }

    void leaveComment(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the title of the movie you want to leave a comment for:");
        String title = scanner.nextLine();
        System.out.println("Enter your comment:");
        String comment = scanner.nextLine();
        InfoFiles f = new InfoFiles();
        Map<String, Movie> movies = f.loadFileMovie();
        movies.get(title).getComments().get(user).add(comment);
        f.saveFileMovie(movies);
    }

    void getMoviesAroundTime() {
        System.out.println("Enter the duration of the movie you want ");
        System.out.println("From Date:");
        Date startDate = Main.getUserDateTime(); // Get the start date from the user
        System.out.println("To Date:");
        Date endDate = Main.getUserDateTime(); // Get the end date from the user
        InfoFiles f = new InfoFiles();
        try {
            Map<String, Movie> movies = f.loadFileMovie();
            ArrayList<Movie> moviesBetweenDates = new ArrayList<>();
            for (Map.Entry<String, Movie> entry : movies.entrySet()) {
                boolean isMovieBetweenDates = entry.getValue().getShowtimes().stream()
                        .anyMatch(showtime -> showtime.getMovieStartTime().after(startDate) && showtime.getMovieStartTime().before(endDate));
                if (isMovieBetweenDates) {
                    moviesBetweenDates.add(entry.getValue());
                }
            }
            if (moviesBetweenDates.isEmpty()) {
                System.out.println("No results found");
            } else {
                for (Movie m : moviesBetweenDates) {
                    System.out.println(m); // Print the movies that match the criteria
                }
            }
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage()); // Handle any exceptions that occur during the process
        }
    }

    public void printAllMoviesGenre() {
        System.out.println("Enter the genre you want:");
        Scanner scanner = new Scanner(System.in);
        String genre = scanner.next();
        InfoFiles f = new InfoFiles();
        Map<String, ArrayList<Movie>> moviesGenre = f.loadFileMovieGenre();
        ArrayList<Movie> moviesGenreArray = moviesGenre.get(genre);

        if (moviesGenreArray == null || moviesGenreArray.isEmpty()) {
            System.out.println("No movies found for the genre \"" + genre + "\"");
        } else {
            System.out.println("Movies in the genre \"" + genre + "\":");
            for (Movie movie : moviesGenreArray) {
                System.out.println(movie);
            }
        }
    }


    void searchMovieByTitle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the title of the movie");
        String title = scanner.nextLine();
        InfoFiles f = new InfoFiles();
        Map<String, Movie> movies = f.loadFileMovie();
        Movie movie = movies.get(title);
        if (movie != null) {
            System.out.println("BackEnd.Movie found:");
            System.out.println(movie);
        } else {
            System.out.println("BackEnd.Movie not found");
        }
    }

    public void printAllMovies() {
        InfoFiles f = new InfoFiles();
        Map<String, Movie> movies = f.loadFileMovie();
        for (Map.Entry<String, Movie> entry : movies.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    void printAllMoviesInHalls() {
        InfoFiles f = new InfoFiles();
        ArrayList<Cinema> halls = f.arrayOfObjectHallsLoad();
        if (!halls.isEmpty()) {
            for (int i = 0; i < halls.size(); i++) {
                List<Movie> movies = halls.get(i).getMovies();
                if (!movies.isEmpty()) {
                    System.out.println("Movies in the " + (i + 1) + " hall:");
                    for (Movie movie : movies) {
                        System.out.println(movie);
                    }
                } else {
                    System.out.println("No movies found in the " + (i + 1) + " Hall");
                }
            }
        } else {
            System.out.println("No halls found.");
        }
    }
}