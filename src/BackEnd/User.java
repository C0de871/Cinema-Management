package BackEnd;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import static Pages.Main.users;

public class User implements Serializable {
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTypeOfUser(char typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    private String password;
    private String name;
    private char typeOfUser;
    Pattern passwordPattern;
    private ArrayList<Movie> favorite;
    private ArrayList<Ticket> myTicket;

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
        this.myTicket = new ArrayList<>();
    }


    public ArrayList<Movie> getFavoriteMovies() {
        return favorite;
    }


    private boolean isExistingEmail(String email, File file, InfoFiles f) {
        for (User u : users) {
            if (Objects.equals(u.email, email))
                return true;
        }
        return false;
    }


    private int isValidRegistration(User user) {
        if (!isValidGmailAddress(user.email)) {
            return 2;
        }
        if (!isValidPassword(user.password)) {
            return 3;
        }
        return 1;
    }


    private int isValidCredentials(String email, String password, File file, InfoFiles f) {
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


    public ArrayList<Ticket> getMyTickets() {
        return myTicket;
    }

    public boolean has(int searalNum) {
        ArrayList<Ticket> tickets = this.myTicket;
        for (Ticket t : tickets) {
            if (t.getSerialNumber() == searalNum)
                return true;
        }
        return false;
    }

    public void bookTicket(Ticket ticket) {
        myTicket.add(ticket);
    }

    public boolean cancelTicket(Ticket ticket) {
        return myTicket.remove(ticket);
    }

    public void viewMyTickets(User user) {
        InfoFiles f = new InfoFiles();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) == user) {
                if (myTicket.isEmpty()) {
                    System.out.println("No tickets booked yet.");
                } else {
                    System.out.println("Your Tickets:");
                }
            } else
                System.out.println("not found the user");
        }
    }

    public boolean findTicket(Movie movie) {
        InfoFiles f = new InfoFiles();

        for (User u : users) {
            if (u == this) {
                ArrayList<Ticket> userTickets = u.getMyTickets();
                if (myTicket.isEmpty()) {
                    return false;
                } else {
                    for (Ticket ticket : userTickets) {
                        if (ticket.getMovie() == movie) {
                            return true;
                        }
                    }
                    return false;
                }
            }
        }
        return false;
    }

    public void addFavoriteMovie(Movie movie) {
        InfoFiles f = new InfoFiles();

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

        for (User user : users) {
            if (user == this) {
                user.getFavoriteMovies().remove(movie);
                break;
            }
        }
        f.saveToFileAccounts(users, f.fileUser);
    }

    public int login(String emailInput, String passwordInput, char type) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return (Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword()));
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword(), getName(), getTypeOfUser());
    }
}