
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
  static    File file = new File("a.txt");

    static void arrayOfObjectWriter(ArrayList<Cinema> s) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(s);
    }
   
    static ArrayList<Cinema> arrayOfObjectReader() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        ArrayList<Cinema> cinemas = (ArrayList<Cinema>) ois.readObject();
        return cinemas;
    }
    
    public static ArrayList<Cinema> halls = new ArrayList<>(5);

    public static void main(String[] args) {
        try {
            System.out.println("Welcome to System!");
            System.out.println("************************************");
            System.out.println("1-Login");
            System.out.println("2-Register");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("1-User Login");
                    System.out.println("2-Admin Login");
                    choice = scanner.nextInt();
                    if (choice == 1) {
                        performLogin("U");
                        while (true) {
                            System.out.println("1-search on movies by showtime");
                            System.out.println("2-search on movies by title");
                            choice = scanner.nextInt();
                            switch (choice) {
                                case 1 -> {
                                    getMoviesAroundTime();
                                }
                                case 2 -> {
                                    searchMovieByTitle();
                                }
                                default -> {
                                }
                            }
                        }
                    } else {
                        performLogin("A");
                        while (true) {
                            System.out.println("1-Add Movie");
                            System.out.println("2-Delete Movie");
                            choice = scanner.nextInt();

                            switch (choice) {
                                case 1:
                                    System.out.println("Enter the number of the hall");
                                    int hallnum = scanner.nextInt();
                                    halls.get(hallnum).add();
                                    break;
                                case 2:
                                    System.out.println("Enter The title you want to delete");
                                    String title = scanner.next();
                                    boolean found = false;
                                    halls=arrayOfObjectReader();
                                    for (Cinema hall : halls) {
                                        if (hall.deleteMovie(title)) {
                                            found = true;
                                            arrayOfObjectWriter(halls);
                                            break;
                                        }
                                    }
                                    if (!found) System.out.println("there is no movie with this title");
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                                    break;
                            }
                        }
                    }
                }
                case 2 -> {
                    System.out.println("1-User Registration ");
                    System.out.println("2-Admin Registration ");
                    choice = scanner.nextInt();
                    if (choice == 1)
                        performRegistration("U");
                    else
                        performRegistration("A");
                    // it should return to the login screen
                }
                default -> System.out.println("Wrong input");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid choice.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


    private static void performLogin(String c) {
        try {
            Account login = new Account();
            login.login(c);
        } catch (Exception e) {
            System.out.println("An error occurred during login: " + e.getMessage());
        }
    }

    private static void performRegistration(String c) {
        try {
            Account login = new Account();
            login.register(c);
        } catch (Exception e) {
            System.out.println("An error occurred during registration: " + e.getMessage());
        }
    }
    public static void getMoviesAroundTime() {
        System.out.println("Enter the duration of the movie you want ");
        System.out.println("From Date:");
        Date startDate = getUserDateTime(); // Get the start date from the user
        System.out.println("To Date:");
        Date endDate = getUserDateTime(); // Get the end date from the user
        List<Movie> moviesBetweenDates = new ArrayList<>(); // Create a list to store movies between the given dates
        try {
            for (Cinema hall : halls) { // Iterate through each cinema hall
                List<Movie> movies = hall.getMovies(); // Get the list of movies in the current hall
                moviesBetweenDates.addAll(movies.stream()
                        .filter(movie -> movie.getShowtimes().stream()
                                .anyMatch(showtime -> showtime.getMovieStartTime().after(startDate) && showtime.getMovieStartTime().before(endDate)))
                        .toList()); // Filter the movies based on the showtimes between the given dates and add them to the list
            }
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage()); // Handle any exceptions that occur during the process
        }
        if (moviesBetweenDates.isEmpty())
            System.out.println("No results found");

        for (Movie m : moviesBetweenDates) {
            System.out.println(m); // Print the movies that match the criteria
        }
    }

    /**
     * This function is used to get the date and time from the user.
     * It prompts the user to enter the date and time in the specified format.
     * If the user enters an invalid date or time format, an error message is displayed.
     * The function uses a while loop to continuously prompt the user until a valid date and time are entered.
     *
     * @return The date and time entered by the user as a Date object.
     */

    public static Date getUserDateTime() {
        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter the date (dd/MM/yyyy): ");
            String dateString = scanner.nextLine();

            System.out.println("Enter the time (hh:mm a): ");
            String timeString = scanner.nextLine();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
            try {
                return dateFormat.parse(dateString + " " + timeString);
            } catch (ParseException e) {
                System.out.println("Invalid date or time format. Please enter the date in dd/MM/yyyy format and time in hh:mm a format.");
            }
        }
    }

    private static void searchMovieByTitle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the title of the movie");
        String title = scanner.next();
        try {
            List<Movie> foundMovies = halls.stream()
                    .flatMap(hall -> hall.getMovies().stream())
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


    
}