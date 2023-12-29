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

    Map<String, Movie> loadFileMovie() throws IOException, ClassNotFoundException {
        Map<String, Movie> mapRead;
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                mapRead = (Map<String, Movie>) ois.readObject();
            }
        } else {
            mapRead = new HashMap<>(); // If the file doesn't exist, create a new map
        }
        return mapRead;
    }

    void saveFileMovie(Map<String, Movie> movieMap) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(movieMap);
            oos.flush();
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

    ArrayList<Cinema> arrayOfObjectHallsLoad() throws IOException, ClassNotFoundException {
        ArrayList<Cinema> Reder;
        if (fileHalls.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream((fileHalls)))) {
                Reder = (ArrayList<Cinema>) ois.readObject();
            }
        } else
            Reder = new ArrayList<>();
        return Reder;
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

    Map<String, ArrayList<Movie>> loadFileMovieGenre() throws IOException, ClassNotFoundException {
        Map<String, ArrayList<Movie>> mapRead;
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileGenre))) {
                mapRead = (Map<String, ArrayList<Movie>>) ois.readObject();
            }
        } else {
            mapRead = new HashMap<>(); // If the file doesn't exist, create a new map
        }
        return mapRead;
    }

    void saveFileMovieGenre(Map<String, ArrayList<Movie>> movieMap) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileGenre))) {
            oos.writeObject(movieMap);
            oos.flush();
        }
    }

}
