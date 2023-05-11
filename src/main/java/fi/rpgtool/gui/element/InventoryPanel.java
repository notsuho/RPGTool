package fi.rpgtool.gui.element;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Tavarapaneeli tavaroiden säilyttämistä varten. Yksittäiset tavarat ovat "soluja", jotka ovat luokan {@link ItemCell} objekteja.
 */
public class InventoryPanel extends JPanel {

    private final List<ItemCell> cells = new ArrayList<>();
    private final JScrollPane pane;
    private final JPanel items;

    public InventoryPanel() {

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

        ItemCell itemCell = new ItemCell(item, this);

        cells.add(itemCell);
        items.add(itemCell);

        updateLastAndRevalidate();

        int height = (int) items.getPreferredSize().getHeight();
        this.pane.getVerticalScrollBar().setValue(height);
        items.scrollRectToVisible(new Rectangle(0, height, 10, 10));
    }

    public void removeItem(ItemCell panel) {
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

    public List<ItemCell> getCells() {
        return cells;
    }

    /**
     * Yksittäinen solu tämän paneelin (siis tavaralistan) yksittäisen tavaran säilyttämisen.
     * Sisältää tekstikentän johon voi kirjoittaa tietoja tavarasta sekä napin, jolla voi joko poistaa tavaran
     * tai lisätä uuden tavaran riippuen siitä, onko tavara merkitty listan viimeiseksi vai ei.
     */
    public static class ItemCell extends JPanel {

        private static final int GAP = 4;

        private final InventoryPanel parent;
        private final JTextField textField;
        private final JButton button;

        public ItemCell(String content, InventoryPanel parent) {

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

        /**
         * Päivittää solun napin sisällön ja tapahtumat
         * @param last onko tämä solu viimeinen solu vai ei
         */
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