package gui.serialization;


import com.google.gson.*;
import gui.LogWindow;
import gui.serialization.DefaultWindowSerializer;
import log.LogEntry;
import log.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LogWindowSerializer extends DefaultWindowSerializer implements JsonSerializer<LogWindow>, JsonDeserializer<LogWindow> {
    public JsonElement serialize(final LogWindow logWindow, final Type type, final JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        super.serialize(logWindow, result);

        ArrayList<LogEntry> arrayList = logWindow.getList();
        JsonArray list = new JsonArray();
        for (LogEntry e: arrayList) {
            list.add(e.getMessage());
        }
        result.add("list", list);
        return result;
    }

    public LogWindow deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        LogWindow lw = new LogWindow(Logger.getDefaultLogSource());
        JsonArray jsonArray = jsonElement.getAsJsonObject().get("list").getAsJsonArray();
        super.deserialize(jsonElement.getAsJsonObject(), lw);

        return lw;
    }
}
