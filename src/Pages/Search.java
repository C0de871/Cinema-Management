package Pages;

import CustomComponent.StaticClass.MyIcon;
import CustomComponent.Text.MyTextField;

import java.awt.*;

public class Search extends MyTextField {

    public Search(Color color) {
        super(color);
        this.setPrefixIcon(MyIcon.search);
        this.setHint("Search Movies");
    }
}
