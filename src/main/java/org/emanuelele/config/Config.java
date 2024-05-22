package org.emanuelele.config;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.IOException;

public class Config implements Iconfig {
    private JsonObject configObject;
    private final String CONFIG_FILE_PATH = "config.json";

    public Config() {
        loadConfig();
    }

    private void loadConfig() {
        try (FileReader reader = new FileReader(CONFIG_FILE_PATH)) {
            configObject = new JsonParser().parse(reader).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JsonElement getValueForKey(String key) {
        String[] keys = key.split("\\.");
        JsonObject currentObject = configObject;
        for (String k : keys) {
            if (currentObject.has(k)) {
                JsonElement element = currentObject.get(k);
                if (!element.isJsonObject()) {
                    return element;
                }
                currentObject = element.getAsJsonObject();
            } else {
                throw new IllegalArgumentException("Key '" + key + "' not found in config");
            }
        }
        return currentObject;
    }

    public String getString(String key) {
        return getValueForKey(key).getAsString();
    }

    public int getInt(String key) {
        return getValueForKey(key).getAsInt();
    }

    public double getDouble(String key) {
        return getValueForKey(key).getAsDouble();
    }

    public boolean getBoolean(String key) {
        return getValueForKey(key).getAsBoolean();
    }

    public JsonObject getJsonObject(String key) {
        return getValueForKey(key).getAsJsonObject();
    }
}
