
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main implements Serializable {
    public static ArrayList<Cinema> halls = new ArrayList<>(5);

    public static void main(String[] args) {
        if (Cinema.fileHalls.length() == 0) {
            for (int i = 0; i < 5; i++) {
                halls.add(new Cinema());
            }
        }

        try {
            System.out.println("Welcome to System!");
            System.out.println("************************************");
            System.out.println("1-Login");
            System.out.println("2-Register");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            Cinema c;
            c = new Cinema();
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
                            System.out.println("3-search on movies by genre");
                            System.out.println("4-show all the movies");
                            System.out.println("5-show all the movies with the halls");

                            choice = scanner.nextInt();
                            switch (choice) {
                                case 1 -> {
                                    for (Cinema hall : halls) {
                                        hall.getMoviesAroundTime();
                                    }
                                }
                                case 2 -> {
                                    c.searchMovieByTitle();
                                }
                                case 3 -> {
                                    System.out.println("Enter the genre of the movie");
                                    String genre = scanner.next();
                                    for (Cinema hall : halls) {
                                        hall.searchMovieByGenre(genre);
                                    }
                                }
                                case 4 -> {
                                    c.printAllMovies();
                                }
                                case 5 -> {
                                    c.printAllMoviesInHalls();
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
                            System.out.println("3-search on movies by showtime");
                            System.out.println("4-search on movies by title");
                            System.out.println("5-search on movies by genre");
                            System.out.println("6-show all the movies");
                            System.out.println("7-show all the movies with the halls");
                            choice = scanner.nextInt();
                            switch (choice) {
                                case 1 -> {
                                    System.out.println("Enter the Hall number");
                                    int hallnum = scanner.nextInt();
                                    c.add(hallnum);
                                }
                                case 2 -> {
                                    c.deleteMovie();
                                }
                                case 3 -> {
                                    for (Cinema hall : halls) {
                                        hall.getMoviesAroundTime();
                                    }
                                }
                                case 4 -> {
                                    c.searchMovieByTitle();
                                }
                                case 5 -> {
                                    System.out.println("Enter the genre of the movie");
                                    String genre = scanner.next();
                                    for (Cinema hall : halls) {
                                        hall.searchMovieByGenre(genre);
                                    }
                                }
                                case 6 -> {
                                    c.printAllMovies();
                                }
                                case 7 -> {
                                    c.printAllMoviesInHalls();
                                }
                                default -> {
                                    System.out.println("Invalid choice. Please try again.");
                                }
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
}