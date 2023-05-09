package fi.rpgtool.gui.window;

import javax.swing.*;

public class InventoryWindow extends JPanel {



    public InventoryWindow() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // tänne esinelista ja muistiinpanokenttä

        JLabel noteLabel = new JLabel("Muistiinpanot");
        JTextArea textArea = new JTextArea();


        this.add(noteLabel);
        this.add(textArea);
    }



}
