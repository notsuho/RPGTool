package fi.rpgtool.gui.element;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
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

    public void addItem(int i) {
        cells.add(new CellPanel(this, i));
        rebuildList();
    }

    public void removeItem(int i) {
        cells.remove(i);
        rebuildList();
    }

    private void rebuildList() {

        items.removeAll();

        int index = 0;

        for (CellPanel panel : cells) {
            panel.setIndex(index);
            items.add(panel);
            index++;
        }

        items.revalidate();
        items.repaint();
    }

    public List<CellPanel> getCells() {
        return cells;
    }

    private static class CellPanel extends JPanel {

        private static final int GAP = 4;

        private final JTextField textField;
        private final JButton button;
        private int index;
        private boolean isLast = false;

        public CellPanel(ScrollablePanel parent, int index) {

            this.index = index;

            setLayout(new BorderLayout());

            Border emptyBorder = BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP);
            Border lineBorder = BorderFactory.createLineBorder(Color.black);

            this.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));

            this.textField = new JTextField("Name " + this.index);
            textField.setPreferredSize(new Dimension(500, 30));

            this.button = new JButton();

            if (isLast) {
                button.setText("+");
            } else {
                button.setText("X");
                button.addActionListener(action -> parent.removeItem(getIndex()));
            }

            this.add(button, BorderLayout.EAST);
            this.add(textField, BorderLayout.WEST);
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public void setLast(boolean last) {
            this.isLast = last;
        }

        public JTextField getTextField() {
            return textField;
        }

        public JButton getButton() {
            return button;
        }

    }


}