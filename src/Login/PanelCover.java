
package Login;

import net.miginfocom.swing.MigLayout;
import CustomComponent.Button.ButtonOutLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.text.DecimalFormat;

import static CustomComponent.StaticClass.Properties.*;

public class PanelCover extends javax.swing.JPanel {
    public double getRoundTopLeft() {
        return roundTopLeft;
    }

    public void setRoundTopLeft(double roundTopLeft) {
        this.roundTopLeft = roundTopLeft;
        repaint();
    }

    public double getRoundTopRight() {
        return roundTopRight;
    }

    public void setRoundTopRight(double roundTopRight) {
        this.roundTopRight = roundTopRight;
        repaint();
    }

    public double getRoundBottomLeft() {
        return roundBottomLeft;
    }

    public void setRoundBottomLeft(double roundBottomLeft) {
        this.roundBottomLeft = roundBottomLeft;
        repaint();
    }

    public double getRoundBottomRight() {
        return roundBottomRight;
    }

    public void setRoundBottomRight(double roundBottomRight) {
        this.roundBottomRight = roundBottomRight;
        repaint();
    }

    public double roundTopLeft = 0;
    public double roundTopRight = 0;
    public double roundBottomLeft = 0;
    public double roundBottomRight = 0;
    
    
    private final DecimalFormat df = new DecimalFormat("##0.###");
    private ActionListener event;
    private MigLayout layout;
    private JLabel title;
    private JLabel description;
    private JLabel description1;
    private ButtonOutLine button;
    private boolean isLogin;
    public PanelCover() {
        initComponents();
        setOpaque(false);
        layout = new MigLayout("wrap, fill", "[center]","push[]25[]10[]25[]push");
        setLayout(layout);
        init();
    }
    private void init(){
        title = new JLabel("Welcome Back!");
        title.setFont(new Font("sansserif", 1, 30));
        title.setForeground(blue_);
        add(title);
        description = new JLabel("To keep connected with us please");
        description.setForeground(new Color(245,245,245));
        add(description);
        description1 = new JLabel("login with your personal info");
        description1.setForeground(new Color(245,245,245));
        add(description1);
        button = new ButtonOutLine();
        button.setBackground(new Color(255,255,255));
        button.setForeground(new Color(255,255,255) );
        button.setText("SIGN IN");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.actionPerformed(e);
            }
        });
        add(button,"w 60%, h 39");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>                        
    @Override
    protected void paintComponent(Graphics grphcs){
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gra = new GradientPaint(0, 0, dark_Gray, 0, getHeight(), dark_Gray);
        g2.setPaint(gra);
        Area area = new Area(createRoundTopLeft());
        if (roundTopRight > 0) {
            area.intersect(new Area(createRoundTopRight()));
        }
        if (roundBottomLeft > 0) {
            area.intersect(new Area(createRoundBottomLeft()));
        }
        if (roundBottomRight > 0) {
            area.intersect(new Area(createRoundBottomRight()));
        }
        g2.fill(area);
        g2.dispose();
        super.paintComponent(grphcs);
    }
    public void addEvent(ActionListener event){
        this.event=event;
    }

    public void registerLeft(double v){
        v=Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -"+v+"% 0 0");
        layout.setComponentConstraints(description, "pad 0 -"+v+"% 0 0");
        layout.setComponentConstraints(description1, "pad 0 -"+v+"% 0 0");
    }
    public void RegisterRight(double v){
        v=Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -"+v+"% 0 0");
        layout.setComponentConstraints(description, "pad 0 -"+v+"% 0 0");
        layout.setComponentConstraints(description1, "pad 0 -"+v+"% 0 0");
    }
    public void loginLeft(double v){
        v=Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(title, "pad 0 "+v+"% 0 "+v+"%");
        layout.setComponentConstraints(description, "pad 0 "+v+"% 0 "+v+"%");
        layout.setComponentConstraints(description1, "pad 0 "+v+"% 0 "+v+"%");
    }
    public void loginRight(double v){
        v=Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(title, "pad 0 "+v+"% 0 "+v+"%");
        layout.setComponentConstraints(description, "pad 0 "+v+"% 0 "+v+"%");
        layout.setComponentConstraints(description1, "pad 0 "+v+"% 0 "+v+"%");
    }
    public void login(boolean login){
        if(this.isLogin!=login){
            if(login){
                title.setText("Hello, Friend!");
                description.setText("Enter your personal details");
                description1.setText("and start jourey with us ");
                button.setText("SIGN UP");

            }else{
                title.setText("Welcome Back!");
                description.setText("To keep connected with us please");
                description1.setText("login with your personal info");
                button.setText("SIGN IN");
            }
            this.isLogin=login;
        }
    }

    private Shape createRoundTopLeft() {
        int width = getWidth();
        int height = getHeight();
        double roundX = Math.min(width, roundTopLeft);
        double roundY = Math.min(height, roundTopLeft);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
        return area;
    }

    private Shape createRoundTopRight() {
        int width = getWidth();
        int height = getHeight();
        double roundX = Math.min(width, roundTopRight);
        double roundY = Math.min(height, roundTopRight);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
        return area;
    }

    private Shape createRoundBottomLeft() {
        int width = getWidth();
        int height = getHeight();
        double roundX = Math.min(width, roundBottomLeft);
        double roundY = Math.min(height, roundBottomLeft);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
        return area;
    }

    private Shape createRoundBottomRight() {
        int width = getWidth();
        int height = getHeight();
        double roundX = Math.min(width, roundBottomRight);
        double roundY = Math.min(height, roundBottomRight);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
        return area;
    }
    // Variables declaration - do not modify                     
    // End of variables declaration                   
}