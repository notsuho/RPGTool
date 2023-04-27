package fi.rpgtool.gui;

import java.awt.*;

/**
 * Wrapper for {@link java.awt.Component} to make code cleaner
 */
public class RPGComponent extends Component {

    private final Component component;
    private int x = 0;
    private int y = 0;
    private int rows = 1;
    private int columns = 1;
    private int fill = 0;

    public RPGComponent(Component component) {
        this.component = component;
    }

    public Component getComponent() {
        return component;
    }

    public void setGridPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setGridDimensions(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public void setFill(int fill) {
        this.fill = fill;
    }

    public int getGridX() {
        return x;
    }

    public int getGridY() {
        return y;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getFill() {
        return fill;
    }

}
