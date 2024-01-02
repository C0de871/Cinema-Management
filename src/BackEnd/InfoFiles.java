package BackEnd;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InfoFiles implements Serializable {
    File file = new File("movie.ser");
    File fileComing = new File("moviesComing.ser");
    File fileHalls = new File("Halls.ser");
    File fileGenre = new File("GenerMovies.ser");
    public File fileUser = new File("fileUser.ser");
    File fileAdmin = new File("fileAdmin.ser");

    public InfoFiles() {
    }

    Map<String, Movie> loadFileMovie() {
        Map<String, Movie> mapRead = new HashMap<>();

        try {
            if (file.exists()) {
                if (file.length() != 0) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        mapRead = (Map<String, Movie>) ois.readObject();
                        System.out.println("Loading Done");
                    }
                } else {
                    System.out.println("File is empty");
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return mapRead;
    }


    void saveFileMovie(Map<String, Movie> movieMap) {
        try {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(movieMap);
                oos.flush();
                System.out.println("saving Done");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Map<String, ComingSoon> loadFileMovieComing() {
        Map<String, ComingSoon> mapRead = new HashMap<>();

        try {
            if (file.exists()) {
                if (fileGenre.length() != 0) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileComing))) {
                        mapRead = (Map<String, ComingSoon>) ois.readObject();
                        System.out.println("Loading Done");
                    }
                } else {
                    System.out.println("is equal to zero");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return mapRead;
    }

    void saveFileMovieComing(Map<String, ComingSoon> movieMap) {
        try {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileComing))) {
                oos.writeObject(movieMap);
                oos.flush();
                System.out.println("saving Done");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void arrayOfObjectHallsSave(ArrayList<Cinema> hall) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileHalls))) {
            oos.writeObject(hall);
            oos.flush();
            System.out.println("saving Done");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    ArrayList<Cinema> arrayOfObjectHallsLoad() {
        ArrayList<Cinema> reader = new ArrayList<>();

        try {
            if (fileHalls.exists()) {
                if (fileHalls.length() != 0) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileHalls))) {
                        reader = (ArrayList<Cinema>) ois.readObject();
                        System.out.println("Loading Done");
                    }
                } else {
                    System.out.println("is equal to zero");
                }
            }
            if (fileHalls.createNewFile()) {

                System.out.println("File created successfully.");
            } else {
                System.out.println("File already exists.");
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
                if (fileGenre.length() != 0) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileGenre))) {
                        mapRead = (Map<String, ArrayList<Movie>>) ois.readObject();
                        System.out.println("Loading Done");
                    }
                } else {
                    mapRead.put("action", new ArrayList<>());
                    mapRead.put("Drama", new ArrayList<>());
                    mapRead.put("comedy", new ArrayList<>());
                    mapRead.put("adventure", new ArrayList<>());
                    mapRead.put("documentary", new ArrayList<>());
                    saveFileMovieGenre(mapRead);
                    System.out.println("is equal to zero");
                }
            } else {
                if (fileGenre.createNewFile()) {

                    System.out.println("File created successfully.");
                } else {
                    System.out.println("File already exists.");
                }

            }
        } catch (IOException | ClassNotFoundException e) {
            // Handle the exceptions, log the error, or display an appropriate message
            e.printStackTrace();
            clearAllFiles();
        }

        return mapRead;
    }

    void saveFileMovieGenre(Map<String, ArrayList<Movie>> movieMap) {
        try {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileGenre))) {
                System.out.println("saving Done");
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

    public ArrayList<User> readFromFileAccounts(File file) {
        ArrayList<User> users = new ArrayList<>();
        try {
            if (file.exists()) {
                if (file.length() != 0) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        users = (ArrayList<User>) ois.readObject();
                        System.out.println("Loading Done");
                    }
                } else {
                    System.out.println("is equal to zero");
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

    public void appendToFileComing(String name, ComingSoon movie) {
        Map<String, ComingSoon> existingMap = loadFileMovieComing(); // Load existing data
        existingMap.put(name, movie); // Append new data to existing data
        saveFileMovieComing(existingMap); // Save the combined data back to the file
    }
}
