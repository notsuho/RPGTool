package fi.rpgtool.gui.panel;

import fi.rpgtool.gui.element.RollButton;
import fi.rpgtool.gui.window.MainWindow;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Nopanheittoa käsittelevä paneeli.
 * Tämä sisältää nopanheittoon liittyviä elementtejä, kuten "vaikeuden", taidon, ominaisuuden ja nopan koon valitsimet.
 */
public class DiceRollPanel extends JPanel {

    private final JComboBox<Integer> difficultySelector;
    private final JComboBox<String> skillSelector;
    private final JComboBox<String> attributeSelector;
    private final JComboBox<Integer> dieSelector;

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
        rollButton.setPreferredSize(new Dimension(150, 150));
        rollButton.setMinimumSize(rollButton.getPreferredSize());

        JPanel leftDiceRollPanel = new JPanel();
        leftDiceRollPanel.setLayout(new BoxLayout(leftDiceRollPanel, BoxLayout.Y_AXIS));
        leftDiceRollPanel.add(this.skillSelector);
        leftDiceRollPanel.add(Box.createVerticalGlue());
        leftDiceRollPanel.add(this.attributeSelector);

        JPanel rightDiceRollPanel = new JPanel();
        rightDiceRollPanel.setLayout(new BoxLayout(rightDiceRollPanel, BoxLayout.Y_AXIS));
        rightDiceRollPanel.add(this.difficultySelector);
        rightDiceRollPanel.add(Box.createVerticalGlue());
        rightDiceRollPanel.add(this.dieSelector);

        JPanel rollButtonPanel = new JPanel();
        rollButtonPanel.setLayout(new GridBagLayout());

        this.add(leftDiceRollPanel);
        rollButtonPanel.add(rollButton);
        this.add(rollButtonPanel);
        this.add(rightDiceRollPanel);

        // Nämä asetetaan tiedon käsittelyn helpottamiseksi.
        rollButton.setDieSelector(this.dieSelector);
        rollButton.setAttributeSelector(this.attributeSelector);
        rollButton.setSkillSelector(this.skillSelector);
        rollButton.setDifficultySelector(this.difficultySelector);

        // Asetetaan oletusarvot
        initValues();
    }

    public JComboBox<String> getSkillSelector() {
        return skillSelector;
    }

    /**
     * Näillä on kovakoodatut arvot, koska ohjelmalle ei ainakaan vielä tehty asetustiedostoa.
     * Toinen ratkaistava ongelma on se, miten ikkuna kasvaisi, kun attribuutteja (tai ominaisuuksia) tulee huomattavasti enemmän.
     * Tämän toteutukseen ei enää tässä vaiheessa ole aikaa.
     */

    // "Vaikeudet" tai mahdolliset kohdeluvut.
    private final int[] DIFFICULTY_VALUES = { 5, 10, 15, 20, 25, 30 };

    // Nopan koko, nopppaa heitettäessä generoidaan luku, joka on välillä [1, silmäluku] + valittu taito + valittu ominaisuus.
    private final int[] DIE_SIZE = { 4, 6, 8, 10, 12, 20, 100 };

    // Attribuutit, eli ominaisuudet
    private final String[] ATTRIBUTE_NAMES = { "VOIMA", "NOPEUS", "ÄLY", "OVELUUS" };

    private void initValues() {

        for (int difficulty : DIFFICULTY_VALUES) {
            difficultySelector.addItem(difficulty);
        }

        for (String attribute : ATTRIBUTE_NAMES) {
            attributeSelector.addItem(attribute);
        }

        for (int dieSize : DIE_SIZE) {
            dieSelector.addItem(dieSize);
        }

    }

}
