package Pages;

import Login.LogInUp;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new  LogInUp().setVisible(true);
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