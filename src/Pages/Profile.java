package Pages;

import CustomComponent.CustomPanel.PanelRound;
import CustomComponent.Text.MyText;
import CustomComponent.Text.PasswordField;
import CustomComponent.Text.ShadingTextField;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

import static CustomComponent.StaticClass.Properties.dark_Gray;
import static CustomComponent.StaticClass.Properties.light_Blue;

public class Profile extends JPanel {
    Profile() {
        this.setLayout(new MigLayout());
        PanelRound panel = new PanelRound();
        panel.setRoundBottomLeft(40);
        panel.setRoundBottomRight(40);
        panel.setRoundTopLeft(40);
        panel.setRoundTopRight(40);
        panel.setBackground(dark_Gray);
        MyText userName = new MyText("Name :", 13, Color.decode("#555576"), 1);
        MyText userEmail = new MyText("Email :", 13, Color.decode("#555576"), 1);
        MyText userPassword = new MyText("Password : ", 13, Color.decode("#555576"), 1);
        ShadingTextField userNameField = new ShadingTextField();
        ShadingTextField userEmailField = new ShadingTextField();
        PasswordField userPasswordField = new PasswordField();
        panel.add(userName, "gs 1");
//        panel.add()
    }
}
