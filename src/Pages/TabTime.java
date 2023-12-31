package Pages;

import CustomComponent.CustomPanel.MaterialTabbed;

import static CustomComponent.StaticClass.Properties.blue_;

public class TabTime extends MaterialTabbed {
    TabTime() {
//        this.setBackground(light_Gray);
//        this.setBorder(new e);
        this.setForeground(blue_);
        for (int i = 0; i < 3; i++) {
            Ticketing ticketing = new Ticketing();
            this.addTab("show " + i, ticketing);
        }
    }

}
