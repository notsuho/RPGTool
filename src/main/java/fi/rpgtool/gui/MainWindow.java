package fi.rpgtool.gui;

import javax.swing.*;

public class MainWindow extends RPGFrame {

    public MainWindow(int width, int height) {
        super(10, 10);

        RPGComponent label = new RPGComponent(new JLabel("Skills:"));

        label.setGridPosition(0, 0);
        label.setBounds(0, 0, 100, 100);

        RPGComponent button = new RPGComponent(new JButton("Roll"));

        button.setGridPosition(2, 10);
        button.setGridDimensions(2, 2);
        button.setBounds(100, 100, 100, 100);

        //button.setFill(GridBagConstraints.HORIZONTAL);

        // Siirrä tämä johonkin parempaan paikkaan
        this.add(label);
        this.add(button);

        this.setSize(width, height);
    }

}
