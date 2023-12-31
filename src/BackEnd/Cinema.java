package BackEnd;

import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static Pages.Main.halls;
import static Pages.Main.users;

public class Cinema implements Serializable {
    private final int hallNum;
    private static int nexthallnum = 1;
    private List<Movie> movies;
    private final InfoFiles infoFiles;
// comment

    public Cinema() {
        this.hallNum = nexthallnum++;
        this.movies = new ArrayList<>();
        this.infoFiles = new InfoFiles();
    }

    public int getHallNum() {
        return hallNum;
    }


    public List<Movie> getMovies() {
        return movies;
    }

    public void addMovie(String type, String name, String g, double p, int h, ArrayList<String> showtimes, String path) {
        Scanner scanner = new Scanner(System.in);
        InfoFiles f = new InfoFiles();
        if (type != "C") {
            try {

                ArrayList<Showtimes> show = new ArrayList<>();
                for (int i = 0; i < showtimes.size(); i += 4) {
                    String dateStart = showtimes.get(i);
                    String timeStart = showtimes.get(i + 1);
                    Date Start = Main.getUserDateTime(dateStart, timeStart);

                    String dateEnd = showtimes.get(i + 2);
                    String timeEnd = showtimes.get(i + 3);
                    Date End = Main.getUserDateTime(dateEnd, timeEnd);
                    Showtimes showtimes1 = new Showtimes(Start, End);
                    show.add(showtimes1);

                }
                System.out.println("Date added!");
                Movie m = new Movie(name, g, show, path, h, p);
                Map<String, ArrayList<Movie>> moviesGenre;

           /*     moviesGenre = f.loadFileMovieGenre();
                moviesGenre.computeIfAbsent(g, k -> new ArrayList<>());
                moviesGenre.get(g).add(m);
                f.saveFileMovieGenre(moviesGenre);
                f.appendToFile(name, m);*/
                if (halls.isEmpty()) {
                    for (int i = 0; i < 5; i++) {
                        halls.add(new Cinema());
                    }
                }
                // Load halls data
                halls.get(h - 1).getMovies().add(m);
                f.arrayOfObjectHallsSave(halls);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            ComingSoon c = new ComingSoon(name, g, "Nothing");
            f.appendToFileComing(name, c);
        }
    }

    void deleteMovie(String title) {

        InfoFiles f = new InfoFiles();
        halls.forEach(h -> h.getMovies().removeIf(m -> m.getTitle().equals(title)));
        f.arrayOfObjectHallsSave(halls);
      /*  Map<String, Movie> movies = f.loadFileMovie();
        String removedValue = String.valueOf(movies.remove(title));
        Map<String, ArrayList<Movie>> movieGenre = f.loadFileMovieGenre();
        for (Map.Entry<String, ArrayList<Movie>> entry : movieGenre.entrySet()) {
            entry.getValue().removeIf(movie -> movie.getTitle().equals(title));
        }
        f.saveFileMovieGenre(movieGenre);
        f.saveFileMovie(movies);*/
    }

    public void leaveComment(User user, Movie movie, String comment) {
        Scanner scanner = new Scanner(System.in);
        try {
        /*    updateMovieComments(movie, user, comment);
            updateGenreComments(movie, user, comment);*/
            updateHallsComments(movie, user, comment);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while leaving a comment.");
        }
    }

  /*  private void updateMovieComments(Movie movie, User user, String comment) {
        try {
            Map<String, Movie> moviesTitle = infoFiles.loadFileMovie();
            if (moviesTitle.get(movie.getTitle()).getComments().get(user) == null) {
                ArrayList<String> com = new ArrayList<>();
                com.add(comment);
                moviesTitle.get(movie.getTitle()).getComments().put(user, com);
                System.out.println("comment added");

            } else {
                moviesTitle.get(movie.getTitle()).getComments().get(user).add(comment);
                System.out.println("comment added");
            }
            infoFiles.saveFileMovie(moviesTitle);
        } catch (Exception e) {
            // Handle the exception, e.g., log it or display an error message
            e.printStackTrace();
            System.out.println("An error occurred while updating movie comments.");
        }
    }*/

/*    private void updateGenreComments(Movie movie, User user, String comment) {
        try {
            Map<String, ArrayList<Movie>> moviesGenre = infoFiles.loadFileMovieGenre();

            if (moviesGenre != null) {
                moviesGenre.entrySet().stream()
                        .filter(entry -> entry.getKey().equals(movie.getGenre()))
                        .flatMap(entry -> entry.getValue().stream())
                        .filter(m -> m == movie)
                        .findFirst()
                        .ifPresent(m -> {
                            m.getComments().get(user).add(comment);
                            infoFiles.saveFileMovieGenre(moviesGenre);
                        });
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while updating genre comments.");
        }
    }*/

    private void updateHallsComments(Movie movie, User user, String comment) {
        try {
            if (halls != null) {
                halls.stream()
                        .flatMap(hall -> hall.getMovies().stream())
                        .filter(m -> m == movie)
                        .findFirst()
                        .ifPresent(m -> {
                            if (m.getComments().get(user) == null) {
                                m.getComments().put(user, new ArrayList<>());
                            }
                            m.getComments().get(user).add(comment);
                            infoFiles.arrayOfObjectHallsSave(halls);
                        });
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while updating halls comments.");
        }
    }


/*    void getMoviesAroundTime() {
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
    }*/

   /* public ArrayList<Movie> getAllMoviesGenre(String genre) {

        InfoFiles f = new InfoFiles();
        if (Objects.equals(genre, "general"))
            return getAllMovies();

        Map<String, ArrayList<Movie>> moviesGenre = f.loadFileMovieGenre();
        ArrayList<Movie> moviesGenreArray = moviesGenre.get(genre);

        if (moviesGenreArray == null || moviesGenreArray.isEmpty()) {
            return new ArrayList<>();
        } else {
            return moviesGenreArray;
        }
    }*/

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    ArrayList<Movie> searchMovieByTitle(String title) {
        Scanner scanner = new Scanner(System.in);
        try {

            InfoFiles f = new InfoFiles();
            ArrayList<Movie> movies = new ArrayList<>();
            for (Cinema hall : halls) {
                ArrayList<Movie> hallMovies = (ArrayList<Movie>) hall.getMovies();
                for (Movie movie : hallMovies) {
                    if (movie.getTitle().contains(title))
                        movies.add(movie);
                }
            }
            return movies;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while searching for movies by title.");
            return new ArrayList<>();
        }
    }

    public ArrayList<Movie> getAllMovies() {
        try {
            InfoFiles f = new InfoFiles();
            ArrayList<Movie> movies = new ArrayList<>();
            for (Cinema hall : halls) {
                movies.addAll(hall.getMovies());
            }
            return movies;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    void printAllMoviesInHalls() {
        InfoFiles f = new InfoFiles();
        if (!halls.isEmpty()) {
            for (int i = 0; i < halls.size(); i++) {
                List<Movie> movies = halls.get(i).getMovies();
                if (!movies.isEmpty()) {
                    System.out.println("Movies in the " + (i + 1) + " hall:");
                    for (Movie movie : movies) {
                    }
                } else {
                    System.out.println("No movies found in the " + (i + 1) + " Hall");
                }
            }
        } else {
            System.out.println("No halls found.");
        }
    }

    void addFavorite(User user, Movie movie) {
        try {
            InfoFiles f = new InfoFiles();


            for (User u : users) {
                if (u == user) {
                    u.getFavorite().add(movie);
                    break;
                }
            }

            f.saveToFileAccounts(users, f.fileUser);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while adding a favorite.");
        }
    }

    void removeFavorite(User user, Movie movie) {
        try {
            InfoFiles f = new InfoFiles();


            users.stream()
                    .filter(u -> u.equals(user))
                    .findFirst()
                    .ifPresent(u -> u.getFavorite().remove(movie));

            f.saveToFileAccounts(users, f.fileUser);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while removing a favorite.");
        }
    }
}