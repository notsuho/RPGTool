package fi.rpgtool.gui.element;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ScrollablePanel extends JPanel {

    private final JPanel items;

    public ScrollablePanel() {

        this.items = new JPanel();
        this.items.setLayout(new BoxLayout(items, BoxLayout.Y_AXIS));

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(items, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
    }

    public void addItem(int i) {
        items.add(new CellPanel(this, i));
        items.revalidate();
        items.repaint();
    }

    public void removeItem(int i) {
        items.remove(i);
        items.revalidate();
        items.repaint();
    }

    private static class CellPanel extends JPanel {

        private static final int GAP = 4;

        private final int index;

        public CellPanel(ScrollablePanel parent, int index) {

            this.index = index;

            setLayout(new BorderLayout());

            Border emptyBorder = BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP);
            Border lineBorder = BorderFactory.createLineBorder(Color.black);
            setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));

            //add(new JTextField(name, 100), makeConstraints(0, 0, GridBagConstraints.WEST));

            JTextField textField = new JTextField("Name " + this.index);

            textField.setPreferredSize(new Dimension(500, 30));

            add(textField, BorderLayout.WEST);
            //add(new JLabel(""), makeConstraints(1, 0, GridBagConstraints.EAST));

            JButton xButton;

            if (index == parent.items.getComponentCount() - 1) {
                xButton = new JButton("+");
            } else {
                xButton = new JButton("X");
            }

            xButton.addActionListener(action -> parent.removeItem(index));

            //add(xButton, makeConstraints(1, 0, GridBagConstraints.EAST));
            add(xButton, BorderLayout.EAST);
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

    }


}