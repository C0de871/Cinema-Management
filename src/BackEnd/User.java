package BackEnd;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class User implements Serializable {
    String email;
    String password;
    String name;
    char typeOfUser;
    Pattern passwordPattern;
    File fileUser = new File("fileUser.ser");
    File fileAdmin = new File("fileAdmin.ser");

    public User() {
    }

    public User(String email, String password, String name, char typeOfUser) {
        this.typeOfUser = typeOfUser;
        this.name = name;
        this.email = email;
        this.password = password;
        passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%.^&+=])(?=\\S+$).{8,}$");
    }

    public boolean login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your email");
        String emailInput = scanner.next();
        System.out.println("Enter your password");
        String passwordInput = scanner.next();
        System.out.println("Enter the type of Login");
        char type = scanner.next().charAt(0);
        System.out.println((type == 'U') ? "User" : "Admin" + " Login");
        File fileName = (type == 'U') ? fileUser : fileAdmin;

        if (isValidCredentials(emailInput, passwordInput, fileName)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid credentials. Please try again.");
            return false;
        }
    }


    public void register() {
        try {

            System.out.println((typeOfUser== 'U') ? "User" : "Admin" + " Registration");

            // Decide the filename based on the user's role
            File file = (typeOfUser=='U') ? fileUser : fileAdmin;

            if (isValidRegistration(this)) {
                if (!isExistingEmail(email, file)) {
                    ArrayList<User> users = readFromFile(file);
                    users.add(this);
                    saveToFile(users, file);
                } else {
                    System.out.println(" User with the same email already exists. Please try a different email.");
                }
            } else {
                System.out.println("Invalid registration details. Please try again.");
            }

        } catch (Exception e) {
            System.out.println("An error occurred during registration: " + e.getMessage());
        }
    }


    private boolean isExistingEmail(String email, File file) {

        ArrayList<User> usersRead = readFromFile(file);
        for (User u : usersRead) {
            if (Objects.equals(u.email, email))
                return true;
        }
        return false;
    }


    private boolean isValidRegistration(User user) {
        if (!isValidGmailAddress(user.email)) {
            System.out.println("Invalid Gmail address format.");
            return false;
        }

        if (!isValidPassword(user.password)) {
            System.out.println("Invalid password format. Password must contain at least 8 characters, including at least one uppercase letter, one lowercase letter, one digit, and one special character (@#$%^&+=).");
            return false;
        }
        return true;
    }


    private boolean isValidCredentials(String email, String password, File file) {
        ArrayList<User> users = readFromFile(file);
        for (User user : users) {
            if (Objects.equals(user.email, email) && Objects.equals(user.password, password)) {
                System.out.println("Hello " + user.name);
                return true;
            }
        }
        return false;
    }

    private boolean isValidGmailAddress(String email) {
        String regex = "^[a-zA-Z0-9+_.-]+@gmail.com$";
        return email.matches(regex);
    }

    private boolean isValidPassword(String password) {
        return passwordPattern.matcher(password).matches();
    }
// ...

    private void saveToFile(ArrayList<User> users, File file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(users);
            oos.flush();
            System.out.println("Data saved to file: " + file);
        } catch (IOException e) {
            System.out.println("An error occurred while saving data to file: " + e.getMessage());
        }
    }

    private ArrayList<User> readFromFile(File file) {
        ArrayList<User> users = new ArrayList<>();
        try {
            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    users = (ArrayList<User>) ois.readObject();
                }
                System.out.println("Data read from file: " + file);
            } else {
                System.out.println("File does not exist: " + file);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while reading data from file: " + e.getMessage());
        }
        return users;
    }
}