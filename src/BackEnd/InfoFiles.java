package BackEnd;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InfoFiles {
    File file = new File("movie.ser");
    File fileHalls = new File("Halls.ser");
    File fileGenre = new File("GenerMovies.ser");
    File fileUser = new File("fileUser.ser");
    File fileAdmin = new File("fileAdmin.ser");

    InfoFiles() {
    }

    Map<String, Movie> loadFileMovie() {
        Map<String, Movie> mapRead = new HashMap<>();

        try {
            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    mapRead = (Map<String, Movie>) ois.readObject();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            // Handle the exceptions, log the error, or display an appropriate message
            e.printStackTrace();
        }

        return mapRead;
    }

    void saveFileMovie(Map<String, Movie> movieMap) {
        try {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(movieMap);
                oos.flush();
            }
        } catch (IOException e) {
            // Handle the exception, log the error, or display an appropriate message
            e.printStackTrace();
        }
    }

    void arrayOfObjectHallsSave(ArrayList<Cinema> hall) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileHalls))) {
            oos.writeObject(hall);
            oos.flush();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    ArrayList<Cinema> arrayOfObjectHallsLoad() {
        ArrayList<Cinema> reader = new ArrayList<>();

        try {
            if (fileHalls.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileHalls))) {
                    reader = (ArrayList<Cinema>) ois.readObject();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            // Handle the exceptions, log the error, or display an appropriate message
            e.printStackTrace();
        }

        return reader;
    }

    public void clearFile(File file) {
        if (file.exists()) {
            file.delete();
            System.out.println(file.getName() + " file cleared successfully.");
        } else {
            System.out.println(file.getName() + " file does not exist.");
        }
    }

    public void clearAllFiles() {
        clearFile(file);
        clearFile(fileHalls);
        clearFile(fileGenre);
        clearFile(fileUser);
        clearFile(fileAdmin);
    }

    Map<String, ArrayList<Movie>> loadFileMovieGenre() {
        Map<String, ArrayList<Movie>> mapRead = new HashMap<>();

        try {
            if (fileGenre.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileGenre))) {
                    mapRead = (Map<String, ArrayList<Movie>>) ois.readObject();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            // Handle the exceptions, log the error, or display an appropriate message
            e.printStackTrace();
        }

        return mapRead;
    }

    void saveFileMovieGenre(Map<String, ArrayList<Movie>> movieMap) {
        try {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileGenre))) {
                oos.writeObject(movieMap);
                oos.flush();
            }
        } catch (IOException e) {
            // Handle the exception, log the error, or display an appropriate message
            e.printStackTrace();
        }
    }

    void saveToFileAccounts(ArrayList<User> users, File file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(users);
            oos.flush();
            System.out.println("Data saved to file: " + file);
        } catch (IOException e) {
            System.out.println("An error occurred while saving data to file: " + e.getMessage());
        }
    }

    ArrayList<User> readFromFileAccounts(File file) {
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

    public void appendToFile(String name, Movie movie) {
        Map<String, Movie> existingMap = loadFileMovie(); // Load existing data
        existingMap.put(name, movie); // Append new data to existing data
        saveFileMovie(existingMap); // Save the combined data back to the file
    }

}
