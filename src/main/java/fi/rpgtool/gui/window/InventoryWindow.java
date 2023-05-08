package fi.rpgtool.gui.window;

import fi.rpgtool.gui.RPGComponent;
import fi.rpgtool.gui.RPGPanel;

import javax.swing.*;

public class InventoryWindow extends JPanel {

    private JPanel inventory = null;

public InventoryWindow() {
    inventory = new JPanel();
    inventory.setLayout(new BoxLayout(inventory, BoxLayout.Y_AXIS));
}
}

// public class InventoryWindow extends RPGPanel {

//     public InventoryWindow(int width, int height) {
//         super(10, 9);

//         RPGComponent label = new RPGComponent(new JLabel("Some other component:"));

//         label.setGridPosition(0, 0);
//         label.setBounds(0, 0, 100, 100);

//         this.add(label);
//         this.setSize(width, height);
//     }

// }
