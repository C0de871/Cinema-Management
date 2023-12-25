

import java.util.ArrayList;
import java.util.List;
class Cinema {
    private final int hallNum;
    private static int nexthallnum=1;
    private final List<Movie> movies;

    public Cinema() {
        this.hallNum = nexthallnum++;
        this.movies = new ArrayList<>();
    }
    public int getHallNum() {
        return hallNum;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
