package Pages;

import BackEnd.Cinema;
import BackEnd.InfoFiles;
import BackEnd.User;
import Login.LogInUp;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    static InfoFiles f = new InfoFiles();
    public static ArrayList<Cinema> halls = f.arrayOfObjectHallsLoad();
    public static ArrayList<User> users = f.readFromFileAccounts(f.fileUser);
    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogInUp().setVisible(true);
            }
        });
    }
}


//uncomment this section to add the body to the scrollpanel

//        JScrollPane scrollPane= new ScrollPaneWin11();
//        scrollPane.setViewportView(bodyPanel);
//        scrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#222631"), 2));
//        scrollPane.setBackground(Color.decode("#222631"));
//        scrollPane.setForeground(Color.decode("#222631"));
//        parentPanel.add(scrollPane, "push,grow");