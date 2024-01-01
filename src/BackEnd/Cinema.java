package BackEnd;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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

    void addMovie(String type,String name,String g,double p,int hallnum,ArrayList<Showtimes>show) {
        Scanner scanner = new Scanner(System.in);
        InfoFiles f = new InfoFiles();
        if (type != "C") {
            try {

                for (int i = 1; i <= 3; i++) {
                    System.out.println("Enter the " + i + " Start showtime of the movie");
                    Date tS = Main.getUserDateTime();

                    System.out.println("Enter the " + i + " End showtime of the movie");
                    Date tE = Main.getUserDateTime();

                    Showtimes s = new Showtimes(tS, tE);
                    show.add(s);
                }
                Movie m = new Movie(name, g, show, "anything", hallNum, p);
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
                hall.get(hallNum - 1).getMovies().add(m);
                f.arrayOfObjectHallsSave(hall);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            ComingSoon c = new ComingSoon(name, g, "Nothing" );
            f.appendToFileComing(name, c);
        }
    }

    void deleteMovie(String title) {

        InfoFiles f = new InfoFiles();
        ArrayList<Cinema> hall = f.arrayOfObjectHallsLoad();
        hall.forEach(h -> h.getMovies().removeIf(m -> m.getTitle().equals(title)));
        f.arrayOfObjectHallsSave(hall);
        Map<String, Movie> movies = f.loadFileMovie();
        String removedValue = String.valueOf(movies.remove(title));
        Map<String, ArrayList<Movie>> movieGenre = f.loadFileMovieGenre();
        for (Map.Entry<String, ArrayList<Movie>> entry : movieGenre.entrySet()) {
            entry.getValue().removeIf(movie -> movie.getTitle().equals(title));
        }
        f.saveFileMovieGenre(movieGenre);
        f.saveFileMovie(movies);
    }

    public void leaveComment(User user, Movie movie, String comment) {
        Scanner scanner = new Scanner(System.in);
        try {
            updateMovieComments(movie, user, comment);
            updateGenreComments(movie, user, comment);
            updateHallsComments(movie, user, comment);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while leaving a comment.");
        }
    }

    private void updateMovieComments(Movie movie, User user, String comment) {
        try {
            Map<String, Movie> moviesTitle = infoFiles.loadFileMovie();
            moviesTitle.get(movie.getTitle()).getComments().get(user).add(comment);
            infoFiles.saveFileMovie(moviesTitle);
        } catch (Exception e) {
            // Handle the exception, e.g., log it or display an error message
            e.printStackTrace();
            System.out.println("An error occurred while updating movie comments.");
        }
    }

    private void updateGenreComments(Movie movie, User user, String comment) {
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
    }

    private void updateHallsComments(Movie movie, User user, String comment) {
        try {
            ArrayList<Cinema> halls = infoFiles.arrayOfObjectHallsLoad();

            if (halls != null) {
                halls.stream()
                        .flatMap(hall -> hall.getMovies().stream())
                        .filter(m -> m == movie)
                        .findFirst()
                        .ifPresent(m -> {
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

    public ArrayList<Movie> getAllMoviesGenre(String genre) {

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
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    ArrayList<Movie> searchMovieByTitle() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter the title of the movie");
            String title = scanner.nextLine();
            InfoFiles f = new InfoFiles();
            Map<String, Movie> moviesMap = f.loadFileMovie();
            ArrayList<Movie> movies = new ArrayList<>(moviesMap.values());

            movies = movies.stream()
                    .filter(movie -> title.contains(movie.getTitle()))
                    .collect(Collectors.toCollection(ArrayList::new));
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
            Map<String, Movie> moviesMap = f.loadFileMovie();

            for (Map.Entry<String, Movie> entry : moviesMap.entrySet()) {
                movies.add(entry.getValue());
            }
            return movies;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /*    void printAllMoviesInHalls() {
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
        }*/
    void addFavorite(User user, Movie movie) {
        try {
            InfoFiles f = new InfoFiles();
            ArrayList<User> users = f.readFromFileAccounts(f.fileUser);

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
            ArrayList<User> users = f.readFromFileAccounts(f.fileUser);

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