package fi.rpgtool.gui.panel;

import fi.rpgtool.gui.element.RollButton;
import fi.rpgtool.gui.window.MainWindow;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class DiceRollPanel extends JPanel {

    private final JComboBox<Integer> difficultySelector;
    private final JComboBox<String> skillSelector;
    private final JComboBox<String> attributeSelector;
    private final JComboBox<Integer> dieSelector;

    // Ladataan tämän tiedot jostain configista jos sen tekemiseen jää aikaa
    public DiceRollPanel(MainWindow mainWindow) {

        this.setLayout(new GridLayout(1,3));
        this.setBorder(new TitledBorder("NOPANHEITTO"));

        this.difficultySelector = new JComboBox<>();
        this.difficultySelector.setBorder(new TitledBorder("Valitse kohdeluku:"));

        this.skillSelector = new JComboBox<>();
        this.skillSelector.setBorder(new TitledBorder("Valitse taito:"));

        this.attributeSelector = new JComboBox<>();
        this.attributeSelector.setBorder(new TitledBorder("Valitse ominaisuus:"));

        this.dieSelector = new JComboBox<>();
        this.dieSelector.setBorder(new TitledBorder("Valitse nopan koko:"));

        RollButton rollButton = new RollButton(mainWindow);
        rollButton.setPreferredSize(new Dimension(50, 50));
        rollButton.setMinimumSize(rollButton.getPreferredSize());

        JPanel drpLeft = new JPanel();
        drpLeft.setLayout(new BoxLayout(drpLeft, BoxLayout.Y_AXIS));
        drpLeft.add(this.skillSelector);
        drpLeft.add(Box.createVerticalGlue());
        drpLeft.add(this.attributeSelector);

        JPanel drpRight = new JPanel();
        drpRight.setLayout(new BoxLayout(drpRight, BoxLayout.Y_AXIS));
        drpRight.add(this.difficultySelector);
        drpRight.add(Box.createVerticalGlue());
        drpRight.add(this.dieSelector);

        this.add(drpLeft);
        this.add(rollButton);
        this.add(drpRight);

        rollButton.setDieSelector(this.dieSelector);
        rollButton.setAttributeSelector(this.attributeSelector);
        rollButton.setSkillSelector(this.skillSelector);
        rollButton.setDifficultySelector(this.difficultySelector);

        initValues();
    }

    public JComboBox<String> getSkillSelector() {
        return skillSelector;
    }

    private final int[] difficulty = { 5, 10, 15, 20, 25, 30 };
    private final int[] dieSize = { 4, 6, 8, 10, 12, 20, 100 };
    private final String[] attribute = { "VOIMA", "NOPEUS", "ÄLY", "OVELUUS" };

    private void initValues() {

        for (int difficulty : difficulty) {
            difficultySelector.addItem(difficulty);
        }

        for (String s : attribute) {
            attributeSelector.addItem(s);
        }

        for (int die : dieSize) {
            dieSelector.addItem(die);
        }

    }

}
