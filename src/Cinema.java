

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.nio.channels.ScatteringByteChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class Cinema  implements Serializable {
    private final int hallNum;
    private static int nexthallnum = 1;
    private List<Movie> movies;
   // private Map<String, Movie> movieMap;
   static  int countOfMovieGenere=0;


    public Cinema() {
        this.hallNum = nexthallnum++;
        this.movies = new ArrayList<>();
       // this.movieMap = new HashMap<>();
    }

    public int getHallNum() {
        return hallNum;
    }


    public List<Movie> getMovies() {
        return movies;
    }

    void add() {
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
            // Create a new Movie object with the provided details
            Movie m = new Movie(name, g, show);
            // Add the movie to the specified hall in the halls ArrayList
          //  this.movieMap.put(name, m);
            Main.addMovieGenre(g);
            countOfMovieGenere++;
            if (Main.file.exists()) {
                Main.halls = Main.arrayOfObjectReader();
                this.movies.add(m);
                Main.arrayOfObjectWriter(Main.halls);

            } else {
                this.movies.add(m);
                Main.arrayOfObjectWriter(Main.halls);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid choice." + e);
        } catch (NotSerializableException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } catch (IOException el) {
            System.out.println("An error occurred: " +el);;
        } catch (ClassNotFoundException en) {
            System.out.println("An error occurred: " +en);
        }
    }

    boolean deleteMovie(String title) {
        try {
            Iterator<Movie> iterator = this.movies.iterator();
            while (iterator.hasNext()) {
                Movie movie = iterator.next();
                if (Objects.equals(movie.getTitle(), title)) {
                    iterator.remove();
                    System.out.println("Deleted");
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());

        }
        return false;
    }

    void getMoviesAroundTime() {
        System.out.println("Enter the duration of the movie you want ");
        System.out.println("From Date:");
        Date startDate = Main.getUserDateTime(); // Get the start date from the user
        System.out.println("To Date:");
        Date endDate = Main.getUserDateTime(); // Get the end date from the user
        List<Movie> moviesBetweenDates = new ArrayList<>(); // Create a list to store movies between the given dates
        try {
            // Iterate through each cinema hall
            List<Movie> movies = this.getMovies(); // Get the list of movies in the current hall
            moviesBetweenDates.addAll(movies.stream()
                    .filter(movie -> movie.getShowtimes().stream()
                            .anyMatch(showtime -> showtime.getMovieStartTime().after(startDate) && showtime.getMovieStartTime().before(endDate)))
                    .toList()); // Filter the movies based on the showtimes between the given dates and add them to the list

        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage()); // Handle any exceptions that occur during the process
        }
        if (moviesBetweenDates.isEmpty())
            System.out.println("No results found");

        for (Movie m : moviesBetweenDates) {
            System.out.println(m); // Print the movies that match the criteria
        }
    }

    void searchMovieByGenre(String genre) {
        List<Movie> foundMovies = new ArrayList<>();

        for (Movie movie : getMovies()) {
            if (movie.getGenre().equalsIgnoreCase(genre)) {
                foundMovies.add(movie);
            }
        }
        System.out.println("Movies found in the genre: " + genre);
        for (Movie movie : foundMovies) {
            System.out.println(movie.getTitle());
        }
    }

    void searchMovieByTitle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the title of the movie");
        String title = scanner.next();
        try {
            List<Movie> foundMovies = getMovies().stream()
                    .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                    .toList();
            if (foundMovies.isEmpty()) {
                System.out.println("No movies found with the title: " + title);
            } else {
                System.out.println("Movies found with the title: " + title);
                foundMovies.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

 /*    public void updateMovieDetails(String title) {
        try {
            for (Movie movie : movies) {
                if (movie.getTitle().equals(title)) {
                    Scanner scanner = new Scanner(System.in);

                    System.out.println("Enter the new title: ");
                    String newTitle = scanner.nextLine();
                    movie.setTitle(newTitle);

                    System.out.println("Enter the new genre: ");
                    String newGenre = scanner.nextLine();
                    movie.setGenre(newGenre);

                    movie.printShowtimes();

                    System.out.println("Enter Which one you want to update it ");
                    int numberOfShowtimeToUpdate = scanner.nextInt();
                    System.out.println("Enter the new start time of the movie");
                    Date newStartTime = Main.getUserDateTime();
                    movie.getShowtimes().get(numberOfShowtimeToUpdate).setMovieStartTime(newStartTime);
                    System.out.println("Enter the new end time of the movie");
                    Date newEndTime = Main.getUserDateTime();
                    movie.getShowtimes().get(numberOfShowtimeToUpdate).setMovieEndTime(newEndTime);

                    System.out.println("Movie details updated successfully!");
                    return;
                }
            }

            System.out.println("Movie not found!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid choice.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }*/

    public void printAllMovies() {
        try {
            String [] readFromeFileMovieGenere = Main.readMovieGenere();
            for ( String re: readFromeFileMovieGenere) {
                System.out.println(re);
            }
        }catch(Exception gg ){
            System.out.println(gg);
        }


    }
}


