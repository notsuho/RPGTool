package fi.rpgtool.gui.window;

import fi.rpgtool.data.Character;
import fi.rpgtool.gui.panel.AttributePanel;
import fi.rpgtool.gui.panel.DiceRollPanel;
import fi.rpgtool.gui.panel.InfoPanel;
import fi.rpgtool.gui.panel.SkillPanel;

import javax.swing.*;
import java.awt.*;

public class StatisticWindow extends JPanel {

    private AttributePanel attributePanel;
    private InfoPanel infoPanel;
    private SkillPanel skillPanel;

    /**
     * JPanel johon tulee hahmon tiedot, ominaisuudet, taidot ja nopanheitto
     */
    public StatisticWindow(Character character) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        InfoPanel infoPanel = new InfoPanel(character);
        AttributePanel attributePanel = new AttributePanel(character);
        SkillPanel skillPanel = new SkillPanel(character);
        DiceRollPanel diceRollPanel = new DiceRollPanel();

        JPanel attributeAndSkillPanel = new JPanel();
        attributeAndSkillPanel.setLayout(new BoxLayout(attributeAndSkillPanel, BoxLayout.X_AXIS));

        attributeAndSkillPanel.add(attributePanel);
        attributeAndSkillPanel.add(skillPanel);

        this.add(infoPanel);
        this.add(attributeAndSkillPanel);
        this.add(diceRollPanel);

        this.setAttributePanel(attributePanel);
        this.setInfoPanel(infoPanel);
        this.setSkillPanel(skillPanel);

        SwingUtilities.invokeLater(() -> {

            // Pyydetään kenttien suosikkikoko
            Dimension nameDimension = infoPanel.getNameField().getPreferredSize();
            Dimension hSpinner = infoPanel.getHealthSpinner().getPreferredSize();
            Dimension aSpinner = infoPanel.getArmorSpinner().getPreferredSize();

            // Asetetaan minimikoko samaksi
            infoPanel.getNameField().setMinimumSize(nameDimension);
            infoPanel.getHealthSpinner().setMinimumSize(hSpinner);
            infoPanel.getArmorSpinner().setMinimumSize(aSpinner);

            infoPanel.getNameField().setMaximumSize(new Dimension(50, 60));
            infoPanel.getHealthSpinner().setMaximumSize(new Dimension(50, 20));
            infoPanel.getArmorSpinner().setMaximumSize(new Dimension(50, 20));

            // Kerrotaan ikkunalle, että se pitää uudelleenasemoida
            infoPanel.getNameField().invalidate();
            infoPanel.getHealthSpinner().invalidate();
            infoPanel.getArmorSpinner().invalidate();
        });
    }

    public void setAttributePanel(AttributePanel attributePanel) {
        this.attributePanel = attributePanel;
    }

    public AttributePanel getAttributePanel() {
        return attributePanel;
    }

    public void setInfoPanel(InfoPanel infoPanel) {
        this.infoPanel = infoPanel;
    }

    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    public void setSkillPanel(SkillPanel skillPanel) {
        this.skillPanel = skillPanel;
    }

    public SkillPanel getSkillPanel() {
        return skillPanel;
    }

}
