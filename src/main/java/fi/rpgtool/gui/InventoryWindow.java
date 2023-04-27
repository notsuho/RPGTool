package fi.rpgtool.gui;

import javax.swing.*;

public class InventoryWindow extends RPGFrame {

    public InventoryWindow(int width, int height) {
        super(10, 10);

        RPGComponent label = new RPGComponent(new JLabel("Some other component:"));

        label.setGridPosition(0, 0);
        label.setBounds(0, 0, 100, 100);

        this.add(label);
        this.setSize(width, height);
    }

}
