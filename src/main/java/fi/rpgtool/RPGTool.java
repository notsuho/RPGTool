package fi.rpgtool;

import fi.rpgtool.gui.window.InventoryWindow;
import fi.rpgtool.gui.window.MainWindow;
import fi.rpgtool.gui.window.StatisticWindow;

import javax.swing.*;

public class RPGTool {

    public static void main(String[] args) {
        // Todennäköisesti turha osa, mutta sallii koodin jatkamisen tässä
        MainWindow window = createWindow();
    }

    public static MainWindow createWindow() {

        MainWindow window = new MainWindow("default.json");

        StatisticWindow statisticWindow = new StatisticWindow(window);
        InventoryWindow inventoryWindow = new InventoryWindow(window);

        JTabbedPane pane = new JTabbedPane();

        pane.addTab("Statistics", statisticWindow);
        pane.addTab("Inventory", inventoryWindow);

        pane.setTabComponentAt(0, new JLabel("Statistics"));
        pane.setTabComponentAt(1, new JLabel("Inventory"));

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
