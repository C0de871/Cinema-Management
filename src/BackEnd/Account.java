package BackEnd;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Account implements Serializable {


    private final Map<String, String> userCredentials;
    private final HashMap<String, String> adminCredentials;
    private final Pattern passwordPattern;

    public Account() {
        userCredentials = new HashMap<>();
        adminCredentials = new HashMap<>();
        passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%.^&+=])(?=\\S+$).{8,}$");
    }

    public void login(String c) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println((Objects.equals(c, "U") ? "BackEnd.User" : "Admin") + " Login");
            String fileName = (Objects.equals(c, "U")) ? "user_credentials.txt" : "admin_credentials.txt";

            boolean loggedIn = false;
            while (!loggedIn) {
                System.out.print("Enter Gmail address to login: ");
                String email = scanner.next();
                System.out.print("Enter password: ");
                String password = scanner.next();
                if (isValidCredentials(email, password, fileName)) {
                    System.out.println("BackEnd.User login successful!");
                    loggedIn = true;
                } else {
                    System.out.println("Invalid credentials. Please try again.");
                }

            }
        }
        catch(Exception e)
        {
            System.out.println("An error occurred during login: " + e.getMessage());
        }

    }
    public void register(String c) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println((Objects.equals(c, "U") ? "BackEnd.User" : "Admin") + " Registration");

            // Decide the filename based on the user's role
            String fileName = (Objects.equals(c, "U")) ? "user_credentials.txt" : "admin_credentials.txt";

            while (true) {
                System.out.print("Enter Gmail address: ");
                String email = scanner.next();
                System.out.print("Enter password: ");
                String password = scanner.next();

                if (isValidRegistration(email, password, c)) {
                    if (!isExistingEmail(email, fileName)) {
                        Map<String, String> credentials = (Objects.equals(c, "U")) ? userCredentials : adminCredentials;
                        credentials.put(email, password);
                        saveToFile(fileName, credentials);
                        System.out.println((Objects.equals(c, "U") ? "BackEnd.User" : "Admin") + " registered successfully!");
                        System.out.println("Registered email: " + email);
                        break;
                    } else {
                        System.out.println((Objects.equals(c, "U") ? "BackEnd.User" : "Admin") + " with the same email already exists. Please try a different email.");
                    }
                } else {
                    System.out.println("Invalid registration details. Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred during registration: " + e.getMessage());
        }
    }


    private boolean isExistingEmail(String email, String fileName) {
     File fil = new File(fileName);
        if(fil.length()==0)
            return false;
        Map<String, String> existingCredentials = readFromFile(fileName);
        return existingCredentials.containsKey(email);
    }


    private boolean isValidRegistration(String email, String password, String c) {
        if (!isValidGmailAddress(email)) {
            System.out.println("Invalid Gmail address format.");
            return false;
        }

        if (!isValidPassword(password)) {
            System.out.println("Invalid password format. Password must contain at least 8 characters, including at least one uppercase letter, one lowercase letter, one digit, and one special character (@#$%^&+=).");
            return false;
        }

        Map<String, String> credentials = (Objects.equals(c, "U")) ? userCredentials : adminCredentials;

        if (credentials.containsKey(email)) {
            System.out.println((Objects.equals(c, "U") ? "BackEnd.User" : "Admin") + " with the same email already exists.");
            return false;
        }

        return true;
    }


    private boolean isValidCredentials(String email, String password , String fileName) {
        Map<String, String> existingCredentials = readFromFile(fileName);
        return existingCredentials.containsKey(email) && existingCredentials.get(email).equals(password);
    }
    private boolean isValidGmailAddress(String email) {
        String regex = "^[a-zA-Z0-9+_.-]+@gmail.com$";// تعريف نمط لفحص صحة عنوان Gmail
        return email.matches(regex);// التحقق من صحة عنوان Gmail باستخدام النمط
        //("gmail.com")باختصار، هذا النمط يتطلب أن يكون عنوان البريد الإلكتروني مكونًا من الأحرف الأبجدية الصغيرة والكبيرة والأرقام وبعض الرموز، مع وجود الرمز "@" وتتبعه .
    }

    private boolean isValidPassword(String password) {
        return passwordPattern.matcher(password).matches();// التحقق من صحة كلمة المرور باستخدام النمط
    }
// ...

    private void saveToFile(String fileName, Map<String, String> map) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
            System.out.println("Data saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving data to file: " + e.getMessage());
        }
    }

    private Map<String, String> readFromFile(String fileName) {
        Map<String, String> credentials = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    credentials.put(parts[0], parts[1]);
                }
            }
            System.out.println("Data read from file: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while reading data from file: " + e.getMessage());
        }
        return credentials;
    }
}