package Pages;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LeftSidePanels extends JPanel {
    public LeftSidePanels(LayoutManager layoutManager, Color color, Border border) {
        super(layoutManager);
        this.setBackground(color);
        this.setBorder(border);
    }

    public LeftSidePanels(LayoutManager layoutManager, Color color) {
        super(layoutManager);
        this.setBackground(color);
    }
}
