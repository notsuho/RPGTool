package fi.rpgtool.gui.panel;

import fi.rpgtool.data.Pair;
import fi.rpgtool.gui.window.MainWindow;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SkillPanel extends JPanel {

    private final List<Pair<JTextField, JSpinner>> data = new ArrayList<>();
    private final MainWindow mainWindow;

    // Ladataan statistiikat hahmosta
    public SkillPanel(MainWindow mainWindow) {

        this.mainWindow = mainWindow;
        this.setLayout(new GridLayout(5, 2));
        this.setBorder(new TitledBorder("TAIDOT"));

        int i = 0;

        for (Map.Entry<String, Integer> abilities : mainWindow.getCharacter().getAbilities().entrySet()) {

            // Rajoita taidot viiteen taitoon
            if (i > 5) {
                break;
            }

            i++;

            data.add(makeSkill(abilities.getKey(), abilities.getValue()));
        }

        // Täytä kentät jos edellinen kohta ei täyttänyt niitä
        for (; i < 5; i++) {
            data.add(makeSkill(null, 1));
        }
    }

    public List<Pair<JTextField, JSpinner>> getData() {
        return data;
    }

    private Pair<JTextField, JSpinner> makeSkill(String skillKey, int skillValue) {

        JTextField textField = new JTextField(skillKey);
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(skillValue, 1, 5, 1));
        ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);

        textField.addKeyListener(new KeyPressListener(this.mainWindow));
        spinner.addChangeListener(change -> mainWindow.getStatisticWindow().setSkillDropdownValues());

        this.add(textField);
        this.add(spinner);

        return new Pair<>(textField, spinner);
    }

    private record KeyPressListener(MainWindow mainWindow) implements KeyListener {

        @Override
            public void keyTyped(KeyEvent e) {
                mainWindow.getStatisticWindow().setSkillDropdownValues();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                mainWindow.getStatisticWindow().setSkillDropdownValues();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                mainWindow.getStatisticWindow().setSkillDropdownValues();
            }
        }

}
