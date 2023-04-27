package fi.rpgtool;

import fi.rpgtool.gui.InventoryWindow;
import fi.rpgtool.gui.MainWindow;
import fi.rpgtool.gui.StatisticWindow;

public class RPGTool {

    public static void main(String[] args) {

        MainWindow window = new MainWindow(600, 800);

        InventoryWindow inventoryWindow = new InventoryWindow(600, 700);
        StatisticWindow statisticWindow = new StatisticWindow(600, 700);

        window.add(inventoryWindow);
        window.add(statisticWindow);

        window.setVisible(true);
    }

}
