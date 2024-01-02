package Pages;

import CustomComponent.Text.MyText;
import CustomComponent.Text.ShadingTextField;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import static CustomComponent.StaticClass.Properties.light_Gray;
import static CustomComponent.Text.MyText.movieShowTime_1;

public class InputShowStart extends JPanel {
    public InputShowStart(MyText showNum) {
        this.setLayout(new MigLayout("center", "push[center]push"));
        ShadingTextField showTimeDateStartField = new ShadingTextField();
        //dd/MM/yyyy hh:mm a
        showTimeDateStartField.setHint("Start: dd/MM/yyyy");
        ShadingTextField showTimeDateEndField = new ShadingTextField();
        showTimeDateEndField.setHint("End: dd/MM/yyyy");
        ShadingTextField showTimeHourStartField = new ShadingTextField();
        showTimeHourStartField.setHint("Start: hh:mm a");
        ShadingTextField showTimeHourEndField = new ShadingTextField();
        showTimeHourEndField.setHint("End: hh:mm a");

        this.setBackground(light_Gray);
        this.add(showNum, "wrap");
        this.add(showTimeDateStartField, "split 2,w 50%");
        this.add(showTimeDateEndField, "wrap,w 50%");
        this.add(showTimeHourStartField, "split 2,w 50%");
        this.add(showTimeHourEndField, "wrap,w 50%");
    }
}
