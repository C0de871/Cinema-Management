
package Login;

import Pages.MyFrame;
import net.miginfocom.swing.MigLayout;
import CustomComponent.Button;
import CustomComponent.MyPasswordField;
import CustomComponent.MyTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {


    public PanelLoginAndRegister() {
        initComponents();
        initLogin();
        initRegister();
        login.setVisible(false);
        register.setVisible(true);
        // hello
    }
    public void initRegister(){
        register.setLayout(new MigLayout("wrap","push[center]push","push[]25[]10[]10[]push"));
        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sansserif",1,30));
        label.setForeground(new Color(7,164,121));
        register.add(label);
        MyTextField txtUser= new MyTextField();
        txtUser.setPrefixIcon(new ImageIcon("assest/user.png"));
        txtUser.setHint("Name");
        txtUser.getText();
        register.add(txtUser,"w 60%");
        MyTextField txtEmail= new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon("assest/mail.png"));
        txtEmail.setHint("Email");
        register.add(txtEmail,"w 60%");
        MyPasswordField txtPass= new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon("assest/pass.png"));
        txtPass.setHint("Password");
        register.add(txtPass,"w 60%");
        Button cmd = new Button();
        cmd.setBackground(new Color(7,164,121));
        cmd.setForeground(new Color (250,250,250));
        cmd.setText("SIGN UP");
        MouseListener listener= new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MyFrame frame = new MyFrame();
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
        login.add(cmd,"w 40%, h 40");
        register.add(cmd,"w 40%, h 40");
    }
    public void initLogin(){
        login.setLayout(new MigLayout("wrap","push[center]push","push[]25[]10[]10[]push"));
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif",1,30));
        label.setForeground(new Color(7,164,121));
        login.add(label);
        MyTextField txtEmail= new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon("assest/mail.png"));
        txtEmail.setHint("Email");
        login.add(txtEmail,"w 60%");
        MyPasswordField txtPass= new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon("assest/pass.png"));
        txtPass.setHint("Password");
        login.add(txtPass,"w 60%");
        Button cmd = new Button();
        cmd.setBackground(new Color(7,164,121));
        cmd.setForeground(new Color (250,250,250));
        cmd.setText("SIGN IN");
        MouseListener listener= new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MyFrame frame = new MyFrame();
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
        login.add(cmd,"w 40%, h 40");
    }
    public void showRegister(boolean show){
        if(show){
            register.setVisible(true);
            login.setVisible(false);
        }else {
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

        login.setBackground(new Color(255, 255, 255));

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

        register.setBackground(new Color(255, 255, 255));

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