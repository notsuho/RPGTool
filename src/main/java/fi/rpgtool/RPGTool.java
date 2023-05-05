package fi.rpgtool;

import fi.rpgtool.gui.window.InventoryWindow;
import fi.rpgtool.gui.window.StatisticWindow;

import javax.swing.*;
import java.awt.*;

public class RPGTool {

    public static void main(String[] args) {

        MainWindow window = new MainWindow(600, 800);

        InventoryWindow inventoryWindow = new InventoryWindow(600, 700);
        StatisticWindow statisticWindow = new StatisticWindow(600, 700);

        JTabbedPane pane = new JTabbedPane();

        MenuBar menu = new MenuBar();
        Menu file = new Menu("File");
        menu.add(file);

        window.setMenuBar(menu);

        JLabel statisticLabel = new JLabel("Statistics");
        JLabel inventoryLabel = new JLabel("Inventory");
        statisticLabel.setPreferredSize(new Dimension(150, 30));
        inventoryLabel.setPreferredSize(new Dimension(150, 30));

        pane.addTab("Statistics", statisticWindow);
        pane.addTab("Inventory", inventoryWindow);

        pane.setTabComponentAt(0, statisticLabel);
        pane.setTabComponentAt(1, inventoryLabel);

        pane.setVisible(true);

        window.setContentPane(pane);
        window.setVisible(true);
    }

}
