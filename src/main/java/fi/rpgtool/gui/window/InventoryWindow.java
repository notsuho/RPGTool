package fi.rpgtool.gui.window;

import fi.rpgtool.data.Character;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class InventoryWindow extends JPanel {

    private final JTextArea notes;

    public InventoryWindow(Character character) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // tänne esinelista ja muistiinpanokenttä

        this.notes = new JTextArea(character.getNotes());
        this.notes.setBorder(new TitledBorder("Muistiinpanot"));

        this.add(notes);
    }

    public JTextArea getNotes() {
        return notes;
    }


}
