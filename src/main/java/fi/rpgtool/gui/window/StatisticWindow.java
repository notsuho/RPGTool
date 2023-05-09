package fi.rpgtool.gui.window;

import fi.rpgtool.data.Character;
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
    public StatisticWindow(Character character) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.infoPanel = new InfoPanel(character);
        this.attributePanel = new AttributePanel(character);
        this.skillPanel = new SkillPanel(character);
        this.diceRollPanel = new DiceRollPanel();

        JPanel attributeAndSkillPanel = new JPanel();
        attributeAndSkillPanel.setLayout(new BoxLayout(attributeAndSkillPanel, BoxLayout.X_AXIS));

        attributeAndSkillPanel.add(attributePanel);
        attributeAndSkillPanel.add(skillPanel);

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
