package fi.rpgtool.gui.element;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ScrollablePanel extends JPanel {

    private final List<CellPanel> cells = new ArrayList<>();
    private final JPanel items;

    public ScrollablePanel() {

        this.items = new JPanel();
        this.items.setLayout(new BoxLayout(items, BoxLayout.Y_AXIS));

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(items, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
    }

    public void addItems(List<String> items) {

        for (String item : items) {

            CellPanel cellPanel = new CellPanel(item, this);

            cells.add(cellPanel);
            this.items.add(cellPanel);
        }

        updateLastAndRevalidate();
    }

    public void addItem(String item) {

        CellPanel cellPanel = new CellPanel(item, this);

        cells.add(cellPanel);
        items.add(cellPanel);

        updateLastAndRevalidate();
    }

    public void removeItem(int i) {
        cells.remove(i);
        items.remove(i);
        updateLastAndRevalidate();
    }

    public void removeItem(CellPanel panel) {
        cells.remove(panel);
        items.remove(panel);
        updateLastAndRevalidate();
    }

    private void updateLastAndRevalidate() {
        if (cells.size() > 0) {
            cells.get(Math.max(0, cells.size() - 2)).setLast(false);
            cells.get(Math.max(0, cells.size() - 1)).setLast(true);
        }

        items.revalidate();
        items.repaint();
    }

    public List<CellPanel> getCells() {
        return cells;
    }

    private static class CellPanel extends JPanel {

        private static final int GAP = 4;

        private final ScrollablePanel parent;
        private final JTextField textField;
        private final JButton button;

        public CellPanel(String content, ScrollablePanel parent) {

            this.parent = parent;

            this.setLayout(new BorderLayout());
            this.setPreferredSize(new Dimension(500, 50));
            this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

            Border emptyBorder = BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP);
            Border lineBorder = BorderFactory.createLineBorder(Color.black);

            this.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));

            this.textField = new JTextField(content);
            textField.setPreferredSize(new Dimension(500, 30));

            this.button = new JButton();

            this.add(button, BorderLayout.EAST);
            this.add(textField, BorderLayout.WEST);
        }

        public void setLast(boolean last) {

            if (last) {

                for (ActionListener listener : button.getActionListeners()) {
                    button.removeActionListener(listener);
                }

                button.setText("+");
                button.addActionListener(action -> parent.addItem(null));

            } else {

                for (ActionListener listener : button.getActionListeners()) {
                    button.removeActionListener(listener);
                }

                button.setText("X");
                button.addActionListener(action -> parent.removeItem(this));
            }

        }

        public JTextField getTextField() {
            return textField;
        }

        public JButton getButton() {
            return button;
        }

    }


}