package fi.rpgtool.gui.window;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import fi.rpgtool.data.Character;
import fi.rpgtool.data.Pair;
import fi.rpgtool.gui.element.HelpDialog;
import fi.rpgtool.gui.element.ScrollablePanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class MainWindow extends JFrame {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private Character character;
    private File file;

    private StatisticWindow statisticWindow;
    private InventoryWindow inventoryWindow;

    /**
     * 
     */
    public MainWindow(String filePath) {

        this.file = new File(filePath);
        load();

        this.setTitle("RPGTool");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(600, 800));
        this.setLocationRelativeTo(null);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        // tän voi pistää falseksi jos ei halua käyttäjän muokkaavan ikkunan kokoa
        this.setResizable(true);

        makeMenuBar();
    }

    public void setInventoryWindow(InventoryWindow inventoryWindow) {
        this.inventoryWindow = inventoryWindow;
    }

    public Character getCharacter() {
        return character;
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

        file.add(makeSaveButton("SAVE"));
        file.add(makeSaveAsButton("SAVE AS"));
        file.add(makeImportButton("IMPORT"));

        help.add(makeHelpMenuItem("KÄYTTÖOHJE"));

        this.setMenuBar(menu);
    }

    private static final String SAVING_SUCCESS = "Tallentaminen onnistui.";
    private static final String SAVING_FAILED = "Tallentaminen epäonnistui.";
    private static final String HELP_ERROR = "Käyttöohjeen näyttämisessä tapahtui virhe.";
    private static final String NOTIFICATION_TITLE = "Ilmoitus";
    private static final String ERROR_TITLE = "Virhe";

    private MenuItem makeSaveButton(String label) {

        MenuItem item = new MenuItem(label);

        item.addActionListener(action -> {
            try {
                save(null);
                JOptionPane.showMessageDialog(this, SAVING_SUCCESS, NOTIFICATION_TITLE, JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, SAVING_FAILED, ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
            }
        });

        return item;
    }

    private MenuItem makeSaveAsButton(String label) {

        MenuItem item = new MenuItem(label);

        item.addActionListener(action -> {
            JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.home")));

            int result = fileChooser.showSaveDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {

                    File chosenFile = fileChooser.getSelectedFile();

                    if (!chosenFile.exists()) {
                        chosenFile.mkdirs();
                    }

                    save(chosenFile);
                    JOptionPane.showMessageDialog(this, SAVING_SUCCESS, NOTIFICATION_TITLE, JOptionPane.INFORMATION_MESSAGE);

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, SAVING_FAILED, ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return item;
    }

    private MenuItem makeImportButton(String label) {

        MenuItem item = new MenuItem(label);

        item.addActionListener(action -> {
            JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.home")));

            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                this.file = fileChooser.getSelectedFile();
                load();
            }
        });

        return item;
    }

    private MenuItem makeHelpMenuItem(String label) {

        MenuItem item = new MenuItem(label);

        item.addActionListener(action -> {
            try {
                new HelpDialog();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, HELP_ERROR, ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
            }
        });

        return item;
    }

    public void save(File file) throws IOException {

        StatisticWindow stats = getStatisticWindow();

        character.setName(stats.getInfoPanel().getNameField().getText());

        character.getAttributes().clear();
        for (Pair<String, JSpinner> attribute : stats.getAttributePanel().getData()) {
            character.setAttribute(attribute.left, (int) attribute.right.getValue());
        }

        character.getSkills().clear();
        for (Pair<JTextField, JSpinner> ability : stats.getSkillPanel().getData()) {
            character.setSkill(ability.left.getText(), (int) ability.right.getValue());
        }

        InventoryWindow inventoryWindow = getInventoryWindow();

        character.setNotes(inventoryWindow.getNotes().getText());

        character.getInventory().clear();
        for (ScrollablePanel.CellPanel panel : inventoryWindow.getItems().getCells()) {

            String text = panel.getTextField().getText();

            if (!text.isBlank()) {
                character.addInventoryItem(text);
            }

        }

        if (file == null) {
            file = this.file;
        }

        if (file.isDirectory()) {
            file = new File(file, character.getName().strip() + ".json");
        }

        Files.writeString(Path.of(file.getPath()), GSON.toJson(character, Character.class), StandardCharsets.UTF_8);
    }

    public void load() {

        try {
            JsonReader reader = new JsonReader(new FileReader(this.file, StandardCharsets.UTF_8));

            this.character = GSON.fromJson(reader, Character.class);

            reader.close();
        } catch (IOException ex) {
            this.character = new Character();
        }
    }
}
