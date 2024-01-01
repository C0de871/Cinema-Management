package BackEnd;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main implements Serializable {

    public static void main(String[] args) {
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
                    System.out.println("1-BackEnd.User Login");
                    System.out.println("2-Admin Login");
                    choice = scanner.nextInt();
                    if (choice == 1) {
                        while (true) {
                            System.out.println("2-search on movies by title");
                            choice = scanner.nextInt();
                            switch (choice) {

                                case 2 -> {
                                    c.searchMovieByTitle();
                                }
                                default -> {
                                }
                            }
                        }
                    } else {
                        while (true) {

                            System.out.println("4-search on movies by title");
                            choice = scanner.nextInt();
                            switch (choice) {
                                case 4 -> {
                                    c.searchMovieByTitle();
                                }
                                default -> {
                                    System.out.println("Invalid choice. Please try again.");
                                }
                            }
                        }
                    }
                }
                case 2 -> {
                    System.out.println("1-BackEnd.User Registration ");
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

    private static void performRegistration(String c) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter your email: ");
            String email = scanner.nextLine();

            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            System.out.print("Enter the type of user (U for User, A for Admin): ");
            char typeOfUser = scanner.nextLine().charAt(0);
            User newUser = new User(email, password, name, typeOfUser);
            newUser.register();
        } catch (Exception e) {
            System.out.println("An error occurred during registration: " + e.getMessage());
        }
    }
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