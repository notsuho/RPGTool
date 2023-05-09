package fi.rpgtool.gui.panel;

import fi.rpgtool.gui.RollButton;

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

        this.setLayout(new GridLayout(1,3));
        this.setBorder(new TitledBorder("NOPANHEITTO"));

        this.difficultySelector = new JComboBox<>();
        this.difficultySelector.setBorder(new TitledBorder("Valitse kohdeluku:"));
        // this.difficultySelector.setPreferredSize(new Dimension(100, 30));

        this.skillSelector = new JComboBox<>();
        this.skillSelector.setBorder(new TitledBorder("Valitse taito:"));
        this.skillSelector.setPreferredSize(new Dimension(100, 30));

        this.attributeSelector = new JComboBox<>();
        this.attributeSelector.setBorder(new TitledBorder("Valitse ominaisuus:"));
        // this.attributeSelector.setPreferredSize(new Dimension(100, 30));

        this.dieSelector = new JComboBox<>();
        this.dieSelector.setBorder(new TitledBorder("Valitse nopan koko:"));
        // this.dieSelector.setPreferredSize(new Dimension(100, 30));

        RollButton rollButton = new RollButton();
        rollButton.setPreferredSize(new Dimension(50, 50));

        JPanel drpLeft = new JPanel();
        // drpLeft.setLayout(new GridLayout(2, 1));
        drpLeft.setLayout(new BoxLayout(drpLeft, BoxLayout.Y_AXIS));
        drpLeft.add(this.skillSelector);
        drpLeft.add(Box.createVerticalGlue());
        drpLeft.add(this.attributeSelector);

        JPanel drpRight = new JPanel();
        // drpRight.setLayout(new GridLayout(2, 1));
        drpRight.setLayout(new BoxLayout(drpRight, BoxLayout.Y_AXIS));
        drpRight.add(this.difficultySelector);
        drpRight.add(Box.createVerticalGlue());
        drpRight.add(this.dieSelector);

        // this.add(this.skillSelector);
        // this.add(this.attributeSelector);
        this.add(drpLeft);
        this.add(rollButton);
        this.add(drpRight);
        // this.add(this.difficultySelector);
        // this.add(this.dieSelector);

        rollButton.setDieSelector(this.dieSelector);
        rollButton.setSkillSelector(this.skillSelector);
        rollButton.setDifficultySelector(this.difficultySelector);

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
