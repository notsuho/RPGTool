package fi.rpgtool.gui;

import javax.swing.*;
import java.awt.*;

public abstract class RPGPanel extends JPanel {

    private final GridLayout layout = new GridLayout();

    public RPGPanel(int rows, int columns) {

        setGridDimensions(rows, columns);
        this.setLayout(layout);
    }

    public void add(RPGComponent component) {

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = component.getGridX();
        constraints.gridy = component.getGridY();
        constraints.gridwidth = component.getRows();
        constraints.gridheight = component.getHeight();
        constraints.fill = component.getFill();

        this.add(component.getComponent(), constraints);
    }

    public void add(Component component, int gridx, int gridy, int width, int height, int fill) {

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        constraints.fill = fill;
        constraints.weightx = 1;
        constraints.weighty = 1;

        this.add(component, constraints);
    }

    public void setGridDimensions(int rows, int columns) {
        layout.setRows(rows);
        layout.setColumns(columns);
    }

}
