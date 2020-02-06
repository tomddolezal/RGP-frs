package data.db.json;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;

public interface JsonConverter<T> extends JsonSerializer<T>, JsonDeserializer<T> {

    default String asString(JsonObject jsonObject, String field) {
        return jsonObject.get(field).getAsString();
    }
}
