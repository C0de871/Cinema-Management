
package Login;

import BackEnd.InfoFiles;
import BackEnd.User;
import Pages.UserFrame;
import net.miginfocom.swing.MigLayout;
import CustomComponent.Button.Button;
import CustomComponent.Button.MyPasswordField;
import CustomComponent.Text.MyTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static CustomComponent.StaticClass.Properties.blue_;
import static CustomComponent.StaticClass.Properties.light_Gray;
import static Login.LogInUp.animator;
import static Pages.Main.users;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {
    public static MyTextField txtEmail;
    public static User user = null;

    public PanelLoginAndRegister() {
        initComponents();
        initLogin();
        initRegister();
        login.setVisible(false);
        register.setVisible(true);
    }

    public void initRegister() {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]20[]push"));
        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(blue_);
        register.add(label);
        MyTextField txtUser = new MyTextField(Color.decode("#B8BFF4"));
        txtUser.setPrefixIcon(new ImageIcon("assest/user.png"));
        txtUser.setHint("Name");
        txtUser.getText();
        register.add(txtUser, "w 60%");
        txtEmail = new MyTextField(Color.decode("#B8BFF4"));
        txtEmail.setPrefixIcon(new ImageIcon("assest/mail.png"));
        txtEmail.setHint("Email");
        register.add(txtEmail, "w 60%");
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon("assest/pass.png"));
        txtPass.setHint("Password");
        register.add(txtPass, "w 60%");
        Button cmd = new Button();
        cmd.setBackground(blue_);
        cmd.setForeground(new Color(250, 250, 250));
        JLabel error = new JLabel();
        error.setForeground(Color.red);
        register.add(error);
        error.setVisible(false);
        //1 -> successful
        // 2 -> " User with the same email already exists.Please try a different email."and false

        cmd.setText("SIGN UP");
        MouseListener listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //String email, String password, String name, char typeOfUser
                User u = new User(txtEmail.getText(), txtPass.getText(), txtUser.getText(), 'U');
                int register = u.register();

                if (register == 1) {
                    if (!animator.isRunning()) {
                        animator.start();
                    }

                }
                else if(register==0) {
                    error.setText("* Email is already exist");
                    error.setVisible(true);
                }
                else if (register == 2) {
                    error.setText("* Invalid Gmail address format");

                    error.setVisible(true);
                } else if (register == 3) {
                    error.setText("<html>* Password must contain at least 8 characters<br/>* including at least one uppercase letter<br/>* one lowercase letter, one digit<br/>* and one special character (@#$%^&+=).</html>");
//                    error.setText("* Password must contain at least 8 characters, including at least one uppercase letter, one lowercase letter, one digit, and one special character (@#$%^&+=).");
                    error.setVisible(true);
                } else {
                    error.setText("* oops!");
                    error.setVisible(true);
                }

                // MyFrame frame = new MyFrame();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        cmd.addMouseListener(listener);
        login.add(cmd, "w 40%, h 40");
        register.add(cmd, "w 40%, h 40");
    }

    public void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]20[]push"));
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(blue_);
        login.add(label);
        MyTextField txtEmail = new MyTextField(Color.decode("#B8BFF4"));
        txtEmail.setPrefixIcon(new ImageIcon("assest/mail.png"));
        txtEmail.setHint("Email");
        login.add(txtEmail, "w 60%");
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon("assest/pass.png"));
        txtPass.setHint("Password");
        login.add(txtPass, "w 60%");
        Button cmd = new Button();
        JLabel error = new JLabel();
        error.setForeground(Color.red);
        login.add(error);
        error.setVisible(false);
        cmd.setBackground(blue_);
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN IN");
        MouseListener listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//              MyFrame frame = new MyFrame();
                User u = new User();
                int login = u.login(txtEmail.getText(), txtPass.getText(), 'U');
                if (login == 1) {
                    InfoFiles f = new InfoFiles();
                    u.setEmail(txtEmail.getText());
                    u.setPassword(txtPass.getText());
                    for (User u1 : users) {
                        if (u.equals(u1)) {
                            System.out.println("Found the user");
                            user = u1;
                        }
                    }
                    UserFrame frame = new UserFrame();
                } else if (login == 2) {
                    System.out.println("password");
                    error.setText("* Invalid Password");
                    error.setVisible(true);
                } else {
                    System.out.println("email");
                    error.setText("* Invalid Email");
                    error.setVisible(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        cmd.addMouseListener(listener);
        login.add(cmd, "w 40%, h 40");
    }

    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(light_Gray);

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
                loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
                loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(light_Gray);

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
                registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
                registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>

    // Variables declaration - do not modify
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration
}