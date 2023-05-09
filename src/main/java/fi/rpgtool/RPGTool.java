package fi.rpgtool;

import fi.rpgtool.data.Character;
import fi.rpgtool.data.CharacterHandler;
import fi.rpgtool.gui.panel.AttributePanel;
import fi.rpgtool.gui.panel.DiceRollPanel;
import fi.rpgtool.gui.panel.InfoPanel;
import fi.rpgtool.gui.panel.SkillPanel;
import fi.rpgtool.gui.window.HelpDialog;
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
        character.setAbility("Kahvinjuominen", 1);
        character.setAbility("Koodaus", 2);
        character.setAbility("Kova äijä", 3);

        character.addInventoryItem("Taikajuoma x2");
        character.addInventoryItem("Taikamiekka x1");

        CharacterHandler.save();

        MainWindow window = new MainWindow();

        MenuBar menu = new MenuBar();
        Menu file = new Menu("FILE");
        Menu help = new Menu("HELP");
        menu.add(file);
        menu.add(help);

        MenuItem m1 = new MenuItem("SAVE");

        m1.addActionListener(action -> {
            JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.home")));
            int result = fileChooser.showSaveDialog(window);
        });

        MenuItem m2 = new MenuItem("IMPORT");

        m2.addActionListener(action -> {
            JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.home")));
            int result = fileChooser.showOpenDialog(window);
        });

        MenuItem m3 = new MenuItem("KÄYTTÖOHJE");
        m3.addActionListener(action -> {
            try {
                HelpDialog hd = new HelpDialog();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        file.add(m1);
        file.add(m2);
        help.add(m3);

        window.setMenuBar(menu);

        StatisticWindow statisticWindow = new StatisticWindow();
        InventoryWindow inventoryWindow = new InventoryWindow();

        InfoPanel ip = new InfoPanel(character);
        AttributePanel ap = new AttributePanel(character);
        SkillPanel sp = new SkillPanel(character);
        DiceRollPanel drp = new DiceRollPanel();

        // näköjään pitää tehdä tälleen tosi tyhmästi ylimääräinen paneeli, koska
        // tabbedpanessa oletus layout näyttäisi olevan flowlayout, eikä sitä tunnu
        // saavan järkevästi vaihdettua joksikin toiseksi... Eli tehdään tyhjä jpane
        // halutulla layoutilla, johon laitetaan halutut alipaneelit, ja joka sitten
        // vielä isketään myöhemmin tabbedpaneen. Woo, inception
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(ip);

        // lisää paneeleja niin saadaan attributet ja skillit asemoitua vierekkäin
        JPanel apAndSp = new JPanel();
        apAndSp.setLayout(new BoxLayout(apAndSp, BoxLayout.X_AXIS));

        apAndSp.add(ap);
        apAndSp.add(sp);

        panel.add(apAndSp);
        // panel.add(ap);
        // panel.add(sp);
        panel.add(drp);

        statisticWindow.add(panel);

        // vanha toteutus ennen kuin aloin tappelemaan jtabbedpane layouttien kanssa
        // statisticWindow.add(ip);
        // statisticWindow.add(ap);
        // statisticWindow.add(sp);
        // statisticWindow.add(dcp);

        JTabbedPane pane = new JTabbedPane();
        // pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

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

        // näitä ehdottomasti kannattaa siistiä heti kun kerkeää
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        // Pyydetään tekstikentän suosikkikoko
                        // Dimension dimension = ip.getNameField().getPreferredSize();
                        // Asetetaan minimikoko samaksi
                        window.setMinimumSize(window.getPreferredSize());
                        ip.getNameField().setMinimumSize(ip.getNameField().getPreferredSize());
                        ip.getHealthSpinner().setMinimumSize(ip.getHealthSpinner().getPreferredSize());
                        ip.getArmorSpinner().setMinimumSize(ip.getArmorSpinner().getPreferredSize());
                        // drp.difficultySelector.setMinimumSize(drp.difficultySelector.getPreferredSize());
                        // Asetetaan leveys "äärettömäksi"
                        // dimension.width = Integer.MAX_VALUE;

                        ip.getNameField().setMaximumSize(new Dimension(50, 60));
                        ip.getHealthSpinner().setMaximumSize(new Dimension(50, 20));
                        ip.getArmorSpinner().setMaximumSize(new Dimension(50, 20));

                        // ip.getHealthSpinner().setMaximumSize(ip.getHealthSpinner().getPreferredSize());
                        // ip.getArmorSpinner().setMaximumSize(ip.getArmorSpinner().getPreferredSize());

                        // Kerrotaan ikkunalle, että se pitää uudelleenasemoida
                        ip.getNameField().invalidate();
                        ip.getHealthSpinner().invalidate();
                        ip.getArmorSpinner().invalidate();
                        window.validate();

                        // Asetetaan ikkuna näkyviin
                        window.setVisible(true);
                    }
                });

        // window.setVisible(true);
    }

}
