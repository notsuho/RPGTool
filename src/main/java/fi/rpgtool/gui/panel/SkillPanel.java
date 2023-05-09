package fi.rpgtool.gui.panel;

import fi.rpgtool.data.Character;
import fi.rpgtool.data.CharacterHandler;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.util.Map;

public class SkillPanel extends JPanel {

    // Ladataan statistiikat hahmosta
    public SkillPanel(Character character) {

        this.setLayout(new GridLayout(5, 2));
        this.setBorder(new TitledBorder("TAIDOT"));

        for (Map.Entry<String, Integer> abilities : character.getAbilities().entrySet()) {

            JSpinner spinner = new JSpinner(new SpinnerNumberModel(abilities.getValue().intValue(), 1, 5, 1));
            ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);

            spinner.addChangeListener(change -> CharacterHandler.getCharacter().setAbility(abilities.getKey(), (int) spinner.getValue()));

            this.add(new JTextField(abilities.getKey()));
            this.add(spinner);
        }

    }

}
