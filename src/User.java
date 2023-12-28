import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {
    private Account login;
    private String name;
    private Set<Movie> favoriteMovies;

    public User() {
        favoriteMovies = new HashSet<>();
        login = new Account();
    }

    public void createAccount(String s) {
        login.register(s);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addFavoriteMovie(Movie movie) {
        favoriteMovies.add(movie);
    }

    public void removeFavoriteMovie(Movie movie) {
        favoriteMovies.remove(movie);
    }

    public Set<Movie> getFavoriteMovies() {
        return favoriteMovies;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", favoriteMovies=" + favoriteMovies +
                '}';
    }
}