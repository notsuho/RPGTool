package fi.rpgtool.gui.panel;

import fi.rpgtool.data.Character;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import javax.swing.event.*;


public class AttributePanel extends JPanel {

    private final String[] attributes = { "VOIMA", "NOPEUS", "ÄLY", "OVELUUS" };

    public AttributePanel(Character character) {

        this.setLayout(new GridLayout(4, 2));
        this.setBorder(new TitledBorder("OMINAISUUDET"));

        for (String attribute : attributes) {

            JSpinner spinner = new JSpinner(new SpinnerNumberModel(10, 10, 20, 1));
            ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);

            JLabel attributeBonus = new JLabel("+0");
            attributeBonus.setHorizontalAlignment(SwingConstants.CENTER);
            attributeBonus.setBorder(new TitledBorder("BONUS"));

            this.add(new JLabel(attribute));
            this.add(spinner);
            this.add(attributeBonus);

            // changelistener joka päivittää "bonus" kentän arvoa, tällä hetkellä vain
            // yksinkertainen bonus = ominaisuus - 10 niin ei mennä turhan monimutkaiseksi
            spinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    JSpinner spinner = (JSpinner) e.getSource();
                    int value = (int) spinner.getValue();
                    // testiprinttiä terminaalin, voi poistaa myöhemmin
                    System.out.println("Value is " + value);
                    attributeBonus.setText("+" + String.valueOf(value - 10));
                }
            });
        }

    }

}
