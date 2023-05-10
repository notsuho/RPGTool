package fi.rpgtool.gui.element;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ScrollablePanel extends JPanel {

    private final JPanel items;

    public ScrollablePanel() {

        this.items = new JPanel();
        this.items.setLayout(new BoxLayout(items, BoxLayout.Y_AXIS));

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(items), BorderLayout.CENTER);
    }

    public void addItem(int i) {
        items.add(new CellPanel(i));
        items.revalidate();
        items.repaint();
    }

    private static class CellPanel extends JPanel {

        private static final int gap = 4;

        private final String name;
        private final int index;

        public CellPanel(int i) {
            this.index = i;
            this.name = "Name " + index;

            setLayout(new GridBagLayout());

            Border emptyBorder = BorderFactory.createEmptyBorder(gap, gap, gap, gap);
            Border lineBorder = BorderFactory.createLineBorder(Color.black);
            setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));

            add(new JLabel(name), makeConstraints(0, 0, GridBagConstraints.WEST));
            add(new JLabel(""), makeConstraints(1, 0, GridBagConstraints.EAST));
            add(new JButton(new MyBtnAction("X")), makeConstraints(2, 0, GridBagConstraints.EAST));
        }

        public int getIndex() {
            return index;
        }

        private GridBagConstraints makeConstraints(int x, int y, int anchor) {

            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = x;
            gbc.gridy = y;
            gbc.gridwidth = 1;
            gbc.weightx = 1.0f;
            gbc.weighty = 1.0f;
            gbc.anchor = anchor;

            return gbc;
        }

        private class MyBtnAction extends AbstractAction {

            public MyBtnAction(String name) {
                super(name);
            }

            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Button pressed for " + name);
            }
        }

    }


}