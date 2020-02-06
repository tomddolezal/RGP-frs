package data.db.json;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import data.db.Hospital;
import data.db.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static data.db.Hospital.HospitalBuilder.getNewHospitalBuilder;

/**
 *
 */
public class HospitalJsonConvertor implements JsonConverter<Hospital> {
    @Override
    public JsonElement serialize(Hospital hospital, Type type, JsonSerializationContext context) {
        JsonObject serializedHospital = new JsonObject();
        serializedHospital.addProperty("UUID", hospital.getUuid().toString());
        serializedHospital.addProperty("website", hospital.getWebsite());
        serializedHospital.addProperty("address", hospital.getAddress());
        serializedHospital.addProperty("hospital", hospital.getName());
        serializedHospital.add("services", context.serialize(hospital.getServicesProvided(), new TypeToken<List<Service>>() {
        }.getType()));
        return serializedHospital;
    }


    @Override
    public Hospital deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject deserializedHospital = json.getAsJsonObject();
        Hospital.HospitalBuilder builder = getNewHospitalBuilder(asString(deserializedHospital, "hospital"),
                UUID.fromString(asString(deserializedHospital, "UUID")))
                .withAddress(asString(deserializedHospital, "address"))
                .withWebsite(asString(deserializedHospital, "website"))
                .withServices(jsonDeserializationContext.deserialize(deserializedHospital.get("services"), new TypeToken<List<Service>>() {
                }.getType()));
        Optional<Hospital> hospital = builder.build();
        if (hospital.isPresent()) {
            return hospital.get();
        } else {
            throw new JsonParseException("failed to parse Hospital");
        }
    }
}