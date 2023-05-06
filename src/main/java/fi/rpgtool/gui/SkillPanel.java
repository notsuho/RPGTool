package fi.rpgtool.gui;

import fi.rpgtool.data.Character;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.Map;

public class SkillPanel extends JPanel {

    // Ladataan statistiikat hahmosta
    public SkillPanel(Character character) {

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(new TitledBorder("TAIDOT"));

        for (Map.Entry<String, Integer> abilities : character.getAbilities().entrySet()) {

            JSpinner spinner = new JSpinner(new SpinnerNumberModel(abilities.getValue().intValue(), 1, 5, 1));
            ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);

            this.add(new JTextField(abilities.getKey()));
            this.add(spinner);

        }

    }

}
