package fi.rpgtool.gui.panel;

import fi.rpgtool.data.Character;
import fi.rpgtool.data.CharacterHandler;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Map;

public class AttributePanel extends JPanel {

    public AttributePanel(Character character) {

        this.setLayout(new GridLayout(4, 2));
        this.setBorder(new TitledBorder("OMINAISUUDET"));

        for (Map.Entry<String, Integer> attribute : character.getAttributes().entrySet()) {

            JSpinner spinner = new JSpinner(new SpinnerNumberModel(attribute.getValue().intValue(), 10, 20, 1));
            ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);

            JLabel attributeBonus = new JLabel("+0");
            attributeBonus.setHorizontalAlignment(SwingConstants.CENTER);
            attributeBonus.setBorder(new TitledBorder("BONUS"));

            // changelistener joka päivittää "bonus" kentän arvoa, tällä hetkellä vain
            // yksinkertainen bonus = ominaisuus - 10 niin ei mennä turhan monimutkaiseksi
            spinner.addChangeListener(change -> {
                CharacterHandler.getCharacter().setAttribute(attribute.getKey(), (int) spinner.getValue());
                attributeBonus.setText("+" + ((int)spinner.getValue() - 10));
            });

            this.add(new JLabel(attribute.getKey()));
            this.add(spinner);
            this.add(attributeBonus);
        }

    }

}
