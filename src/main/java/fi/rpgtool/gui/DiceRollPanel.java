package fi.rpgtool.gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class DiceRollPanel extends JPanel {

    private final JComboBox<Integer> difficultySelector;
    private final JComboBox<Integer> skillSelector;
    private final JComboBox<String> attributeSelector;
    private final JComboBox<Integer> dieSelector;

    // Ladataan tämän tiedot jostain configista jos sen tekemiseen jää aikaa
    public DiceRollPanel() {

        this.setLayout(new GridLayout(5, 2));
        this.setBorder(new TitledBorder("NOPANHEITTO"));

        this.difficultySelector = new JComboBox<>();
        this.skillSelector = new JComboBox<>();
        this.attributeSelector = new JComboBox<>();
        this.dieSelector = new JComboBox<>();

        this.add(new JLabel("Valitse kohdeluku:"));
        this.add(this.difficultySelector);
        this.add(new JLabel("Valitse taito:"));
        this.add(this.skillSelector);
        this.add(new JLabel("Valitse ominaisuus:"));
        this.add(this.attributeSelector);
        this.add(new JLabel("Nopan koko:"));
        this.add(this.dieSelector);

        RollButton rollButton = new RollButton();
        rollButton.setPreferredSize(new Dimension(50, 50));

        rollButton.setDieSelector(this.dieSelector);
        rollButton.setSkillSelector(this.skillSelector);
        rollButton.setDifficultySelector(this.difficultySelector);

        this.add(rollButton);

        initValues();
    }

    private final int[] difficulty = { 5, 10, 15, 20, 25, 30 };
    private final int[] skillBonus = { 1, 2, 3, 4, 5 };
    private final int[] dieSize = { 4, 6, 8, 10, 12, 20, 100 };
    private final String[] attribute = { "VOIMA", "NOPEUS", "ÄLY", "OVELUUS" };

    private void initValues() {

        for (int difficulty : difficulty) {
            difficultySelector.addItem(difficulty);
        }

        for (int bonus : skillBonus) {
            skillSelector.addItem(bonus);
        }

        for (String s : attribute) {
            attributeSelector.addItem(s);
        }

        for (int die : dieSize) {
            dieSelector.addItem(die);
        }

    }

}
