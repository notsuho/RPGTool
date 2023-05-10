package fi.rpgtool.gui.panel;

import fi.rpgtool.gui.window.MainWindow;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Infopaneeli joka sisältää valitun hahmon perustiedot kuten nimen, terveyspisteet ja panssaripisteet.
 */
public class InfoPanel extends JPanel {

    private final JTextField nameField;
    private final JSpinner healthSpinner;
    private final JSpinner armorSpinner;

    public InfoPanel(MainWindow mainWindow) {

        this.setLayout(new GridBagLayout());
        this.setBorder(new TitledBorder("HAHMON TIEDOT"));

        this.nameField = new JTextField(mainWindow.getCharacter().getName());
        this.nameField.setBorder(new TitledBorder("Hahmon nimi"));
        this.nameField.setPreferredSize(new Dimension(200, 40));

        // laitetaan spinnerin tekstikentän editointi pois päältä,
        // niin käyttäjä voi muokata sitä vain nuolinappien avulla
        // sama muille spinnereille myös
        this.healthSpinner = new JSpinner(new SpinnerNumberModel(mainWindow.getCharacter().getHealth(), 0, 100, 1));
        this.healthSpinner.setBorder(new TitledBorder("Terveyspisteet"));
        this.healthSpinner.setPreferredSize(new Dimension(150, 50));
        ((JSpinner.DefaultEditor) healthSpinner.getEditor()).getTextField().setEditable(false);
        this.healthSpinner.addChangeListener(change -> mainWindow.getCharacter().setHealth((int) this.healthSpinner.getValue()));

        this.armorSpinner = new JSpinner(new SpinnerNumberModel(mainWindow.getCharacter().getArmor(), 10, 50, 1));
        this.armorSpinner.setBorder(new TitledBorder("Panssaripisteet"));
        this.armorSpinner.setPreferredSize(new Dimension(150, 50));
        ((JSpinner.DefaultEditor) armorSpinner.getEditor()).getTextField().setEditable(false);
        this.armorSpinner.addChangeListener(change -> mainWindow.getCharacter().setArmor((int) this.armorSpinner.getValue()));

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.add(new JLabel(new ImageIcon("src/main/resources/kuva-isompi.jpg")));
        namePanel.add(this.nameField);

        JPanel healthPanel = new JPanel();

        healthPanel.setLayout(new GridBagLayout());
        healthPanel.setPreferredSize(new Dimension(200, 100));

        JLabel heartLabel = new JLabel(new ImageIcon("src/main/resources/hp-heart.jpg"));
        JLabel armorLabel = new JLabel(new ImageIcon("src/main/resources/armor-shield.jpg"));

        heartLabel.setPreferredSize(new Dimension(30, 30));
        armorLabel.setPreferredSize(new Dimension(30, 30));
        heartLabel.setMaximumSize(heartLabel.getPreferredSize());
        armorLabel.setMaximumSize(armorLabel.getPreferredSize());

        healthPanel.add(heartLabel, makeConstraints(0, 0));
        healthPanel.add(this.healthSpinner, makeConstraints(1, 0));
        healthPanel.add(armorLabel, makeConstraints(0, 1));
        healthPanel.add(this.armorSpinner, makeConstraints(1, 1));

        this.add(namePanel, makeConstraints(0, 0));

        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(80, 30));
        label.setMinimumSize(label.getPreferredSize());

        this.add(label, makeConstraints(1, 0));
        this.add(healthPanel, makeConstraints(2, 0));

        this.setMaximumSize(new Dimension(600, 200));
    }

    private GridBagConstraints makeConstraints(int gridx, int gridy) {

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = gridx;
        constraints.gridy = gridy;

        return constraints;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JSpinner getHealthSpinner() {
        return healthSpinner;
    }

    public int getHealth() {
        return (int) this.healthSpinner.getValue();
    }

    public JSpinner getArmorSpinner() {
        return armorSpinner;
    }

    public int getArmor() {
        return (int) this.armorSpinner.getValue();
    }

}
