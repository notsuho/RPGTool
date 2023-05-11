package fi.rpgtool.gui.window;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import fi.rpgtool.RPGTool;
import fi.rpgtool.data.Character;
import fi.rpgtool.data.Pair;
import fi.rpgtool.gui.element.HelpDialog;
import fi.rpgtool.gui.element.InventoryPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Ohjelman pääikkuna, joka pitää sisällään kaiken muun.
 */
public class MainWindow extends JFrame {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private Character character;
    private boolean hasChanged = false;
    private final File file;

    private StatisticWindow statisticWindow;
    private InventoryWindow inventoryWindow;


    public MainWindow(File file) {

        this.file = file;
        load();

        this.setTitle("RPGTool");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new CloseAdapter(this));
        this.setPreferredSize(new Dimension(600, 800));
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

    public void setUnsaved(boolean saved) {
        this.hasChanged = saved;
    }

    public boolean needsSaving() {
        return hasChanged;
    }

    private void makeMenuBar() {

        MenuBar menu = new MenuBar();

        Menu file = new Menu("Tiedosto");
        Menu help = new Menu("Ohje");

        menu.add(file);
        menu.add(help);

        file.add(makeSaveButton("Tallenna"));
        file.add(makeSaveAsButton("Tallenna nimellä"));
        file.add(makeImportButton("Tuo tiedostosta"));

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

        item.addActionListener(action -> openSaveWindow());

        return item;
    }

    private void openSaveWindow() {

        JFileChooser fileChooser = new JFileChooser();

        File saveFile = new File(System.getProperty("user.home"), character.getName().replaceAll("\\s+", "_") + ".json");

        fileChooser.setSelectedFile(saveFile);

        int result = fileChooser.showSaveDialog(null);

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

    }

    private MenuItem makeImportButton(String label) {

        MenuItem item = new MenuItem(label);

        item.addActionListener(action -> {
            JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.home")));

            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                RPGTool.createWindow(fileChooser.getSelectedFile());
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
        for (InventoryPanel.ItemCell panel : inventoryWindow.getItems().getCells()) {

            String text = panel.getTextField().getText();

            if (!text.isBlank()) {
                character.addInventoryItem(text);
            }

        }

        if (file == null) {
            file = this.file;
        }

        if (file.isDirectory()) {
            file = new File(file, character.getName().replaceAll("\\s+", "_") + ".json");
        }

        Files.writeString(Path.of(file.getPath()), GSON.toJson(character, Character.class), StandardCharsets.UTF_8);

        setUnsaved(false);
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

    private static class CloseAdapter extends WindowAdapter {

        private static final String UNSAVED_CHANGES = "Sinulla on tallentamattomia muutoksia, haluatko tallentaa ne?";
        private static final Object[] YES_NO_CANCEL_OPTIONS = {"Kyllä", "Ei", "Mene takaisin"};

        private final MainWindow window;

        public CloseAdapter(MainWindow window) {
            this.window = window;
        }

        @Override
        public void windowClosing(WindowEvent e) {

            if (!window.needsSaving()) {
                window.dispose();
                return;
            }

            int option = JOptionPane.showOptionDialog(null, UNSAVED_CHANGES, NOTIFICATION_TITLE,
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, YES_NO_CANCEL_OPTIONS, -1);

            switch (option) {
                case JOptionPane.YES_OPTION -> window.openSaveWindow();
                case JOptionPane.NO_OPTION -> window.dispose();
            }

        }
    }
}
