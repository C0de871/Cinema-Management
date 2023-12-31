package CustomComponent.ScrollPane;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 *
 * @author Raven
 */
public class ScrollPaneWin11 extends JScrollPane {
    public ScrollPaneWin11(JPanel panel) {
        this.setViewportView(panel);
        this.setBackground(Color.decode("#222631"));
        this.setForeground(Color.decode("#222631"));
        this.setMinimumSize(new Dimension(0, 320));
        this.setBorder(BorderFactory.createLineBorder(Color.decode("#222631"), 2));
        getVerticalScrollBar().setUI(new ScrollBarWin11UI());
        getHorizontalScrollBar().setUI(new ScrollBarWin11UI());
        setLayout(new ScrollLayout());
    }

    public ScrollPaneWin11(JTextArea resultTextArea) {
        this.setViewportView(resultTextArea);
        this.setBackground(Color.decode("#222631"));
        this.setForeground(Color.decode("#222631"));
        this.setMinimumSize(new Dimension(0, 320));
        this.setBorder(BorderFactory.createLineBorder(Color.decode("#222631"), 2));
        getVerticalScrollBar().setUI(new ScrollBarWin11UI());
        getHorizontalScrollBar().setUI(new ScrollBarWin11UI());
        setLayout(new ScrollLayout());
    }

    @Override
    public boolean isOptimizedDrawingEnabled() {
        return false;
    }

    @Override
    public void updateUI() {
        super.updateUI();
        EventQueue.invokeLater(() -> {
            setComponentZOrder(getVerticalScrollBar(), 0);
            setComponentZOrder(getHorizontalScrollBar(), 1);
            setComponentZOrder(getViewport(), 2);
            getVerticalScrollBar().setOpaque(false);
            getHorizontalScrollBar().setOpaque(false);
        });
    }

    private class ScrollLayout extends ScrollPaneLayout {

        @Override
        public void layoutContainer(Container parent) {
            super.layoutContainer(parent);
            if (parent instanceof JScrollPane) {
                JScrollPane scroll = (JScrollPane) parent;
                Rectangle rec = scroll.getViewport().getBounds();
                Insets insets = parent.getInsets();
                int rhHeight = 0;
                if (scroll.getColumnHeader() != null) {
                    Rectangle rh = scroll.getColumnHeader().getBounds();
                    rhHeight = rh.height;
                }
                rec.width = scroll.getBounds().width - (insets.left + insets.right);
                rec.height = scroll.getBounds().height - (insets.top + insets.bottom) - rhHeight;
                if (Objects.nonNull(viewport)) {
                    viewport.setBounds(rec);
                }
                if (!Objects.isNull(hsb)) {
                    Rectangle hrc = hsb.getBounds();
                    hrc.width = rec.width;
                    hsb.setBounds(hrc);
                }
            }
        }
    }
}
