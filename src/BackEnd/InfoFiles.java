package BackEnd;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InfoFiles {
    File file = new File("movie.ser");
    File fileHalls = new File("Halls.ser");
    File fileGenre = new File("GenerMovies.ser");

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
        } catch (FileNotFoundException e) {
            System.out.println(e);
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

    public void clearMovieFile() {
        File movieFile = new File("movie.ser");
        if (movieFile.exists()) {
            movieFile.delete();
            System.out.println("BackEnd.Movie file cleared successfully.");
        } else {
            System.out.println("BackEnd.Movie file does not exist.");
        }
    }

    public void clearHallsFile() {
        File hallsFile = new File("Halls.ser");
        if (hallsFile.exists()) {
            hallsFile.delete();
            System.out.println("Halls file cleared successfully.");
        } else {
            System.out.println("Halls file does not exist.");
        }
    }

    public void clearGenreFile() {
        File hallsFile = new File("GenerMovies.ser");
        if (hallsFile.exists()) {
            hallsFile.delete();
            System.out.println("Halls file cleared successfully.");
        } else {
            System.out.println("Halls file does not exist.");
        }
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
}
