package fi.rpgtool.gui.window;

import fi.rpgtool.gui.RPGComponent;
import fi.rpgtool.gui.RPGPanel;
import fi.rpgtool.gui.RollButton;

import javax.swing.*;

public class StatisticWindow extends JPanel {

    private JPanel stats = null;

    public StatisticWindow() {
        stats = new JPanel();
        stats.setLayout(new BoxLayout(stats, BoxLayout.Y_AXIS));
    }
}

// public class StatisticWindow extends RPGPanel {

//     public StatisticWindow(int width, int height) {
//         super(10, 9);

//         RPGComponent label = new RPGComponent(new JLabel("Statistic component:"));

//         label.setGridPosition(0, 0);
//         label.setBounds(0, 0, 100, 100);

//         JLabel dummy1 = new JLabel();
//         JLabel dummy2 = new JLabel();

//         RollButton button = new RollButton();

//         button.setBounds(100, 100, 100, 100);

//         this.add(dummy1, 0, 9, 3, 1, 0);
//         this.add(button, 3, 9, 3, 1, 0);
//         this.add(dummy2, 6, 9, 3, 1, 0);

//         this.add(label);

//         this.setSize(width, height);
//     }

// }
