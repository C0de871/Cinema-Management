package BackEnd;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Ticketing {
    public void bookTicketAsync(User user, ArrayList<Integer> pos, Movie movie, Showtimes showtimes) {
        Thread bookThread = new Thread(() -> {
            try {
                bookTicket(user, pos, movie, showtimes);
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

    public void bookTicket(User user, ArrayList<Integer> pos, Movie movie, Showtimes showtimes) {
        InfoFiles f = new InfoFiles();
        Scanner scanner = new Scanner(System.in);

        try {

            Map<String, Movie> movieTitle = f.loadFileMovie();


            Showtimes selectedShowtime = showtimes;

            ArrayList<User> users = f.readFromFileAccounts(f.fileUser);
            int userIndex = users.indexOf(user);
            ArrayList<Ticket> tickets = new ArrayList<>();

            ArrayList<Cinema> halls = f.arrayOfObjectHallsLoad();
            int index = 0;
            outer:
            for (Cinema hall : halls) {
                ArrayList<Movie> movies = (ArrayList<Movie>) hall.getMovies();

                for (Movie m : movies) {
                    if (m == movie) {
                        index = m.getShowtimes().indexOf(showtimes);
                        tickets = m.getShowtimes().get(index).getTickets();

                        for (int position : pos) {
                            Ticket selectedTicket = tickets.get(position);


                            Showtimes time = m.getShowtimes().get(index);
                            selectedTicket.setMovie(m);
                            selectedTicket.setShowtime(time);
                            selectedTicket.setActive(true);
                            selectedTicket.setTicketPrice(movie.getPrice());
                            // Attempt to book the ticket for the user
                            users.get(userIndex).getMyTickets().add(selectedTicket);

                        }
                        hall.setMovies(movies);
                        f.saveToFileAccounts(users, f.fileUser);
                        break outer;
                    }

                }
            }


            f.arrayOfObjectHallsSave(halls);

            Map<String, Movie> movies = f.loadFileMovie();
            movies.get(movie.getTitle()).getShowtimes().get(index).setTickets(tickets);
            f.saveFileMovie(movies);

            Map<String, ArrayList<Movie>> moviesGenre = f.loadFileMovieGenre();
            String genre = movie.getGenre();
            ArrayList<Movie> genreMovies = moviesGenre.get(genre);

            for (Movie m : genreMovies) {
                if (m.equals(movie)) {
                    m.getShowtimes().get(index).setTickets(tickets);
                    break;
                }
            }

            moviesGenre.put(genre, genreMovies);
            f.saveFileMovieGenre(moviesGenre);

        } catch (Exception e) {
            e.printStackTrace();
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



