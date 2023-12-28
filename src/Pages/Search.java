package Pages;

import CustomComponent.MyIcon;
import CustomComponent.MyTextField;

import java.awt.*;

public class Search extends MyTextField {

    public Search(Color color) {
        super(color);
        this.setPrefixIcon(MyIcon.search);
        this.setHint("Search Movies");
    }
}
