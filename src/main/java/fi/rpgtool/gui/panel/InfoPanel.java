package fi.rpgtool.gui.panel;

import fi.rpgtool.data.Character;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class InfoPanel extends JPanel {

    private final JTextField nameField;
    private final JSpinner healthSpinner;
    private final JSpinner armorSpinner;

    public InfoPanel(Character character) {

        // this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setLayout(new GridLayout(1, 2));
        this.setBorder(new TitledBorder("HAHMON TIEDOT"));

        this.nameField = new JTextField(character.getName(), 25);
        this.nameField.setBorder(new TitledBorder("Hahmon nimi"));
        this.nameField.setPreferredSize(new Dimension(50, 40));

        // laitetaan spinnerin tekstikentän editointi pois päältä,
        // niin käyttäjä voi muokata sitä vain nuolinappien avulla
        // sama muille spinnereille myös
        this.healthSpinner = new JSpinner(new SpinnerNumberModel(character.getHealth(), 0, 100, 1));
        this.healthSpinner.setBorder(new TitledBorder("Terveyspisteet"));
        this.healthSpinner.setPreferredSize(new Dimension(50, 20));
        ((JSpinner.DefaultEditor) healthSpinner.getEditor()).getTextField().setEditable(false);
        this.healthSpinner.addChangeListener(change -> character.setHealth((int) this.healthSpinner.getValue()));

        this.armorSpinner = new JSpinner(new SpinnerNumberModel(character.getArmor(), 10, 50, 1));
        this.armorSpinner.setBorder(new TitledBorder("Panssaripisteet"));
        this.armorSpinner.setPreferredSize(new Dimension(50, 20));
        ((JSpinner.DefaultEditor) armorSpinner.getEditor()).getTextField().setEditable(false);
        this.armorSpinner.addChangeListener(change -> character.setArmor((int) this.armorSpinner.getValue()));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel1.add(new JLabel(new ImageIcon("src/main/resources/kuva-isompi.jpg")));
        panel1.add(this.nameField);

        JPanel panel2 = new JPanel();
        // panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel2.setLayout(new GridLayout(2, 2));
        // panel2.setLayout(new FlowLayout(1));
        // panel2.setBorder(new TitledBorder("TESTI"));

        panel2.add(new JLabel(new ImageIcon("src/main/resources/hp-heart.jpg")));
        panel2.add(this.healthSpinner);
        panel2.add(new JLabel(new ImageIcon("src/main/resources/armor-shield.jpg")));
        panel2.add(this.armorSpinner);

        this.add(panel1);
        this.add(panel2);
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
