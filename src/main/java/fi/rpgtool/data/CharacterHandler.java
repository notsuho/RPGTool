package fi.rpgtool.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CharacterHandler {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    // This is usually a bad idea, but we are running out of time and there can only be one character at a time either way
    private static Character character;
    private static File characterFile;

    /**
     * Load a character from a JSON file or create a new one if the file does not exist
     * @param filePath the path to the character file
     * @return the loaded {@link Character} or a new {@link Character} if the file could not be found
     */
    public static Character load(String filePath) {
        return load(new File(filePath));
    }

    /**
     * Load a character from a JSON file or create a new one if the file does not exist
     * @param file the character file
     * @return the loaded {@link Character} or a new {@link Character} if the file could not be found
     */
    public static Character load(File file) {

        characterFile = file;

        try {
            JsonReader reader = new JsonReader(new FileReader(file));

            character = GSON.fromJson(reader, Character.class);

            reader.close();
        } catch (IOException ex) {
            character = new Character();
        }

        return character;
    }

    public static void save() throws IOException {
        save(characterFile);
    }

    public static void save(String filePath) throws IOException {
        save(new File(filePath));
    }

    public static void save(File file) throws IOException {
        Files.writeString(Path.of(file.getPath()), GSON.toJson(character, Character.class));
    }

}
