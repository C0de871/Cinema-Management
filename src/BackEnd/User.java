package BackEnd;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class User implements Serializable {
    private String email;
    private String password;
    private String name;
    private char typeOfUser;
    Pattern passwordPattern;
    private ArrayList<Movie> favorite;

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public char getTypeOfUser() {
        return typeOfUser;
    }

    public Pattern getPasswordPattern() {
        return passwordPattern;
    }

    public ArrayList<Movie> getFavorite() {
        return favorite;
    }

    public User(String email, String password, String name, char typeOfUser) {
        this.typeOfUser = typeOfUser;
        this.name = name;
        this.email = email;
        this.password = password;
        passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%.^&+=])(?=\\S+$).{8,}$");
        favorite = new ArrayList<>();
    }

    public void addFavoriteMovie(Movie movie) {
        InfoFiles f = new InfoFiles();
        ArrayList<User> users = f.readFromFileAccounts(f.fileUser);
        for (User user : users) {
            if (user == this) {
                user.getFavoriteMovies().add(movie);
                break;
            }
        }
        f.saveToFileAccounts(users, f.fileUser);
    }

    public void removeFavoriteMovie(Movie movie) {
        InfoFiles f = new InfoFiles();
        ArrayList<User> users = f.readFromFileAccounts(f.fileUser);
        for (User user : users) {
            if (user == this) {
                user.getFavoriteMovies().remove(movie);
                break;
            }
        }
        f.saveToFileAccounts(users, f.fileUser);
    }

    public ArrayList<Movie> getFavoriteMovies() {
        return favorite;
    }

    public int login(String emailInput, String passwordInput, char type) {

        System.out.println("Enter your email");

        System.out.println("Enter your password");

        System.out.println("Enter the type of Login");

        System.out.println((type == 'U') ? "User" : "Admin" + " Login");
        InfoFiles f = new InfoFiles();
        File fileName = (type == 'U') ? f.fileUser : f.fileAdmin;
        int isValidCredentials = isValidCredentials(emailInput, passwordInput, fileName, f);
        if (isValidCredentials == 1) {
            return 1;
        } else if (isValidCredentials == 2) {
            return 2;
        } else
            return 3;
    }

    public int register() {
        try {
            InfoFiles f = new InfoFiles();
            System.out.println((typeOfUser == 'U') ? "User" : "Admin" + " Registration");
            // Decide the filename based on the user's role
            File file = (typeOfUser == 'U') ? f.fileUser : f.fileAdmin;
            int isValidRegistration = isValidRegistration(this);
            if (!isExistingEmail(this.email, file, f)) {
                if (isValidRegistration == 1) {
                    ArrayList<User> users = f.readFromFileAccounts(file);
                    users.add(this);
                    f.saveToFileAccounts(users, file);
                    return 1;
                } else if (isValidRegistration == 2) {
                    return 2;
                } else {
                    return 3;
                }
            } else {
                return 0;
            }
        } catch (Exception e) {
            System.out.println("An error occurred during registration: " + e.getMessage());
        }
        return 4;
    }


    private boolean isExistingEmail(String email, File file, InfoFiles f) {

        ArrayList<User> usersRead = f.readFromFileAccounts(file);
        for (User u : usersRead) {
            if (Objects.equals(u.email, email))
                return true;
        }
        return false;
    }


    private int isValidRegistration(User user) {
        if (!isValidGmailAddress(user.email)) {
            System.out.println("Invalid Gmail address format.");
            return 2;
        }

        if (!isValidPassword(user.password)) {
            System.out.println("Invalid password format. Password must contain at least 8 characters, including at least one uppercase letter, one lowercase letter, one digit, and one special character (@#$%^&+=).");
            return 3;
        }
        return 1;
    }


    private int isValidCredentials(String email, String password, File file, InfoFiles f) {

        ArrayList<User> users = f.readFromFileAccounts(file);
        for (User user : users) {
            if (Objects.equals(user.email, email)) {
                if (Objects.equals(user.password, password)) {
                    return 1;
                } else {
                    return 2;
                }
            }
        }
        return 3;
    }

    private boolean isValidGmailAddress(String email) {
        String regex = "^[a-zA-Z0-9+_.-]+@gmail.com$";
        return email.matches(regex);
    }

    private boolean isValidPassword(String password) {
        return passwordPattern.matcher(password).matches();
    }
}