package fi.rpgtool.gui.window;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import fi.rpgtool.data.Character;
import fi.rpgtool.data.Pair;
import fi.rpgtool.gui.element.HelpDialog;

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

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
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
                this.file = fileChooser.getSelectedFile();
                load();
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

    public void save(File file) throws IOException {

        StatisticWindow stats = getStatisticWindow();

        character.setName(stats.getInfoPanel().getNameField().getText());

        character.getAttributes().clear();
        for (Pair<String, JSpinner> attribute : stats.getAttributePanel().getData()) {
            character.setAttribute(attribute.left, (int) attribute.right.getValue());
        }

        character.getAbilities().clear();
        for (Pair<JTextField, JSpinner> ability : stats.getSkillPanel().getData()) {
            character.setAbility(ability.left.getText(), (int) ability.right.getValue());
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
