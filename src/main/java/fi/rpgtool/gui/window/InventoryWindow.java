package fi.rpgtool.gui.window;

import fi.rpgtool.data.Character;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class InventoryWindow extends JPanel {

    public InventoryWindow(Character character) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // tänne esinelista ja muistiinpanokenttä

        JTextArea textArea = new JTextArea(character.getNotes());
        textArea.setBorder(new TitledBorder("Muistiinpanot"));

        this.add(textArea);
    }



}
