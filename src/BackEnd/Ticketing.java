package BackEnd;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Ticketing {
    public void bookTicketAsync(User user, int ticketNumber, Movie movie) {
        Thread bookThread = new Thread(() -> {
            try {
                bookTicket(user, ticketNumber, movie);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("An error occurred during ticket booking.");
            }
        });

        bookThread.start();
    }
    public void cancelTicketAsync(Ticket ticket, User user) {
        Thread cancelThread = new Thread(() -> {
            try {
                cancelTicket(ticket, user);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("An error occurred during ticket cancellation.");
            }
        });

        cancelThread.start();
    }

    public void bookTicket(User user, int ticketNumber, Movie movie) {
        InfoFiles f = new InfoFiles();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter the showtime: ");
            Map<String, Movie> movieTitle = f.loadFileMovie();
            movieTitle.get(movie.getTitle()).printShowtimes();
            int showtimeIndex = scanner.nextInt();
            Showtimes selectedShowtime = movie.getShowtimes().get(showtimeIndex - 1);
            int availableSeats = selectedShowtime.getAvailableSeats(selectedShowtime);

            System.out.println("Enter the number of tickets to book: ");
            int numTickets = scanner.nextInt();

            if (availableSeats >= numTickets) {
                ArrayList<Integer> chosen = new ArrayList<>();
                System.out.println("Enter the seat positions to book: ");

                for (int i = 0; i < numTickets; i++) {
                    int position = scanner.nextInt();
                    chosen.add(position);
                }

                ArrayList<User> users = f.readFromFileAccounts(f.fileUser);
                int userIndex = users.indexOf(user);
                ArrayList<Ticket> tickets = new ArrayList<>();

                ArrayList<Cinema> halls = f.arrayOfObjectHallsLoad();

                for (Cinema hall : halls) {
                    ArrayList<Movie> movies = (ArrayList<Movie>) hall.getMovies();

                    for (Movie m : movies) {
                        if (m == movie) {
                            tickets = m.getShowtimes().get(showtimeIndex - 1).getTickets();

                            for (int position : chosen) {
                                int ticketIndex = position - 1;

                                if (ticketIndex >= 0 && ticketIndex < tickets.size()) {
                                    Ticket selectedTicket = tickets.get(ticketIndex);

                                    if (!selectedTicket.isActive()) {
                                        Showtimes time = m.getShowtimes().get(showtimeIndex - 1);
                                        selectedTicket.setMovie(m);
                                        selectedTicket.setShowtime(time);
                                        selectedTicket.setActive(true);
                                        selectedTicket.setTicketPrice(50);
                                        // Attempt to book the ticket for the user
                                        users.get(userIndex).getMyTickets().add(selectedTicket);
                                    } else {
                                        System.out.println("The position " + position + " is already booked.");
                                    }
                                } else {
                                    System.out.println("Invalid position: " + position);
                                }
                            }

                            hall.setMovies(movies);
                            f.saveToFileAccounts(users, f.fileUser);
                            break;
                        }
                    }
                }

                f.arrayOfObjectHallsSave(halls);

                Map<String, Movie> movies = f.loadFileMovie();
                movies.get(movie.getTitle()).getShowtimes().get(showtimeIndex - 1).setTickets(tickets);
                f.saveFileMovie(movies);

                Map<String, ArrayList<Movie>> moviesGenre = f.loadFileMovieGenre();
                String genre = movie.getGenre();
                ArrayList<Movie> genreMovies = moviesGenre.get(genre);

                for (Movie m : genreMovies) {
                    if (m.equals(movie)) {
                        m.getShowtimes().get(showtimeIndex - 1).setTickets(tickets);
                        break;
                    }
                }

                moviesGenre.put(genre, genreMovies);
                f.saveFileMovieGenre(moviesGenre);

                System.out.println("Ticket booking successful!");
            } else {
                System.out.println("Not enough available seats for the selected showtime.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred during ticket booking.");
        } finally {
            scanner.close();
        }
    }

    public boolean cancelTicket(Ticket ticket, User user) {
        InfoFiles f = new InfoFiles();

        try {

            ArrayList<User> users = f.readFromFileAccounts(f.fileUser);


            boolean userCancellationSuccessful = users.stream()
                    .filter(u -> u.equals(user))
                    .findFirst()
                    .map(u -> u.getMyTickets().removeIf(t -> t == ticket))
                    .orElse(false);


            f.saveToFileAccounts(users, f.fileUser);


            Map<String, Movie> movieTitle = f.loadFileMovie();


            int index = movieTitle.get(ticket.getMovie().getTitle()).getShowtimes().indexOf(ticket.getShowtime());
            boolean movieTitleCancellation = movieTitle.get(ticket.getMovie().getTitle()).getShowtimes().get(index).getTickets().get(ticket.getPosition()).isActive();
            movieTitle.get(ticket.getMovie().getTitle()).getShowtimes().get(index).getTickets().get(ticket.getPosition()).setActive(false);
            f.saveFileMovie(movieTitle);


            Map<String, ArrayList<Movie>> movieGenre = f.loadFileMovieGenre();


            boolean movieGenreCancellation = movieGenre.get(ticket.getMovie().getGenre()).stream()
                    .filter(m -> m == ticket.getMovie())
                    .findFirst()
                    .map(m -> m.getShowtimes().get(index).getTickets().get(ticket.getPosition()).isActive())
                    .orElse(false);
            movieGenre.get(ticket.getMovie().getGenre()).stream()
                    .filter(m -> m == ticket.getMovie())
                    .findFirst()
                    .ifPresent(m -> m.getShowtimes().get(index).getTickets().get(ticket.getPosition()).setActive(false));
            f.saveFileMovieGenre(movieGenre);


            ArrayList<Cinema> halls = f.arrayOfObjectHallsLoad();


            boolean hallCancellation = halls.stream()
                    .flatMap(hall -> hall.getMovies().stream())
                    .filter(m -> m == ticket.getMovie())
                    .findFirst()
                    .map(m -> m.getShowtimes().get(index).getTickets().get(ticket.getPosition()).isActive())
                    .orElse(false);
            halls.stream()
                    .flatMap(hall -> hall.getMovies().stream())
                    .filter(m -> m == ticket.getMovie())
                    .findFirst()
                    .ifPresent(m -> m.getShowtimes().get(index).getTickets().get(ticket.getPosition()).setActive(false));

            // Check if all cancellations were successful
            if (userCancellationSuccessful && movieTitleCancellation && movieGenreCancellation && hallCancellation) {
                System.out.println("Ticket cancellation successful!");
                return true;
            } else {
                System.out.println("Ticket cancellation failed. Ticket not found or already canceled.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred during ticket cancellation.");
            return false;
        }
    }
}



