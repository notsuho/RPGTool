package fi.rpgtool.gui.window;

import fi.rpgtool.gui.element.ScrollablePanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class InventoryWindow extends JPanel {

    private final JTextArea notes;
    private final ScrollablePanel items;

    public InventoryWindow(MainWindow mainWindow) {
        this.setLayout(new BorderLayout());

        // tänne esinelista ja muistiinpanokenttä
        this.items = new ScrollablePanel();
        this.items.setBorder(new TitledBorder("Tavaraluettelo"));
        this.items.setPreferredSize(new Dimension(400, 400));

        for (int i = 0; i < 20; i++) {
            this.items.addItem(i);
        }

        this.notes = new JTextArea(mainWindow.getCharacter().getNotes());
        this.notes.setBorder(new TitledBorder("Muistiinpanot"));
        this.notes.setPreferredSize(new Dimension(400, 300));

        this.add(items, BorderLayout.PAGE_START);
        this.add(notes, BorderLayout.PAGE_END);
    }

    public JTextArea getNotes() {
        return notes;
    }

    public ScrollablePanel getItems() {
        return items;
    }

}
