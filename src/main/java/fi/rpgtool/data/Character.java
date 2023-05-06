package fi.rpgtool.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Character {

    private String name = "";
    private final Map<String, Integer> statistics = new HashMap<>();
    private final Map<String, Integer> abilities = new HashMap<>();
    private final List<String> inventory = new ArrayList<>();

    public String getName() {
        return name;
    }

    public Map<String, Integer> getStatistics() {
        return statistics;
    }

    public Map<String, Integer> getAbilities() {
        return abilities;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatistic(String key, int value) {
        statistics.put(key, value);
    }

    public void setAbility(String key, int value) {
        abilities.put(key, value);
    }

    public void removeStatistic(String key) {
        statistics.remove(key);
    }

    public void removeAbility(String key) {
        abilities.remove(key);
    }

    public List<String> getInventory() {
        return inventory;
    }

    public void addInventoryItem(String item) {
        inventory.add(item);
    }

    public void removeInventoryItem(int index) {
        inventory.remove(index);
    }

}
