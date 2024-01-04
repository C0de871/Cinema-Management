package Pages;

import CustomComponent.CustomPanel.PanelRound;
import CustomComponent.Text.MyText;
import CustomComponent.Text.PasswordField;
import CustomComponent.Text.ShadingTextField;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import static CustomComponent.StaticClass.Properties.*;
import static Login.PanelLoginAndRegister.user;

public class Profile extends JPanel {
    public Profile() {
        this.setLayout(new MigLayout("debug"));
        PanelRound panel = new PanelRound();
        panel.setLayout(new MigLayout("insets 8% 8% 8% 8%"));
        this.setBackground(light_Gray);
        panel.setRoundBottomLeft(40);
        panel.setRoundBottomRight(40);
        panel.setRoundTopLeft(40);
        panel.setRoundTopRight(40);
        panel.setBackground(dark_Gray);
        MyText userName = new MyText("Name : ", 13, Color.decode("#555576"), 0);
        MyText userEmail = new MyText("Email : ", 13, Color.decode("#555576"), 0);
        MyText userPassword = new MyText("Password : ", 13, Color.decode("#555576"), 0);
        MyText changeName = new MyText("Change Name", 13, Color.red, 1);
        MyText uneditable = new MyText("Can't be changed", 13, Color.red, 1);
        MyText changePassword = new MyText("Change Password", 13, Color.red, 1);
        MyText myRoom = new MyText("My Room", 40, Color.decode("#B9B9BB"), 1);
        ShadingTextField userNameField = new ShadingTextField();
        userNameField.setText(user.getName());
        userNameField.setBackground(Color.decode("#2A2A3B"));
        userNameField.setShadowColor(new Color(0, 0, 0, 0));
        userNameField.setEditable(false);
        ShadingTextField userEmailField = new ShadingTextField();
        userEmailField.setText(user.getEmail());
        userEmailField.setBackground(Color.decode("#2A2A3B"));
        userEmailField.setShadowColor(new Color(0, 0, 0, 0));
        userEmailField.setEditable(false);
        PasswordField userPasswordField = new PasswordField();
        userPasswordField.setText(user.getPassword());
        userPasswordField.setBackground(Color.decode("#2A2A3B"));
        userPasswordField.setShadowColor(new Color(0, 0, 0, 0));
        userPasswordField.setEditable(false);
        panel.add(userName, "sg 1,w 7%,left");
        panel.add(userNameField, "w 40%,left");
        panel.add(changeName, "wrap,gap before 5%,pushx");
        panel.add(userEmail, "sg 1,w 7%");
        panel.add(userEmailField, "w 40%");
        panel.add(uneditable, "wrap,gap before 5%,pushx");
        panel.add(userPassword, "sg 1,w 7%");
        panel.add(userPasswordField, "w 40%");
        panel.add(changePassword, "wrap,gap before 5%,pushx");
        panel.add(myRoom, "spanx,spany,center,push");

        this.add(panel, "w 60%,h 60%,center,push");

    }
}
