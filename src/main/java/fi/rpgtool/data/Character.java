package fi.rpgtool.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Dataluokka varsinaiselle hahmolle.
 * GSON osaa lukea JSON tiedoston tämän luokan instanssiksi, ja osaa myös kirjoittaa uuden JSON tiedoston tämän luokan perusteella.
 */
public class Character {

    private static final int DEFAULT_ATTRIBUTE_VALUE = 10;

    private final Map<String, Integer> attributes = new HashMap<>();
    private final Map<String, Integer> skills = new HashMap<>();

    private String name = "";
    private int health = 10;
    private int armor = 10;
    private String notes;
    private final List<String> inventory = new ArrayList<>();

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

    public Map<String, Integer> getSkills() {
        return skills;
    }

    public void setSkill(String key, int value) {
        skills.put(key, value);
    }

    public List<String> getInventory() {
        return inventory;
    }

    public void addInventoryItem(String item) {
        inventory.add(item);
    }

}
