package fi.rpgtool.gui.window;

import fi.rpgtool.data.CharacterHandler;
import fi.rpgtool.data.Pair;
import fi.rpgtool.gui.element.HelpDialog;
import fi.rpgtool.gui.panel.SkillPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame {

    private StatisticWindow statisticWindow;
    private InventoryWindow inventoryWindow;

    /**
     * 
     */
    public MainWindow () {

        this.setTitle("RPGTool");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(600, 700));
        this.setLocationRelativeTo(null);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        // tän voi pistää falseksi jos ei halua käyttäjän muokkaavan ikkunan kokoa
        this.setResizable(true);

        makeMenuBar();
    }

    public void setInventoryWindow(InventoryWindow inventoryWindow) {
        this.inventoryWindow = inventoryWindow;
    }

    public InventoryWindow getInventoryWindow() {
        return this.inventoryWindow;
    }

    public void setStatisticWindow(StatisticWindow statisticWindow) {
        this.statisticWindow = statisticWindow;
    }

    public StatisticWindow getStatisticWindow() {
        return statisticWindow;
    }

    private void makeMenuBar() {

        MenuBar menu = new MenuBar();
        Menu file = new Menu("FILE");
        Menu help = new Menu("HELP");
        menu.add(file);
        menu.add(help);

        MenuItem m1 = new MenuItem("SAVE");

        m1.addActionListener(action -> {
            try {
                save(null);
                JOptionPane.showMessageDialog(this, "Tallentaminen onnistui");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Tallentaminen epäonnistui");
            }
        });

        MenuItem m2 = new MenuItem("SAVE AS");

        m2.addActionListener(action -> {
            JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.home")));

            int result = fileChooser.showSaveDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {

                    File chosenFile = fileChooser.getSelectedFile();

                    if (!chosenFile.exists()) {
                        chosenFile.mkdirs();
                    }

                    save(chosenFile);
                    JOptionPane.showMessageDialog(this, "Tallentaminen onnistui");

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Tallentaminen epäonnistui");
                }
            }
        });

        MenuItem m3 = new MenuItem("IMPORT");

        m3.addActionListener(action -> {
            JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.home")));

            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                CharacterHandler.load(fileChooser.getSelectedFile());
            }
        });

        MenuItem m4 = new MenuItem("KÄYTTÖOHJE");
        m4.addActionListener(action -> {
            try {
                HelpDialog hd = new HelpDialog();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Käyttöohjeen avaamisessa tapahtui virhe.");
                e.printStackTrace();
            }
        });

        file.add(m1);
        file.add(m2);
        file.add(m3);
        help.add(m4);

        this.setMenuBar(menu);

    }

    private void save(File file) throws IOException {

        StatisticWindow stats = getStatisticWindow();

        CharacterHandler.getCharacter().setName(stats.getInfoPanel().getNameField().getText());

        for (Pair<String, JSpinner> attribute : stats.getAttributePanel().getData()) {
            CharacterHandler.getCharacter().setAttribute(attribute.left, (int) attribute.right.getValue());
        }

        for (Pair<JTextField, JSpinner> ability : stats.getSkillPanel().getData()) {
            CharacterHandler.getCharacter().setAbility(ability.left.getText(), (int) ability.right.getValue());
        }

        if (file == null) {
            CharacterHandler.save();
        } else {
            CharacterHandler.save(file);
        }

    }
}
