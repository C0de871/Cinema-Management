
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
        passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%.^&+=])(?=\\S+$).{8,}$");    }

    public void login(String c) {
        try {
            Scanner scanner = new Scanner(System.in);
            if (Objects.equals(c, "A"))
                System.out.println("Admin Login");
            else
                System.out.println("User Login");

            boolean loggedIn = false;
            while (!loggedIn) {
                System.out.print("Enter Gmail address to login: ");
                String email = scanner.next();
                System.out.print("Enter password: ");
                String password = scanner.next();
                if (Objects.equals(c, "U")) {
                    if (isValidCredentialsU(email, password)) {
                        System.out.println("User login successful!");
                        loggedIn = true;
                    } else {
                        System.out.println("Invalid credentials. Please try again.");
                    }
                } else {
                    if (isValidCredentialsA(email, password)) {
                        System.out.println("Admin login successful!");
                        loggedIn = true;
                    } else {
                        System.out.println("Invalid credentials. Please try again.");
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("An error occurred during login: " + e.getMessage());
        }
    }
// I am the only one here
    public void register(String c) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("User Registration");
            while (true) { // a while loop is break just when the register is succeeded
                System.out.print("Enter Gmail address: ");
                String email = scanner.next();
                System.out.print("Enter password: ");
                String password = scanner.next();
                if (Objects.equals(c, "U")) {
                    if (isValidRegistration(email, password, c)) {
                        if (!isExistingEmail(email)) {
                            userCredentials.put(email, password);
                            saveToFile("first.txt", userCredentials);
                            System.out.println("User registered successfully!");
                            System.out.println("Registered email: " + email);
                            break;
                        } else {
                            System.out.println("User with the same email already exists. Please try a different email.");
                        }
                    } else {
                        System.out.println("Invalid registration details. Please try again.");
                    }
                } else {
                    if (isValidRegistration(email, password, c)) {
                        if (!isExistingEmail(email)) {
                            adminCredentials.put(email, password);
                            System.out.println("Admin registered successfully!");
                            System.out.println("Registered Gmail: " + email);
                            break;
                        } else {
                            System.out.println("Admin with the same email already exists. Please try a different email.");
                        }
                    } else {
                        System.out.println("Invalid registration details. Please try again.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred during registration: " + e.getMessage());
        }
    }

    private boolean isExistingEmail(String email) {
        Map<String, String> existingCredentials = readFromFile("first.txt");
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
        if (Objects.equals(c, "U")) {
            if (userCredentials.containsKey(email)) {
                System.out.println("Gmail address already exists.");
                return false;
            }
        } else {
            if (adminCredentials.containsKey(email)) {
                System.out.println("Gmail address already exists.");
                return false;
            }
        }
        return true;
    }

    private boolean isValidCredentialsU(String email, String password) {
        Map<String, String> existingCredentials = readFromFile("first.txt");

        return existingCredentials.containsKey(email) && existingCredentials.get(email).equals(password);
    }

    private boolean isValidCredentialsA(String email, String password) {
        Map<String, String> existingCredentials = readFromFile("first.txt");

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