

import java.io.*;
import java.util.*;

class Cinema implements Serializable {
    private final int hallNum;
    private static int nexthallnum = 1;
    private List<Movie> movies;
    private Map<String, Movie> movieMap;
    static int countOfMovieGenere = 0;


    public Cinema() {
        this.hallNum = nexthallnum++;
        this.movies = new ArrayList<>();
        this.movieMap = new HashMap<>();
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
            // Create a new Movie object with the provided details
            Movie m = new Movie(name, g, show);
            // Add the movie to the specified hall in the halls ArrayList
            appendToFile(name, m);
            ArrayList<Cinema> hall = arrayOfObjectHallsLoad();
            if (hall.isEmpty()) {
                for (int i = 0; i < 5; i++) {
                    hall.add(new Cinema());
                    System.out.println("hi");
                }
            }

            // Load hall data
            hall.get(hallNumToadd - 1).getMovies().add(m);
            arrayOfObjectHallsSave(hall);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void deleteMovie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the title of the movie you want to delete");
        String title = scanner.next();

        try {
            ArrayList<Cinema> hall = arrayOfObjectHallsLoad();
            hall.forEach(h -> h.getMovies().removeIf(m -> m.getTitle().equals(title)));
            arrayOfObjectHallsSave(hall);
            Map<String, Movie> movies = loadFileMovie();
            String removedValue = String.valueOf(movies.remove(title));
            if (removedValue != null) {
                System.out.println(title + "Movie was removed");
            } else {
                System.out.println("there is no movie in this title");
            }
            saveFileMovie(movies);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    void getMoviesAroundTime() {
        System.out.println("Enter the duration of the movie you want ");
        System.out.println("From Date:");
        Date startDate = Main.getUserDateTime(); // Get the start date from the user
        System.out.println("To Date:");
        Date endDate = Main.getUserDateTime(); // Get the end date from the user
        try {
            Map<String, Movie> movies = loadFileMovie();
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
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage()); // Handle any exceptions that occur during the process
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
        String title = scanner.nextLine();

        try {
            Map<String, Movie> movies = loadFileMovie();
            Movie movie = movies.get(title);
            if (movie != null) {
                System.out.println("Movie found:");
                System.out.println(movie);
            } else {
                System.out.println("Movie not found");
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
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
            Map<String, Movie> movies = loadFileMovie();
            for (Map.Entry<String, Movie> entry : movies.entrySet()) {
                System.out.println(entry.getValue());
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    void printAllMoviesInHalls() {
        try {
            ArrayList<Cinema> halls = arrayOfObjectHallsLoad();
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
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    File file = new File("movie.ser");

    public void appendToFile(String name, Movie movie) {
        try {
            Map<String, Movie> existingMap = loadFileMovie(); // Load existing data
            existingMap.put(name, movie); // Append new data to existing data
            saveFileMovie(existingMap); // Save the combined data back to the file
        } catch (IOException e) {
            // Handle IOException
            System.out.println("An error occurred while accessing the file: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            // Handle ClassNotFoundException
            System.out.println("An error occurred while loading the data: " + e.getMessage());
        }
    }


    private void saveFileMovie(Map<String, Movie> movieMap) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(movieMap);
        }
    }

    private Map<String, Movie> loadFileMovie() throws IOException, ClassNotFoundException {
        Map<String, Movie> mapRead;
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                mapRead = (Map<String, Movie>) ois.readObject();
            }
        } else {
            mapRead = new HashMap<>(); // If the file doesn't exist, create a new map
        }
        return mapRead;
    }

    static File fileHalls = new File("Halls.ser");

    /*   public void appendToFileHalls(int index, Movie movie) {
           try {
               ArrayList<Cinema> hall = arrayOfObjectHallsLoad(); // Load hall data
               hall.get(index).getMovies().add(movie);
               arrayOfObjectHallsSave(hall); // Save the combined data back to the file
           } catch (IOException e) {
               // Handle IOException
               System.out.println("An error occurred while accessing the file: " + e.getMessage());
           } catch (ClassNotFoundException e) {
               // Handle ClassNotFoundException
               System.out.println("An error occurred while loading the data: " + e.getMessage());
           }
       }
   */
    private void arrayOfObjectHallsSave(ArrayList<Cinema> hall) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileHalls))) {
            oos.writeObject(hall);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private ArrayList<Cinema> arrayOfObjectHallsLoad() throws IOException, ClassNotFoundException {
        ArrayList<Cinema> Reder;
        if (fileHalls.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream((fileHalls)))) {
                Reder = (ArrayList<Cinema>) ois.readObject();
            }
        } else
            Reder = new ArrayList<>();
        return Reder;
    }

    public void clearMovieFile() {
        File movieFile = new File("movie.ser");
        if (movieFile.exists()) {
            movieFile.delete();
            System.out.println("Movie file cleared successfully.");
        } else {
            System.out.println("Movie file does not exist.");
        }
    }

    public void clearHallsFile() {
        File hallsFile = new File("Halls.ser");
        if (hallsFile.exists()) {
            hallsFile.delete();
            System.out.println("Halls file cleared successfully.");
        } else {
            System.out.println("Halls file does not exist.");
        }
    }
}