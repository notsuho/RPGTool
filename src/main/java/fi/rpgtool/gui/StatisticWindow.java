package fi.rpgtool.gui;

import javax.swing.*;

public class StatisticWindow extends RPGFrame {

    public StatisticWindow(int width, int height) {
        super(10, 10);

        RPGComponent label = new RPGComponent(new JLabel("Statistic component:"));

        label.setGridPosition(0, 0);
        label.setBounds(0, 0, 100, 100);

        RPGComponent button = new RPGComponent(new JButton("Roll"));

        button.setGridPosition(2, 10);
        button.setGridDimensions(2, 2);
        button.setBounds(100, 100, 100, 100);

        //button.setFill(GridBagConstraints.HORIZONTAL);

        this.add(label);
        this.add(button);

        this.setSize(width, height);
    }

}
