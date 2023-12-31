package CustomComponent.CustomPanel;

import CustomComponent.StaticClass.MyIcon;
import CustomComponent.Text.MyText;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import static CustomComponent.StaticClass.MyPanels.*;
import static CustomComponent.StaticClass.Properties.*;

public class AnimatedPanel extends JPanel {
//    public void setPrefixIcon(Icon prefixIcon) {
//        this.prefixIcon = prefixIcon;
//        initBorder();
//    }
//    public void settext(String text) {
//        this.text = text;
//    }


    public Color getEffectColor() {
        return effectColor;
    }

    public void setEffectColor(Color effectColor) {
        this.effectColor = effectColor;
    }

    //    private String text = "";
    private Animator animator;
    private int targetSize;
    private float animatSize;
    private Point pressedPoint;
    private float alpha;
    private Color effectColor = new Color(255, 255, 255);
    private Icon prefixIcon;
    private Icon suffixIcon;
    private MyText text;
    JLabel icon;
    MouseListener listener;

    public String getText() {
        return text.getText();
    }

    public void setText() {
        text.setForeground(light_Blue);
    }

    public void setIcon() {
        if (icon.getIcon() == MyIcon.clickeddiscover) icon.setIcon(MyIcon.discover);
        else if (icon.getIcon() == MyIcon.clickedtrending) icon.setIcon(MyIcon.trending);
        else if (icon.getIcon() == MyIcon.clickedupcoming) icon.setIcon(MyIcon.upcoming);
        else if (icon.getIcon() == MyIcon.clickedheart) icon.setIcon(MyIcon.heart);
        else if (icon.getIcon() == MyIcon.clickeddot) icon.setIcon(MyIcon.dot);
    }

    public AnimatedPanel(MigLayout migLayout, JLabel icon, MyText text) {
        this.text = text;
        this.icon = icon;
        this.setBackground(Color.decode("#15151d"));
        this.add(this.icon);
        this.add(text);
        setLayout(migLayout);
//        setContentAreaFilled(false);
        setOpaque(false);
        setBorder(new EmptyBorder(5, 0, 5, 0));
//        setBackground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        listener = new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Do nothing
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Do nothing
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                currentPage = text.getText();
//                 Print a message when the panel is clicked
                setBackground(Color.decode("#232331"));
                text.changeColor("#2585f8");
                if (icon.getIcon() == MyIcon.discover) icon.setIcon(MyIcon.clickeddiscover);
                else if (icon.getIcon() == MyIcon.trending) icon.setIcon(MyIcon.clickedtrending);
                else if (icon.getIcon() == MyIcon.upcoming) icon.setIcon(MyIcon.clickedupcoming);
                else if (icon.getIcon() == MyIcon.heart) icon.setIcon(MyIcon.clickedheart);
                else if (icon.getIcon() == MyIcon.dot) icon.setIcon(MyIcon.clickeddot);
                System.out.println(text.getText());
                cardLayout.show(cardPanel, text.getText());
                if (discoverBar.getText() != currentPage) {
                    discoverBar.setText();
                    discoverBar.setBackground(dark_Gray);
                    discoverBar.setIcon();
                }
                if (trendingBar.getText() != currentPage) {
                    trendingBar.setText();
                    trendingBar.setBackground(dark_Gray);
                    trendingBar.setIcon();

                }
                if (upcomingBar.getText() != currentPage) {
                    upcomingBar.setText();
                    upcomingBar.setBackground(dark_Gray);
                    upcomingBar.setIcon();

                }
                if (collectionBar.getText() != currentPage) {
                    collectionBar.setText();
                    collectionBar.setBackground(dark_Gray);
                    collectionBar.setIcon();

                }
                if (actionBar.getText() != currentPage) {
                    actionBar.setText();
                    actionBar.setBackground(dark_Gray);
                    actionBar.setIcon();

                }
                if (dramaBar.getText() != currentPage) {
                    dramaBar.setText();
                    dramaBar.setBackground(dark_Gray);
                    dramaBar.setIcon();

                }
                if (comedyBar.getText() != currentPage) {
                    comedyBar.setText();
                    comedyBar.setBackground(dark_Gray);
                    comedyBar.setIcon();

                }
                if (adventureBar.getText() != currentPage) {
                    adventureBar.setText();
                    adventureBar.setBackground(dark_Gray);
                    adventureBar.setIcon();

                }
                if (documentaryBar.getText() != currentPage) {
                    documentaryBar.setText();
                    documentaryBar.setBackground(dark_Gray);
                    documentaryBar.setIcon();

                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Color.decode("#232331"));
                text.changeColor("#2585f8");
                if (icon.getIcon() == MyIcon.discover) icon.setIcon(MyIcon.clickeddiscover);
                else if (icon.getIcon() == MyIcon.trending) icon.setIcon(MyIcon.clickedtrending);
                else if (icon.getIcon() == MyIcon.upcoming) icon.setIcon(MyIcon.clickedupcoming);
                else if (icon.getIcon() == MyIcon.heart) icon.setIcon(MyIcon.clickedheart);
                else if (icon.getIcon() == MyIcon.dot) icon.setIcon(MyIcon.clickeddot);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (currentPage != text.getText()) {
                    setBackground(Color.decode("#15151d"));
                    text.changeColor("#B8BFF4");
                    if (icon.getIcon() == MyIcon.clickeddiscover) icon.setIcon(MyIcon.discover);
                    else if (icon.getIcon() == MyIcon.clickedtrending) icon.setIcon(MyIcon.trending);
                    else if (icon.getIcon() == MyIcon.clickedupcoming) icon.setIcon(MyIcon.upcoming);
                    else if (icon.getIcon() == MyIcon.clickedheart) icon.setIcon(MyIcon.heart);
                    else if (icon.getIcon() == MyIcon.clickeddot) icon.setIcon(MyIcon.dot);
                }
            }
        };
        addMouseListener(listener);


        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                targetSize = Math.max(getWidth(), getHeight()) * 2;
                animatSize = 0;
                pressedPoint = me.getPoint();
                alpha = 0.5f;
                if (animator.isRunning()) {
                    animator.stop();
                }
                animator.start();
            }
        });
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (fraction > 0.5f) {
                    alpha = 1 - fraction;
                }
                animatSize = fraction * targetSize;
                repaint();
            }
        };
        animator = new Animator(700, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        int width = getWidth();
        int height = getHeight();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRect(0, 0, width, height);
        if (pressedPoint != null) {
            g2.setColor(effectColor);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            g2.fillRect((int) (pressedPoint.x - animatSize / 2), (int) (pressedPoint.y - animatSize / 2), (int) animatSize, (int) animatSize);
        }
        g2.dispose();
        grphcs.drawImage(img, 0, 0, null);
//        paintIcon(grphcs);
        super.paintComponent(grphcs);
    }
//    private void paintIcon(Graphics g) {
//        Graphics2D g2 = (Graphics2D) g;
//        if (prefixIcon != null) {
//            Image prefix = ((ImageIcon) prefixIcon).getImage();
//            int y = (getHeight() - prefixIcon.getIconHeight()) / 2;
//            g2.drawImage(prefix, 10, y, this);
//        }
//        if (suffixIcon != null) {
//            Image suffix = ((ImageIcon) suffixIcon).getImage();
//            int y = (getHeight() - suffixIcon.getIconHeight()) / 2;
//            g2.drawImage(suffix, getWidth() - suffixIcon.getIconWidth() - 10, y, this);
//        }
//    }
//    public void paint(Graphics g) {
//        super.paint(g);
//            int h = getHeight();
//            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//            Insets ins = getInsets();
//            FontMetrics fm = g.getFontMetrics();
//            g.setColor(new Color(0, 0, 0));
//            g.drawString(text, ins.left, h / 2 + fm.getAscent() / 2 - 2);
//        }
//    private void initBorder() {
//        int left = 15;
//        int right = 15;
//        //  5 is default
//        if (prefixIcon != null) {
//            //  prefix is left
//            left = prefixIcon.getIconWidth() + 15;
//        }
//        if (suffixIcon != null) {
//            //  suffix is right
//            right = suffixIcon.getIconWidth() + 15;
//        }
//        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, left, 10, right));
//    }
}
