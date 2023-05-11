package fi.rpgtool.gui.element;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ScrollablePanel extends JPanel {

    private final List<CellPanel> cells = new ArrayList<>();
    private final JScrollPane pane;
    private final JPanel items;

    public ScrollablePanel() {

        this.items = new JPanel();
        this.items.setLayout(new BoxLayout(items, BoxLayout.Y_AXIS));

        this.setLayout(new BorderLayout());

        this.pane = new JScrollPane(items, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pane.getVerticalScrollBar().setUnitIncrement(16);

        this.add(pane, BorderLayout.CENTER);
    }

    public void addItems(List<String> items) {
        for (String item : items) {
            addItem(item);
        }
    }

    public void addItem(String item) {

        CellPanel cellPanel = new CellPanel(item, this);

        cells.add(cellPanel);
        items.add(cellPanel);

        this.pane.getVerticalScrollBar().setValue(Integer.MAX_VALUE);

        updateLastAndRevalidate();

        this.pane.getVerticalScrollBar().setValue((int)items.getPreferredSize().getHeight());
    }

    public void removeItem(CellPanel panel) {
        cells.remove(panel);
        items.remove(panel);
        updateLastAndRevalidate();
    }

    private void updateLastAndRevalidate() {

        if (cells.size() > 0) {
            cells.get(Math.max(0, cells.size() - 2)).update(false);
            cells.get(Math.max(0, cells.size() - 1)).update(true);
        }

        items.revalidate();
        items.repaint();
    }

    public List<CellPanel> getCells() {
        return cells;
    }

    public static class CellPanel extends JPanel {

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

            this.button = new JButton();

            this.add(textField, BorderLayout.CENTER);
            this.add(button, BorderLayout.EAST);
        }

        public void update(boolean last) {

            if (last) {

                // Poistetaan kaikki muut elementit (niitä pitäisi olla vain 1) jotta yksi nappi ei yhtäkkiä tee kahta asiaa
                for (ActionListener listener : button.getActionListeners()) {
                    button.removeActionListener(listener);
                }

                button.setText("+");
                button.addActionListener(action -> parent.addItem(null));

            } else {

                // Poistetaan kaikki muut elementit (niitä pitäisi olla vain 1) jotta yksi nappi ei yhtäkkiä tee kahta asiaa
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

    }


}