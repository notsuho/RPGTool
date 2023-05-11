package fi.rpgtool.gui.panel;

import fi.rpgtool.data.Pair;
import fi.rpgtool.gui.window.MainWindow;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Attribuuttipaneeli eli ominaisuuspaneeli. Sisältää tietoa hahmon ominaisuuksista, joista saa bonuksia nopanheittoon.
 */
public class AttributePanel extends JPanel {

    private static final String[] ATTRIBUTE_VALUES = {"VOIMA", "ÄLY", "NOPEUS", "OVELUUS"};
    private final List<Pair<String, JSpinner>> data = new ArrayList<>();

    public AttributePanel(MainWindow mainWindow) {

        this.setLayout(new GridLayout(4, 2));
        this.setBorder(new TitledBorder("OMINAISUUDET"));

        for (String attribute : ATTRIBUTE_VALUES) {

            int attributeValue = mainWindow.getCharacter().getAttribute(attribute);

            JSpinner spinner = new JSpinner(new SpinnerNumberModel(attributeValue, 10, 20, 1));
            ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);

            JLabel attributeBonus = new JLabel("+" + (attributeValue - 10));
            attributeBonus.setHorizontalAlignment(SwingConstants.CENTER);
            attributeBonus.setBorder(new TitledBorder("BONUS"));

            // changelistener joka päivittää "bonus" kentän arvoa, tällä hetkellä vain
            // yksinkertainen bonus = ominaisuus - 10 niin ei mennä turhan monimutkaiseksi
            spinner.addChangeListener(change -> {
                attributeBonus.setText("+" + ((int)spinner.getValue() - 10));
                mainWindow.setUnsaved(true);
            });

            data.add(new Pair<>(attribute, spinner));

            this.add(new JLabel(attribute));
            this.add(spinner);
            this.add(attributeBonus);
        }

    }

    public List<Pair<String, JSpinner>> getData() {
        return data;
    }

}
