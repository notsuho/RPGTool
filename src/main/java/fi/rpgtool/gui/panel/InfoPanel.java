package fi.rpgtool.gui.panel;

import fi.rpgtool.data.Character;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class InfoPanel extends JPanel {

    private final JTextField nameField;
    private final JSpinner healthSpinner;
    private final JSpinner armorSpinner;

    public InfoPanel(Character character) {

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(new TitledBorder("HAHMON TIEDOT"));

        this.nameField = new JTextField(character.getName(), 25);
        this.nameField.setBorder(new TitledBorder("Hahmon nimi"));
        this.nameField.setPreferredSize(new Dimension(50, 20));

        // laitetaan spinnerin tekstikentän editointi pois päältä,
        // niin käyttäjä voi muokata sitä vain nuolinappien avulla
        // sama muille spinnereille myös
        this.healthSpinner = new JSpinner(new SpinnerNumberModel(10, 0, 100, 1));
        this.healthSpinner.setBorder(new TitledBorder("Terveyspisteet"));
        this.healthSpinner.setPreferredSize(new Dimension(50, 20));
        ((JSpinner.DefaultEditor) healthSpinner.getEditor()).getTextField().setEditable(false);

        this.armorSpinner = new JSpinner(new SpinnerNumberModel(10, 10, 50, 1));
        this.armorSpinner.setBorder(new TitledBorder("Panssaripisteet"));
        this.armorSpinner.setPreferredSize(new Dimension(50, 20));
        ((JSpinner.DefaultEditor) armorSpinner.getEditor()).getTextField().setEditable(false);

        this.add(new JLabel(new ImageIcon("src/main/resources/kuva-isompi.jpg")));
        this.add(this.nameField);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        panel.add(this.healthSpinner);
        panel.add(this.armorSpinner);

        this.add(panel);
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
