package fi.rpgtool;

import fi.rpgtool.data.Character;
import fi.rpgtool.data.CharacterHandler;
import fi.rpgtool.gui.panel.AttributePanel;
import fi.rpgtool.gui.panel.DiceRollPanel;
import fi.rpgtool.gui.panel.InfoPanel;
import fi.rpgtool.gui.panel.SkillPanel;
import fi.rpgtool.gui.window.InventoryWindow;
import fi.rpgtool.gui.window.MainWindow;
import fi.rpgtool.gui.window.StatisticWindow;

import javax.swing.*;
import java.awt.*;

public class RPGTool {

    public static void main(String[] args) {

        Character character = CharacterHandler.load("NewUniqueNameForRPGTool2.json");

        // Todennäköisesti turha osa, mutta sallii koodin jatkamisen tässä
        MainWindow window = createWindow(character);
    }

    private static MainWindow createWindow(Character character) {

        MainWindow window = new MainWindow();

        StatisticWindow statisticWindow = new StatisticWindow();
        InventoryWindow inventoryWindow = new InventoryWindow();

        InfoPanel infoPanel = new InfoPanel(character);
        AttributePanel attributePanel = new AttributePanel(character);
        SkillPanel skillPanel = new SkillPanel(character);
        DiceRollPanel diceRollPanel = new DiceRollPanel();

        // lisää paneeleja niin saadaan attributet ja skillit asemoitua vierekkäin

        JPanel attributeAndSkillPanel = new JPanel();
        attributeAndSkillPanel.setLayout(new BoxLayout(attributeAndSkillPanel, BoxLayout.X_AXIS));

        attributeAndSkillPanel.add(attributePanel);
        attributeAndSkillPanel.add(skillPanel);

        statisticWindow.add(infoPanel);
        statisticWindow.add(attributeAndSkillPanel);
        statisticWindow.add(diceRollPanel);

        JTabbedPane pane = new JTabbedPane();

        pane.addTab("Statistics", statisticWindow);
        pane.addTab("Inventory", inventoryWindow);

        pane.setTabComponentAt(0, new JLabel("Statistics"));
        pane.setTabComponentAt(1, new JLabel("Inventory"));

        // Datan hakemiseksi
        statisticWindow.setAttributePanel(attributePanel);
        statisticWindow.setInfoPanel(infoPanel);
        statisticWindow.setSkillPanel(skillPanel);

        window.setStatisticWindow(statisticWindow);
        window.setInventoryWindow(inventoryWindow);

        // pane.setVisible(true);
        // window.setContentPane(pane);
        window.getContentPane().add(pane);
        window.pack();

        // näitä ehdottomasti kannattaa siistiä heti kun kerkeää
        SwingUtilities.invokeLater(() -> {

            // Pyydetään kenttien suosikkikoko
            Dimension nameDimension = infoPanel.getNameField().getPreferredSize();
            Dimension hSpinner = infoPanel.getHealthSpinner().getPreferredSize();
            Dimension aSpinner = infoPanel.getArmorSpinner().getPreferredSize();
            // Asetetaan minimikoko samaksi
            window.setMinimumSize(window.getPreferredSize());
            infoPanel.getNameField().setMinimumSize(nameDimension);
            infoPanel.getHealthSpinner().setMinimumSize(hSpinner);
            infoPanel.getArmorSpinner().setMinimumSize(aSpinner);
            // diceRollPanel.difficultySelector.setMinimumSize(diceRollPanel.difficultySelector.getPreferredSize());
            // Asetetaan leveys "äärettömäksi"
            // dimension.width = Integer.MAX_VALUE;

            infoPanel.getNameField().setMaximumSize(new Dimension(50, 60));
            infoPanel.getHealthSpinner().setMaximumSize(new Dimension(50, 20));
            infoPanel.getArmorSpinner().setMaximumSize(new Dimension(50, 20));

            // infoPanel.getHealthSpinner().setMaximumSize(infoPanel.getHealthSpinner().getPreferredSize());
            // infoPanel.getArmorSpinner().setMaximumSize(infoPanel.getArmorSpinner().getPreferredSize());

            // Kerrotaan ikkunalle, että se pitää uudelleenasemoida
            infoPanel.getNameField().invalidate();
            infoPanel.getHealthSpinner().invalidate();
            infoPanel.getArmorSpinner().invalidate();
            window.validate();

            // Asetetaan ikkuna näkyviin
            window.setVisible(true);

        });

        return window;
    }

}
