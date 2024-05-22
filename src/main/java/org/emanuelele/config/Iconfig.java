package org.emanuelele.config;

import com.google.gson.JsonObject;

public interface Iconfig {
    String getString(String key);
    int getInt(String key);
    double getDouble(String key);
    boolean getBoolean(String key);
    JsonObject getJsonObject(String key);
}
