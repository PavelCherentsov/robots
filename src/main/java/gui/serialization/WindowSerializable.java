package gui.serialization;

import com.google.gson.JsonSerializer;

public interface WindowSerializable {
    public JsonSerializer getSerializer();
}
