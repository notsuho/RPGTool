package fi.rpgtool.gui.window;

import fi.rpgtool.data.Pair;
import fi.rpgtool.gui.panel.AttributePanel;
import fi.rpgtool.gui.panel.DiceRollPanel;
import fi.rpgtool.gui.panel.InfoPanel;
import fi.rpgtool.gui.panel.SkillPanel;

import javax.swing.*;
import java.awt.*;

public class StatisticWindow extends JPanel {

    private final AttributePanel attributePanel;
    private final InfoPanel infoPanel;
    private final SkillPanel skillPanel;
    private final DiceRollPanel diceRollPanel;

    /**
     * JPanel johon tulee hahmon tiedot, ominaisuudet, taidot ja nopanheitto
     */
    public StatisticWindow(MainWindow mainWindow) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.infoPanel = new InfoPanel(mainWindow);
        this.attributePanel = new AttributePanel(mainWindow);
        this.skillPanel = new SkillPanel(mainWindow);
        this.diceRollPanel = new DiceRollPanel(mainWindow);

        JPanel attributeAndSkillPanel = new JPanel();
        attributeAndSkillPanel.setLayout(new BoxLayout(attributeAndSkillPanel, BoxLayout.X_AXIS));

        attributeAndSkillPanel.add(attributePanel);
        attributeAndSkillPanel.add(skillPanel);

        setSkillDropdownValues();

        this.add(infoPanel);
        this.add(attributeAndSkillPanel);
        this.add(diceRollPanel);

        SwingUtilities.invokeLater(() -> {

            // Pyydetään kenttien suosikkikoko
            Dimension nameDimension = infoPanel.getNameField().getPreferredSize();
            Dimension hSpinner = infoPanel.getHealthSpinner().getPreferredSize();
            Dimension aSpinner = infoPanel.getArmorSpinner().getPreferredSize();

            // Asetetaan minimikoko samaksi
            infoPanel.getNameField().setMinimumSize(nameDimension);
            infoPanel.getHealthSpinner().setMinimumSize(hSpinner);
            infoPanel.getArmorSpinner().setMinimumSize(aSpinner);

            infoPanel.getNameField().setMaximumSize(new Dimension(500, 60));
            infoPanel.getHealthSpinner().setMaximumSize(new Dimension(50, 20));
            infoPanel.getArmorSpinner().setMaximumSize(new Dimension(50, 20));

            // Kerrotaan ikkunalle, että se pitää uudelleenasemoida
            infoPanel.getNameField().invalidate();
            infoPanel.getHealthSpinner().invalidate();
            infoPanel.getArmorSpinner().invalidate();
        });
    }

    public void setSkillDropdownValues() {

        JComboBox<String> skills = diceRollPanel.getSkillSelector();

        Object selectedItem = skills.getSelectedItem();

        skills.removeAllItems();

        for (Pair<JTextField, JSpinner> skill : skillPanel.getData()) {

            String text = skill.left.getText();

            if (!text.isBlank()) {
                skills.addItem(text);
            }

            if (text.equals(selectedItem)) {
                skills.setSelectedItem(text);
            }
        }

    }

    public AttributePanel getAttributePanel() {
        return attributePanel;
    }

    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    public SkillPanel getSkillPanel() {
        return skillPanel;
    }

    public DiceRollPanel getDiceRollPanel() {
        return diceRollPanel;
    }

}
