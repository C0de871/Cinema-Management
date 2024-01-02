package BackEnd;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Ticketing {
   // private final ExecutorService threadPool = Executors.newFixedThreadPool(10);
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

    public void cancelTicketAsync(User user, ArrayList<Integer> pos, Movie movie, Showtimes showtimes) {
        Thread cancelThread = new Thread(() -> {
            try {
                cancelTicket(user, pos, movie, showtimes);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("An error occurred during ticket cancellation.");
            }
        });

        cancelThread.start();
    }
   /* public void shutdownThreadPool() {
        threadPool.shutdown();
    }*/
    public synchronized void bookTicket(User user, ArrayList<Integer> pos, Movie movie, Showtimes showtimes) {
        InfoFiles f = new InfoFiles();
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

    public synchronized void cancelTicket(User user, ArrayList<Integer> pos, Movie movie, Showtimes showtimes) {
        InfoFiles f = new InfoFiles();

        try {

            ArrayList<Ticket> tickets = new ArrayList<>();

            ArrayList<User> users = f.readFromFileAccounts(f.fileUser);
            int userIndex = users.indexOf(user);
            int index = 0;
            ArrayList<Cinema> halls = f.arrayOfObjectHallsLoad();
            outer:
            for (Cinema hall : halls) {
                ArrayList<Movie> movies = (ArrayList<Movie>) hall.getMovies();

                for (Movie m : movies) {
                    if (m == movie) {
                        index = m.getShowtimes().indexOf(showtimes);
                        tickets = m.getShowtimes().get(index).getTickets();

                        for (int position : pos) {
                            tickets.get(position).setActive(false);
                            Ticket cancelledTicket = tickets.get(position);
                            users.get(userIndex).getMyTickets().remove(cancelledTicket);
                        }
                        m.getShowtimes().get(index).setTickets(tickets);
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

}



