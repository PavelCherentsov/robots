package gui.serialization;

import com.google.gson.*;

import javax.swing.*;
import java.beans.PropertyVetoException;
import java.lang.reflect.Type;

public class SimpleWindowSerializer implements JsonSerializer<JInternalFrame>, JsonDeserializer<JInternalFrame> {
    @Override
    public JInternalFrame deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JInternalFrame window = new JInternalFrame();
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        window.setLocation(jsonObject.get("x").getAsInt(), jsonObject.get("y").getAsInt());
        window.setSize(jsonObject.get("width").getAsInt(), jsonObject.get("height").getAsInt());

        try {
            window.setIcon(jsonObject.get("isIcon").getAsBoolean());
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        System.out.println(1);
        return window;
    }

    @Override
    public JsonElement serialize(JInternalFrame jInternalFrame, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.add("width", new JsonPrimitive(jInternalFrame.getWidth()));
        result.add("height", new JsonPrimitive(jInternalFrame.getHeight()));
        result.add("x", new JsonPrimitive(jInternalFrame.getX()));
        result.add("y", new JsonPrimitive(jInternalFrame.getY()));
        result.add("isIcon", new JsonPrimitive(jInternalFrame.isIcon()));

        return result;
    }
}
