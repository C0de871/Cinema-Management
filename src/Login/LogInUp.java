package Login;

import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import static CustomComponent.Properties.light_Gray;

public class LogInUp extends javax.swing.JFrame {
    static Animator animator;
    private MigLayout layout;
    private PanelCover cover;
    private PanelLoginAndRegister loginAndRegister;
    private boolean isLogin;
    private final double addSize=30;
    private final double coverSize=40;
    private final double loginSize=60;
    private final DecimalFormat df = new DecimalFormat("##0.###");

    public LogInUp() {
        initComponents();
        init();
    }
    private void init(){
        layout = new MigLayout("fill,insets 0");
        cover = new PanelCover();
        cover.setRoundBottomRight(300);
        cover.setRoundTopRight(300);
        loginAndRegister = new PanelLoginAndRegister();
        TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void timingEvent(float fraction) {
                double fractionCover;
                double fractionLogin;
                double size = coverSize;
                double roundBottomRight = cover.getRoundBottomRight();
                double roundTopRight = cover.getRoundTopRight();
                double roundBottomLeft = cover.getRoundBottomLeft();
                double roundTopLeft = cover.getRoundTopLeft();
                if(fraction<=0.5f){
                    size+=fraction*addSize;
//                    System.out.println("s"+size);
                }else{
                    size+=addSize-fraction*addSize;
//                    System.out.println(size);
                }
                if(isLogin){
//                    System.out.println("I am the register");
                    fractionCover = 1f - fraction;
                    fractionLogin=fraction;
                    roundBottomRight = (fraction) * 300;
                    roundTopRight = (fraction) * 300;
                    roundBottomLeft = (1f - fraction) * 300;
                    roundTopLeft = (1f - fraction) * 300;
                    if(fraction>=0.5f){
                        cover.RegisterRight(fractionCover*100);
                    }else{
                        cover.loginRight(fractionLogin*100);
                    }
                }else{
//                    System.out.println("I am the login");
                    roundBottomRight = (1f - fraction) * 300;
                    roundTopRight = (1f - fraction) * 300;
                    roundBottomLeft = fraction * 300;
                    roundTopLeft = fraction * 300;
                    fractionCover= fraction;
                    fractionLogin= 1f - fraction;
                    if(fraction<=0.5f){
                        cover.registerLeft(fraction*100);
                    }else{
                        cover.loginLeft(fractionLogin*100);
                    }
                }
                if(fraction>=0.5f){
                    loginAndRegister.showRegister(isLogin);
                }
                fractionCover= Double.valueOf(df.format(fractionCover));
                fractionLogin= Double.valueOf(df.format(fractionLogin));
                cover.setRoundBottomRight(roundBottomRight);
                cover.setRoundTopRight(roundTopRight);
                cover.setRoundBottomLeft(roundBottomLeft);
                cover.setRoundTopLeft(roundTopLeft);
                layout.setComponentConstraints(cover, "width "+ size +"%, pos " + fractionCover+ "al 0 n 100%");
                layout.setComponentConstraints(loginAndRegister, "width "+ loginSize +"%, pos " + fractionLogin+ "al 0 n 100%");
                bg.revalidate();
            }

            @Override
            public void end() {
                isLogin= !isLogin;
            }
        };
        animator = new Animator(580, target);
        animator.setAcceleration(0.5f);
        animator.setResolution(0);
        bg.setLayout(layout);
        bg.add(cover, "width " + coverSize + "%, pos 0al 0 n 100%");
        bg.add(loginAndRegister, "width " + loginSize + "%, pos 1al 0 n 100%");
        cover.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!animator.isRunning()){
                    animator.start();
                }
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(light_Gray);
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 515, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }
    // Variables declaration - do not modify                     
    private javax.swing.JLayeredPane bg;
    // End of variables declaration                   
}
