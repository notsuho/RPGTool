package fi.rpgtool;

import javax.swing.JFrame;

import java.awt.Dimension;

import javax.swing.*;

import fi.rpgtool.gui.RPGFrame;

public class MainWindow extends JFrame {

    private JFrame mainWindow = null;

    public MainWindow () {
        mainWindow = new JFrame();
        mainWindow.setTitle("RPGTool");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setPreferredSize(new Dimension(500, 800));
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setLayout(new BoxLayout(mainWindow.getContentPane(), BoxLayout.Y_AXIS));
        // tän voi pistää falseksi jos ei halua käyttäjän muokkaavan ikkunan kokoa
        setResizable(true);
    }
}

// public class MainWindow extends RPGFrame {

//     public MainWindow(int width, int height) {
//         super(10, 10);
//         this.setSize(width, height);
//     }

// }
