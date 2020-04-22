package gui.serialization;


import com.google.gson.*;
import gui.GameWindow;

import java.lang.reflect.Type;

public class GameWindowSerializer extends DefaultWindowSerializer implements JsonSerializer<GameWindow>, JsonDeserializer<GameWindow>{
    public JsonElement serialize(final GameWindow window, final Type type, final JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        super.serialize(window, result);

        result.add("targetX", new JsonPrimitive(window.getTargetPosition().x));
        result.add("targetY", new JsonPrimitive(window.getTargetPosition().y));

        JsonObject robot = new JsonObject();
        robot.add("x", new JsonPrimitive(window.getRobot().getM_PositionX()));
        robot.add("y", new JsonPrimitive(window.getRobot().getM_PositionY()));
        robot.add("direction", new JsonPrimitive(window.getRobot().getM_Direction()));
        result.add("robot", robot);

        return result;
    }

    public GameWindow deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        GameWindow gameWindow = new GameWindow();
        JsonObject w = jsonElement.getAsJsonObject();

        super.deserialize(w,gameWindow);

        JsonObject r = w.get("robot").getAsJsonObject();

        gameWindow.setRobot(r.get("x").getAsDouble(), r.get("y").getAsDouble(), r.get("direction").getAsDouble());
        gameWindow.setTargetPosition(w.get("targetX").getAsInt(), w.get("targetY").getAsInt());
        return gameWindow;
    }
}
