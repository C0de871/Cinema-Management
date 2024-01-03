package BackEnd;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static Login.PanelLoginAndRegister.user;
import static Pages.Main.halls;
import static Pages.Main.users;


public class Ticketing {
    // private final ExecutorService threadPool = Executors.newFixedThreadPool(10);
    public void bookTicketAsync(User user, ArrayList<Integer> pos, Movie movie, Showtimes showtimes) {

        Thread bookThread = new Thread(() -> {
            try {
                System.out.println(pos);
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
    public void bookTicket(User user, ArrayList<Integer> pos, Movie movie, Showtimes showtimes) {
        InfoFiles f = new InfoFiles();
        try {
            //   Map<String, Movie> movieTitle = f.loadFileMovie();


            int userIndex = users.indexOf(user);

            ArrayList<Cinema> hall = halls;
            halls.get(0).printAllMoviesInHalls();
            ArrayList<Movie> movies = (ArrayList<Movie>) halls.get(movie.getHallNum() - 1).getMovies();
            for (Movie m : movies) {
                System.out.println(m);
            }
            System.out.println(movie);
            int indexofmovie = halls.get(movie.getHallNum() - 1).getMovies().indexOf(movie);
            System.out.println(indexofmovie);
            int indexofshowtime = halls.get(movie.getHallNum() - 1).getMovies().get(indexofmovie).getShowtimes().indexOf(showtimes);
            System.out.println(indexofshowtime);
            ArrayList<Ticket> ticketArrayList = halls.get(movie.getHallNum() - 1).getMovies().get(indexofmovie).getShowtimes().get(indexofshowtime).getTickets();


            for (int position : pos) {
                ticketArrayList.get(position).setMovie(movie);
                ticketArrayList.get(position).setShowtime(showtimes);
                ticketArrayList.get(position).setActive(true);
                ticketArrayList.get(position).setTicketPrice(movie.getPrice());
                System.out.println(ticketArrayList.get(position));
                users.get(userIndex).getMyTickets().add(ticketArrayList.get(position));
            }

            halls.get(movie.getHallNum() - 1).getMovies().get(indexofmovie).getShowtimes().get(indexofshowtime).setTickets(ticketArrayList);
            for (Ticket t : ticketArrayList) {
                System.out.println(t.getMovie());
            }
            System.out.println(pos.size());
            f.arrayOfObjectHallsSave(halls);
            f.saveToFileAccounts(users, f.fileUser);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void cancelTicket(User user, ArrayList<Integer> pos, Movie movie, Showtimes showtimes) {
        InfoFiles f = new InfoFiles();
        try {
            //   Map<String, Movie> movieTitle = f.loadFileMovie();


            int userIndex = users.indexOf(user);
            int indexofmovie = halls.get(movie.getHallNum() - 1).getMovies().indexOf(movie);
            System.out.println(indexofmovie);
            int indexofshowtime = halls.get(movie.getHallNum() - 1).getMovies().get(indexofmovie).getShowtimes().indexOf(showtimes);
            System.out.println(indexofshowtime);
            ArrayList<Ticket> ticketArrayList = halls.get(movie.getHallNum() - 1).getMovies().get(indexofmovie).getShowtimes().get(indexofshowtime).getTickets();

            System.out.println(pos);

            for (int position : pos) {

                ticketArrayList.get(position).setActive(false);
                System.out.println(ticketArrayList.get(position));
                Ticket movieTicket = ticketArrayList.get(position);
                int ser = movieTicket.getSerialNumber();
                ArrayList<Ticket> userTicket = users.get(userIndex).getMyTickets();
                for (Ticket ticket : userTicket) {
                    if (ticket.equals(movieTicket)) {
                        System.out.println("df  " + users.get(userIndex).getMyTickets().remove(ticket));
                        break;
                    }
                }
                System.out.println("sucess");
            }
            user.viewMyTickets(user);
            halls.get(movie.getHallNum() - 1).getMovies().get(indexofmovie).getShowtimes().get(indexofshowtime).setTickets(ticketArrayList);
            for (Ticket t : ticketArrayList) {
                System.out.println(t.isActive());
            }
            f.arrayOfObjectHallsSave(halls);
            f.saveToFileAccounts(users, f.fileUser);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



