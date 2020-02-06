package data.db.json;

import com.google.gson.*;
import data.db.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.fromString;

public class ServiceJsonConvertor implements JsonConverter<Service> {

    @Override
    public Service deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject deserializedService = json.getAsJsonObject();
        List<String> contacts = new ArrayList<>();
        deserializedService.getAsJsonArray("contact").forEach(contact ->
                contacts.add(contact.getAsString())
        );
        return new Service(
                fromString(asString(deserializedService, "UUID")),
                asString(deserializedService, "type"),
                asString(deserializedService, "service"),
                asString(deserializedService, "waittime"),
                contacts
        );
    }

    @Override
    public JsonElement serialize(Service service, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject serializedService = new JsonObject();
        serializedService.addProperty("UUID", service.getUuid().toString());
        serializedService.addProperty("type", service.getType());
        serializedService.addProperty("service", service.getService());
        serializedService.addProperty("waittime", service.getWaitTime());
        JsonArray contactInfo = new JsonArray();
        service.getContactDetails().forEach(contactInfo::add);
        serializedService.add("contact", contactInfo);
        return serializedService;
    }
}
