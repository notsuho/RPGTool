package fi.rpgtool.gui;

import fi.rpgtool.data.Character;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class AttributePanel extends JPanel {

    private final String[] attributes = { "VOIMA", "NOPEUS", "ÄLY", "OVELUUS" };

    public AttributePanel(Character character) {

        this.setLayout(new GridLayout(4, 2));
        this.setBorder(new TitledBorder("OMINAISUUDET"));

        for (String attribute : attributes) {

            JSpinner spinner = new JSpinner(new SpinnerNumberModel(10, 10, 20, 1));
            ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);

            JLabel attributeBonus = new JLabel("0");
            attributeBonus.setBorder(new TitledBorder("BONUS"));
            attributeBonus.setHorizontalAlignment(SwingConstants.CENTER);

            this.add(new JLabel(attribute));
            this.add(spinner);
            this.add(attributeBonus);
        }

    }

}
