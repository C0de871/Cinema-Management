

import java.util.*;

class Cinema {
    private final int hallNum;
    private static int nexthallnum = 1;
    private  List<Movie> movies;

    public Cinema() {
        this.hallNum = nexthallnum++;
        this.movies = new ArrayList<>();
    }

    public int getHallNum() {
        return hallNum;
    }



    public List<Movie> getMovies() {
        return movies;
    }

    public void add() {
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


            if (Main.file.exists()) {
                Main.halls = Main.arrayOfObjectReader();
                this.movies.add(m);
                Main.arrayOfObjectWriter(Main.halls);

            } else {
                this.movies.add(m);
                Main.arrayOfObjectWriter(Main.halls);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid choice.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public boolean deleteMovie(String title) {
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

}


