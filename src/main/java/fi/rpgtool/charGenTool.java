package fi.rpgtool;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class charGenTool extends JFrame {

    private int difficulty[] = { 5, 10, 15, 20, 25, 30 };
    private int skillBonus[] = { 1, 2, 3, 4, 5 };
    private int dieSize[] = { 4, 6, 8, 10, 12, 20, 100 };
    private String attribute[] = { "VOIMA", "NOPEUS", "ÄLY", "OVELUUS" };

    public charGenTool() {

        JFrame mainView = new JFrame();
        mainView.setTitle("RPGTool");
        mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainView.setSize(500, 800);
        mainView.setLocationRelativeTo(null);
        mainView.setLayout(new BoxLayout(mainView.getContentPane(), BoxLayout.Y_AXIS));
        // tän voi pistää falseksi jos ei halua käyttäjän muokkaavan ikkunan kokoa
        setResizable(true);

        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("File");
        menu.add(file);

        mainView.setJMenuBar(menu);

        // Luodaan nimipaneeli johon tulee hahmokuva, nimi,
        // terveyspisteet ja haarniskapisteet (armor tjsp)
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        namePanel.setBorder(new TitledBorder("HAHMON TIEDOT"));
        ImageIcon icon = new ImageIcon("kuva.jpg");
        JLabel charPic = new JLabel(icon);
        JLabel charLabel = new JLabel("Hahmon nimi");
        JTextField charName = new JTextField("Erkki Esimerkki");
        JLabel healthLabel = new JLabel("Terveyspisteet");
        JSpinner healthSpinner = new JSpinner(new SpinnerNumberModel(10, 0, 100, 1));
        // laitetaan spinnerin tekstikentän editointi pois päältä,
        // niin käyttäjä voi muokata sitä vain nuolinappien avulla
        // sama muille spinnereille myös
        ((JSpinner.DefaultEditor) healthSpinner.getEditor()).getTextField().setEditable(false);
        JLabel armorLabel = new JLabel("Panssaripisteet");
        JSpinner armorSpinner = new JSpinner(new SpinnerNumberModel(10, 10, 50, 1));
        ((JSpinner.DefaultEditor) armorSpinner.getEditor()).getTextField().setEditable(false);

        // Lisätään kaikki luodut komponentit paneeliin
        namePanel.add(charPic);
        namePanel.add(charLabel);
        namePanel.add(charName);
        namePanel.add(healthLabel);
        namePanel.add(healthSpinner);
        namePanel.add(armorLabel);
        namePanel.add(armorSpinner);

        // paneeli ominaisuuksille / attributeille
        JPanel attributePanel = new JPanel();
        attributePanel.setLayout(new GridLayout(4, 2));
        attributePanel.setBorder(new TitledBorder("OMINAISUUDET"));

        // luodaan ominaisuuksien labelit, spinnerit ja label bonukselle
        for (int i = 0; i < attribute.length; i++) {
            JLabel label = new JLabel(attribute[i]);
            JSpinner spinner = new JSpinner(new SpinnerNumberModel(10, 10, 20, 1));
            ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
            JLabel attributeBonus = new JLabel("0");
            attributeBonus.setBorder(new TitledBorder("BONUS"));
            attributeBonus.setHorizontalAlignment(SwingConstants.CENTER);
            attributePanel.add(label);
            attributePanel.add(spinner);
            attributePanel.add(attributeBonus);
        }

        JPanel skillPanel = new JPanel();
        // wanha layout paneeliin, jätin tähän niin muistaa mikä aikaisempi setup oli
        // jos siihen pitääkin palata siihen
        // skillPanel.setLayout(new GridLayout(5, 2, 5, 5));
        skillPanel.setLayout(new BoxLayout(skillPanel, BoxLayout.Y_AXIS));
        skillPanel.setBorder(new TitledBorder("TAIDOT"));

        // nämäkin jossain vaiheessa lisätä...
        // JButton help = new JButton("?");
        // skillPanel.add(help);

        // luodaan tekstikentät taidoille ja spinnerit niiden arvoille
        for (int i = 1; i <= 5; i++) {
            JTextField skillTextField = new JTextField("Taito " + i);
            JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
            ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
            skillPanel.add(skillTextField);
            skillPanel.add(spinner);
        }

        // luodaan nopanheittopaneeli
        JPanel diceRollPanel = new JPanel();
        diceRollPanel.setLayout(new GridLayout(5, 2));
        // diceRollPanel.setLayout(new BorderLayout());
        // diceRollPanel.setLayout(new BoxLayout(diceRollPanel, BoxLayout.X_AXIS));
        diceRollPanel.setBorder(new TitledBorder("NOPANHEITTO"));

        // luodaan pudotusvalikko kohdeluvulle ja täytetään (valmiista) listasta
        JComboBox<Integer> difficultySelect = new JComboBox<Integer>();
        for (int i = 0; i < difficulty.length; i++) {
            difficultySelect.addItem(difficulty[i]);
        }
        JLabel difficultyLabel = new JLabel("Valitse kohdeluku:");
        diceRollPanel.add(difficultyLabel);
        diceRollPanel.add(difficultySelect);

        // luodaan pudotusvalikko taidon arvolle ja täytetään (taas valmiista) listasta
        JComboBox<Integer> skillSelect = new JComboBox<Integer>();
        for (int i = 0; i < skillBonus.length; i++) {
            skillSelect.addItem(skillBonus[i]);
        }
        JLabel skillLabel = new JLabel("Valitse taito:");
        diceRollPanel.add(skillLabel);
        diceRollPanel.add(skillSelect);

        // luodaan pudotusvalikko ominaisuuksille ja täytetään (taas valmiista) listasta
        JComboBox<String> attributeSelect = new JComboBox<String>();
        for (int i = 0; i < attribute.length; i++) {
            attributeSelect.addItem(attribute[i]);
        }
        JLabel attributeLabel = new JLabel("Valitse ominaisuus:");
        diceRollPanel.add(attributeLabel);
        diceRollPanel.add(attributeSelect);

        // luodaan pudotusvalikko heitettävän nopan koolle ja täytetään (taas valmiista)
        // listasta
        JComboBox<Integer> dieSelect = new JComboBox<Integer>();
        for (int i = 0; i < dieSize.length; i++) {
            dieSelect.addItem(dieSize[i]);
        }
        JLabel diceLabel = new JLabel("Nopan koko:");
        diceRollPanel.add(diceLabel);
        diceRollPanel.add(dieSelect);

        // luodaan nopanheittonappi ja tuloskenttä (Jonka kai olis tarkoitus olla popup
        // dialogi mutta good enough for now)
        JButton rollButton = new JButton("HEITÄ NOPPAA");
        diceRollPanel.add(rollButton);

        JTextField resultField = new JTextField("Heittotulos: ");
        diceRollPanel.add(resultField);

        // action listener nopanheittonapille
        rollButton.addActionListener(new ActionListener() {

            Timer timer;

            public void actionPerformed(ActionEvent e) {

                timer = new Timer(50, new ActionListener() {

                    final int rolls = 6;
                    final int ticks = 10;
                    int tick = 0;
                    final Random rand = new Random();

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int dice = (int) dieSelect.getSelectedItem();
                        int result = rand.nextInt(1, dice + 1);

                        if (tick <= rolls) {
                            rollButton.setText("Silmäluku: " + result);
                        } else if (tick == ticks) {

                            int skill = (int) skillSelect.getSelectedItem();

                            result += skill;

                            int target = (int) difficultySelect.getSelectedItem();

                            if (result >= target) {
                                resultField.setText("Onnistuit! Tulos: " + result);
                            } else {
                                resultField.setText("Epäonnistuit! Tulos: " + result);
                            }

                            timer.stop();
                        }

                        tick++;
                    }
                });

                timer.setInitialDelay(0);
                timer.start();
            }
        });

        // lisätään kaikki asiat päänäkymään
        mainView.add(namePanel);
        mainView.add(attributePanel);
        mainView.add(skillPanel);
        mainView.add(diceRollPanel);

        mainView.pack();

        mainView.setVisible(true);
    }

    public static void main(String[] args) {
        new charGenTool();
    }
}