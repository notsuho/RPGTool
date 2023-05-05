package fi.rpgtool.gui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ThreadLocalRandom;

public class RollButton extends JButton {

    public RollButton() {
        this.setText("Rolled: " + ThreadLocalRandom.current().nextInt(1, 6));
        this.addMouseListener(new RollButtonClickListener(this));
    }

    static class RollButtonClickListener implements MouseListener {

        private final RollButton rollButton;

        public RollButtonClickListener(RollButton rollButton) {
            this.rollButton = rollButton;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            this.rollButton.setText("Rolled: " + ThreadLocalRandom.current().nextInt(1, 6));
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

