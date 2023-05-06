package fi.rpgtool.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ThreadLocalRandom;

public class RollButton extends JButton {

    public RollButton() {
        this.setText("Click to roll!");
        this.addMouseListener(new RollButtonClickListener(this));
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

                    if (tick <= rolls) {
                        rollButton.setText("Rolled: " + ThreadLocalRandom.current().nextInt(1, 7));
                    } else if (tick == ticks) {
                        rollButton.setText("<html><center>" + rollButton.getText() + "<br/>Click to roll!</center></html>");
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

