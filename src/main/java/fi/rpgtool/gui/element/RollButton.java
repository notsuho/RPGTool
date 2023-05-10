package fi.rpgtool.gui.element;

import fi.rpgtool.gui.window.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Varsinainen nopanheittonappi. Nappia painettaessa se generoi satunnaisen luvun välillä [1, nopankoko] + attribuutin arvo + taidon arvo.
 * Attribuutin arvo ja taidon arvo haetaan niiden omista paneeleista automaattisesti nopanheittopaneelissa valittujen arvojen perusteella.
 */
public class RollButton extends JButton {

    private JComboBox<Integer> dieSelector = null;
    private JComboBox<String> skillSelector = null;
    private JComboBox<String> attributeSelector = null;
    private JComboBox<Integer> difficultySelector = null;

    private static final int DEFAULT_DIE = 4;
    private static final int DEFAULT_SKILL = 1;
    private static final int DEFAULT_ATTRIBUTE = 10;
    private static final int DEFAULT_DIFFICULTY = 5;

    private final MainWindow mainWindow;

    public RollButton(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.setText("HEITÄ NOPPAA");
        this.addMouseListener(new RollButtonClickListener(this));
    }

    public void setDieSelector(JComboBox<Integer> dieSelector) {
        this.dieSelector = dieSelector;
    }

    public void setAttributeSelector(JComboBox<String> attributeSelector) {
        this.attributeSelector = attributeSelector;
    }

    public void setSkillSelector(JComboBox<String> skillSelector) {
        this.skillSelector = skillSelector;
    }

    public void setDifficultySelector(JComboBox<Integer> difficultySelector) {
        this.difficultySelector = difficultySelector;
    }

    public int getDie() {

        if (this.dieSelector == null || this.dieSelector.getSelectedItem() == null) {
            return DEFAULT_DIE;
        }

        return (int) this.dieSelector.getSelectedItem();
    }

    public int getAttribute() {

        if (this.attributeSelector == null || this.attributeSelector.getSelectedItem() == null) {
            return DEFAULT_ATTRIBUTE;
        }

        return (int) mainWindow.getStatisticWindow().getAttributePanel().getData().get(this.attributeSelector.getSelectedIndex()).right.getValue();
    }

    public int getSkill() {

        if (this.skillSelector == null || this.skillSelector.getSelectedItem() == null) {
            return DEFAULT_SKILL;
        }

        return (int) mainWindow.getStatisticWindow().getSkillPanel().getData().get(this.skillSelector.getSelectedIndex()).right.getValue();
    }

    public int getDifficulty() {

        if (this.difficultySelector == null || this.difficultySelector.getSelectedItem() == null) {
            return DEFAULT_DIFFICULTY;
        }

        return (int) this.difficultySelector.getSelectedItem();
    }

    static class RollButtonClickListener implements MouseListener {

        private final RollButton rollButton;
        private Timer timer;

        public RollButtonClickListener(RollButton rollButton) {
            this.rollButton = rollButton;
        }

        @Override
        public void mouseClicked(MouseEvent e) {

            timer = new Timer(50, new ActionListener() {

                final int rolls = 6;
                final int ticks = 10;
                int tick = 0;

                @Override
                public void actionPerformed(ActionEvent e) {

                    int dice = rollButton.getDie();
                    int result = ThreadLocalRandom.current().nextInt(1, dice + 1);

                    if (tick <= rolls) {
                        rollButton.setText("Silmäluku: " + result);
                    } else if (tick == ticks) {

                        int skill = rollButton.getSkill();
                        int attributeBonus = (rollButton.getAttribute()-10);

                        int noppa = result;
                        result += skill;
                        result += attributeBonus;

                        int target = rollButton.getDifficulty();

                        if (result >= target) {
                            rollButton.setText("<html><center>HEITÄ NOPPAA<br/>Onnistuit!"
                            + "<br/>Tulos: " + result 
                            + "<br/>Noppa: " + noppa
                            + "<br/>Ominaisuus: +" + attributeBonus 
                            + "<br/>Taito: +" + skill + "</center></html>");
                        } else {
                            rollButton.setText("<html><center>HEITÄ NOPPAA<br/>Epäonnistuit!"
                            + "<br/>Tulos: " + result
                            + "<br/>Noppa: " + noppa
                            + "<br/>Ominaisuus: +" + attributeBonus 
                            + "<br/>Taito: +" + skill + "</center></html>");
                        }

                        timer.stop();
                    }

                    tick++;
                }
            });

            timer.setInitialDelay(0);
            timer.start();
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    }

}

