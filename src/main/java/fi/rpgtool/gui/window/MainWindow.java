package fi.rpgtool.gui.window;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    /**
     * 
     */
    public MainWindow () {

        this.setTitle("RPGTool");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(700, 900));
        this.setLocationRelativeTo(null);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        // tän voi pistää falseksi jos ei halua käyttäjän muokkaavan ikkunan kokoa
        this.setResizable(true);
    }
}
