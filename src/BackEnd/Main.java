package BackEnd;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main implements Serializable {

    public static void main(String[] args) {

    }

    public static Date getUserDateTime(String dateString, String timeString) {
        while (true) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
            try {
                return dateFormat.parse(dateString + " " + timeString);
            } catch (ParseException e) {
                System.out.println("Invalid date or time format. Please enter the date in dd/MM/yyyy format and time in hh:mm a format.");
            }
        }
    }
}