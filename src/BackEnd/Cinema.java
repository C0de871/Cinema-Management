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
            moviesGenre = loadFileMovieGenre();
            moviesGenre.computeIfAbsent(g, k -> new ArrayList<>());
            moviesGenre.get(g).add(m);
            f.saveFileMovieGenre(moviesGenre);
            appendToFile(name, m);
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
        Map<String, Movie> movies = loadFileMovie();
        String removedValue = String.valueOf(movies.remove(title));
        if (removedValue != null) {
            System.out.println(title + "BackEnd.Movie was removed");
        } else {
            System.out.println("there is no movie in this title");
        }
        f.saveFileMovie(movies);
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
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage()); // Handle any exceptions that occur during the process
        }
    }

    public void printAllMoviesGenre() {
        System.out.println("Enter the genre you want:");
        Scanner scanner = new Scanner(System.in);
        String genre = scanner.next();
        Map<String, ArrayList<Movie>> moviesGenre = loadFileMovieGenre();
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


    private Map<String, ArrayList<Movie>> loadFileMovieGenre() {
        InfoFiles f = new InfoFiles();
        Map<String, ArrayList<Movie>> mapRead = new HashMap<>();

        try {
            if (f.fileGenre.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f.fileGenre))) {
                    mapRead = (Map<String, ArrayList<Movie>>) ois.readObject();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            // Handle the exception, log it, or display an appropriate message
            e.printStackTrace();
        }

        return mapRead;
    }


    void searchMovieByTitle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the title of the movie");
        String title = scanner.nextLine();

        Map<String, Movie> movies = loadFileMovie();
        Movie movie = movies.get(title);
        if (movie != null) {
            System.out.println("BackEnd.Movie found:");
            System.out.println(movie);
        } else {
            System.out.println("BackEnd.Movie not found");
        }
    }

 /*    public void updateMovieDetails(String title) {
        try {
            for (BackEnd.Movie movie : movies) {
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
                    Date newStartTime = BackEnd.Main.getUserDateTime();
                    movie.getShowtimes().get(numberOfShowtimeToUpdate).setMovieStartTime(newStartTime);
                    System.out.println("Enter the new end time of the movie");
                    Date newEndTime = BackEnd.Main.getUserDateTime();
                    movie.getShowtimes().get(numberOfShowtimeToUpdate).setMovieEndTime(newEndTime);

                    System.out.println("BackEnd.Movie details updated successfully!");
                    return;
                }
            }

            System.out.println("BackEnd.Movie not found!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid choice.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }*/

    public void printAllMovies() {
        Map<String, Movie> movies = loadFileMovie();
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

    public void appendToFile(String name, Movie movie) {
        InfoFiles f = new InfoFiles();
        Map<String, Movie> existingMap = loadFileMovie(); // Load existing data
        existingMap.put(name, movie); // Append new data to existing data
        f.saveFileMovie(existingMap); // Save the combined data back to the file
    }


    private Map<String, Movie> loadFileMovie() {
        InfoFiles f = new InfoFiles();
        Map<String, Movie> mapRead = new HashMap<>();

        try {
            if (f.file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f.file))) {
                    mapRead = (Map<String, Movie>) ois.readObject();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return mapRead;
    }

}