package fi.rpgtool.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Character {

    private static final int DEFAULT_ATTRIBUTE_VALUE = 10;

    private final Map<String, Integer> attributes = new HashMap<>();
    private final Map<String, Integer> abilities = new HashMap<>();
    private final List<String> inventory = new ArrayList<>();

    private String name = "";
    private int health = 10;
    private int armor = 10;
    private String notes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getArmor() {
        return armor;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public Map<String, Integer> getAttributes() {
        return attributes;
    }

    public void setAttribute(String key, int value) {
        attributes.put(key, value);
    }

    public int getAttribute(String key) {
        return attributes.getOrDefault(key, DEFAULT_ATTRIBUTE_VALUE);
    }

    public Map<String, Integer> getAbilities() {
        return abilities;
    }

    public void setAbility(String key, int value) {
        abilities.put(key, value);
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
