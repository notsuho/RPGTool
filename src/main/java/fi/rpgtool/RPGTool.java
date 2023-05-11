package fi.rpgtool;

import fi.rpgtool.gui.window.InventoryWindow;
import fi.rpgtool.gui.window.MainWindow;
import fi.rpgtool.gui.window.StatisticWindow;

import javax.swing.*;
import java.io.File;

public class RPGTool {

    public static void main(String[] args) {
        // Todennäköisesti turha osa, mutta sallii koodin jatkamisen tässä
        MainWindow window = createWindow(new File("default.json"));
    }

    public static MainWindow createWindow(File file) {

        MainWindow window = new MainWindow(file);

        StatisticWindow statisticWindow = new StatisticWindow(window);
        InventoryWindow inventoryWindow = new InventoryWindow(window);

        JTabbedPane pane = new JTabbedPane();

        pane.addTab("Hahmotiedot", statisticWindow);
        pane.addTab("Tavaraluettelo", inventoryWindow);

        pane.setTabComponentAt(0, new JLabel("Hahmotiedot"));
        pane.setTabComponentAt(1, new JLabel("Tavaraluettelo"));

        // Datan hakemiseksi
        window.setStatisticWindow(statisticWindow);
        window.setInventoryWindow(inventoryWindow);

        window.getContentPane().add(pane);
        window.pack();

        SwingUtilities.invokeLater(() -> {

            // Asetetaan minimikoko samaksi
            window.setMinimumSize(window.getPreferredSize());
            window.validate();

            // Asetetaan ikkuna näkyviin
            window.setVisible(true);
        });

        return window;
    }

}
