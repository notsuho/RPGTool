package fi.rpgtool.gui.window;

import fi.rpgtool.gui.element.InventoryPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class InventoryWindow extends JPanel {

    private final JTextArea notes;
    private final InventoryPanel items;

    public InventoryWindow(MainWindow mainWindow) {
        this.setLayout(new BorderLayout());

        this.items = new InventoryPanel();
        this.items.setBorder(new TitledBorder("Tavaraluettelo"));
        this.items.setPreferredSize(new Dimension(400, 400));

        this.items.addItems(mainWindow.getCharacter().getInventory());
        this.items.addItem(null);

        this.notes = new JTextArea(mainWindow.getCharacter().getNotes());
        this.notes.setBorder(new TitledBorder("Muistiinpanot"));
        // this.notes.setPreferredSize(new Dimension(400, 300));
        this.notes.setLineWrap(true);

        JScrollPane notePane = new JScrollPane(notes, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        notePane.setPreferredSize(new Dimension(400, 300));

        this.add(items, BorderLayout.CENTER);
        this.add(notePane, BorderLayout.SOUTH);
    }

    public JTextArea getNotes() {
        return notes;
    }

    public InventoryPanel getItems() {
        return items;
    }

}
