package gui.serialization;


import com.google.gson.*;
import gui.GameWindow;
import gui.RobotInfoWindow;

import java.lang.reflect.Type;

public class RobotInfoWindowSerializer extends DefaultWindowSerializer implements JsonSerializer<RobotInfoWindow>, JsonDeserializer<RobotInfoWindow>{
    public JsonElement serialize(final RobotInfoWindow window, final Type type, final JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        super.serialize(window, result);

        return result;
    }

    public RobotInfoWindow deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        RobotInfoWindow gameWindow = new RobotInfoWindow();
        JsonObject w = jsonElement.getAsJsonObject();

        super.deserialize(w,gameWindow);

        return gameWindow;
    }
}
