package Pages;

import javax.swing.*;
import java.awt.*;

public class MyText extends JLabel {
    MyText(String text,int size,String nm,int fontStyle){
        this.setText(text);
        this.setFont(new Font("sansserif",fontStyle, size));
        this.setForeground(Color.decode(nm));
    }
    void changeColor(String color){
        this.setForeground(Color.decode(color));
    }
}
