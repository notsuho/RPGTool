package fi.rpgtool.data;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Character {

    private static final Gson gson = new Gson();

    private String name;
    private Map<String, Integer> statistics;
    private Map<String, Integer> abilities;

    /**
     * Load a character from a JSON file.
     * @param filePath the path to the character file
     * @return the loaded {@link Character}
     * @throws IOException if reading the file failed.
     */
    public static Character load(String filePath) throws IOException {
        return load(new File(filePath));
    }

    /**
     * Load a character from a JSON file.
     * @param file the character file
     * @return the loaded {@link Character}
     * @throws IOException if reading the file failed.
     */
    public static Character load(File file) throws IOException {

        JsonReader reader = new JsonReader(new FileReader(file));

        Character character = gson.fromJson(reader, Character.class);

        reader.close();

        return character;
    }

    public void save(String filePath) throws IOException {
        save(new File(filePath));
    }

    public void save(File file) throws IOException {
        Files.writeString(Path.of(file.getPath()), gson.toJson(this, Character.class));
    }

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

}
