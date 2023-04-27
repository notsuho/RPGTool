package fi.rpgtool.gui;

import javax.swing.*;
import java.awt.*;

public abstract class RPGFrame extends JFrame {

    private final GridBagConstraints constraints = new GridBagConstraints();
    private final GridLayout layout = new GridLayout();

    public RPGFrame(int rows, int columns) {

        setGridDimensions(rows, columns);

        this.setLayout(layout);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void add(RPGComponent component) {

        constraints.gridx = component.getGridX();
        constraints.gridy = component.getGridY();
        constraints.gridwidth = component.getRows();
        constraints.gridheight = component.getHeight();
        constraints.fill = component.getFill();

        this.add(component.getComponent(), constraints);
    }

    public void setGridDimensions(int rows, int columns) {
        layout.setRows(rows);
        layout.setColumns(columns);
    }

}
