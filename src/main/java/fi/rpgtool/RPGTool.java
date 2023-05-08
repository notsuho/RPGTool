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
import java.io.File;
import java.io.IOException;

public class RPGTool {

    public static void main(String[] args) throws IOException {

        Character character = CharacterHandler.load("NewUniqueNameForRPGTool2.json");

        character.setName("Erkki Esimerkki");
        character.setStatistic("health", 1);
        character.removeAbility("health");
        character.setAbility("someability", 2);

        character.addInventoryItem("Taikajuoma x2");
        character.addInventoryItem("Taikamiekka x1");

        CharacterHandler.save();

        MainWindow window = new MainWindow();

        MenuBar menu = new MenuBar();
        Menu file = new Menu("File");
        menu.add(file);

        MenuItem m1 = new MenuItem("Save");

        m1.addActionListener(action -> {
            JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.home")));
            int result = fileChooser.showSaveDialog(window);
        });

        MenuItem m2 = new MenuItem("Import");

        m2.addActionListener(action -> {
            JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.home")));
            int result = fileChooser.showOpenDialog(window);
        });

        MenuItem m3 = new MenuItem("Three");
        file.add(m1);
        file.add(m2);
        file.add(m3);

        window.setMenuBar(menu);

        StatisticWindow statisticWindow = new StatisticWindow();
        InventoryWindow inventoryWindow = new InventoryWindow();

        InfoPanel ip = new InfoPanel(character);
        AttributePanel ap = new AttributePanel(character);
        SkillPanel sp = new SkillPanel(character);
        DiceRollPanel dcp = new DiceRollPanel();

        // näköjään pitää tehdä tälleen tosi tyhmästi ylimääräinen paneeli, koska
        // tabbedpanessa oletus layout näyttäisi olevan flowlayout, eikä sitä tunnu
        // saavan järkevästi vaihdettua joksikin toiseksi... Eli tehdään tyhjä jpane
        // halutulla layoutilla, johon laitetaan halutut alipaneelit, ja joka sitten
        // vielä isketään myöhemmin tabbedpaneen. Woo, inception
        //JPanel panel = new JPanel();
        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //panel.add(ip);
        //panel.add(ap);
        //panel.add(sp);
        //panel.add(dcp);

        //statisticWindow.add(panel);

        // vanha toteutus ennen kuin aloin tappelemaan jtabbedpane layouttien kanssa
        statisticWindow.add(ip);
        statisticWindow.add(ap);
        statisticWindow.add(sp);
        statisticWindow.add(dcp);

        JTabbedPane pane = new JTabbedPane();
        //pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        JLabel statisticLabel = new JLabel("Statistics");
        JLabel inventoryLabel = new JLabel("Inventory");
        // statisticLabel.setPreferredSize(new Dimension(150, 30));
        // inventoryLabel.setPreferredSize(new Dimension(150, 30));

        pane.addTab("Statistics", statisticWindow);
        pane.addTab("Inventory", inventoryWindow);

        pane.setTabComponentAt(0, statisticLabel);
        pane.setTabComponentAt(1, inventoryLabel);

        // pane.setVisible(true);
        // window.setContentPane(pane);
        window.getContentPane().add(pane);
        window.pack();
        window.setVisible(true);
    }

}
