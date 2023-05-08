package fi.rpgtool.gui;

import fi.rpgtool.data.Character;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class InfoPanel extends JPanel {

    private final JTextField nameField;
    private final JSpinner healthSpinner;
    private final JSpinner armorSpinner;

    public InfoPanel(Character character) {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new TitledBorder("HAHMON TIEDOT"));

        this.nameField = new JTextField(character.getName(), 25);
        nameField.setBorder(new TitledBorder("Hahmon nimi"));

        // laitetaan spinnerin tekstikentän editointi pois päältä,
        // niin käyttäjä voi muokata sitä vain nuolinappien avulla
        // sama muille spinnereille myös
        this.healthSpinner = new JSpinner(new SpinnerNumberModel(10, 0, 100, 1));
        this.healthSpinner.setBorder(new TitledBorder("Terveyspisteet"));
        ((JSpinner.DefaultEditor) healthSpinner.getEditor()).getTextField().setEditable(false);

        this.armorSpinner = new JSpinner(new SpinnerNumberModel(10, 10, 50, 1));
        this.armorSpinner.setBorder(new TitledBorder("Panssaripisteet"));
        ((JSpinner.DefaultEditor) armorSpinner.getEditor()).getTextField().setEditable(false);

        this.add(new JLabel(new ImageIcon("src/main/java/fi/rpgtool/kuva.jpg")));
        // this.add(new JLabel("Hahmon nimi"));
        this.add(this.nameField);
        // this.add(new JLabel("Terveyspisteet"));
        this.add(this.healthSpinner);
        // this.add(new JLabel("Panssaripisteet"));
        this.add(this.armorSpinner);
    }

    public JTextField getNameField() {
        return nameField;
    }

    public int getHealth() {
        return (int) this.healthSpinner.getValue();
    }

    public int getArmor() {
        return (int) this.armorSpinner.getValue();
    }

}
