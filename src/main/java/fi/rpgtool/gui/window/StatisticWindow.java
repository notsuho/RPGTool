package fi.rpgtool.gui.window;

import fi.rpgtool.gui.panel.AttributePanel;
import fi.rpgtool.gui.panel.InfoPanel;
import fi.rpgtool.gui.panel.SkillPanel;

import javax.swing.*;

public class StatisticWindow extends JPanel {

    private AttributePanel attributePanel;
    private InfoPanel infoPanel;
    private SkillPanel skillPanel;

    /**
     * JPanel johon tulee hahmon tiedot, ominaisuudet, taidot ja nopanheitto
     */
    public StatisticWindow() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
