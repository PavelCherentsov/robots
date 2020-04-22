package gui.serialization;

import com.google.gson.*;

import javax.swing.*;
import java.beans.PropertyVetoException;


public class DefaultWindowSerializer {
    public JsonElement serialize(final JInternalFrame window, JsonObject result) {
        result.add("width", new JsonPrimitive(window.getWidth()));
        result.add("height", new JsonPrimitive(window.getHeight()));
        result.add("x", new JsonPrimitive(window.getX()));
        result.add("y", new JsonPrimitive(window.getY()));
        result.add("isIcon", new JsonPrimitive(window.isIcon()));

        return result;
    }

    public JInternalFrame deserialize(JsonObject jsonObject, JInternalFrame window) throws JsonParseException {

        window.setLocation(jsonObject.get("x").getAsInt(), jsonObject.get("y").getAsInt());
        window.setSize(jsonObject.get("width").getAsInt(), jsonObject.get("height").getAsInt());

        try {
            window.setIcon(jsonObject.get("isIcon").getAsBoolean());
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        return window;
    }
}
