package data.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import data.db.json.HospitalJsonConvertor;
import data.db.json.ServiceJsonConvertor;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static data.db.Hospital.HospitalBuilder.getNewHospitalBuilder;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.io.FileUtils.writeStringToFile;

/**
 * In between the {@link servlet.HospitalServlet} and {@link HospitalConnector}
 */
public class HospitalCache {

    private final Collection<Hospital> cache = new HashSet<>();
    private final Gson jsonConvertor;
    private final HospitalConnector connector;

    public HospitalCache(HospitalConnector connector) throws ConnectorException {
        this.connector = connector;
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Hospital.class, new HospitalJsonConvertor());
        builder.registerTypeAdapter(Service.class, new ServiceJsonConvertor());
        jsonConvertor = builder.create();
        cache.addAll(readHospitals(connector));
    }

    public String getHospitals() {
        return jsonConvertor.toJson(cache.toArray());
    }

    /**
     * Updates the service.
     *
     * @param json json for the service to be updated
     */
    public void updateService(String json) {
        Service service = jsonConvertor.fromJson(json, Service.class);
        Hospital hospital = cache.stream()
                .filter(h -> h.getServicesProvided().stream()
                        .filter(s -> s.getUuid().equals(service.getUuid())).count() == 1)
                .findAny()
                .orElse(null);

        if (hospital != null) {
            cache.remove(hospital);
            List<Service> services = hospital.getServicesProvided().stream()
                    .filter(s -> !s.getUuid().equals(service.getUuid()))
                    .collect(toList());
            services.add(service);
            Optional<Hospital> updatedHospital = getNewHospitalBuilder(hospital.getName(), hospital.getUuid())
                    .withWebsite(hospital.getWebsite())
                    .withAddress(hospital.getAddress())
                    .withServices(services)
                    .build();

            if (updatedHospital.isPresent()) {
                cache.add(updatedHospital.get());
                try {
                    updateData(updatedHospital.get());
                } catch (ConnectorException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void updateData(Hospital updatedHospital) throws ConnectorException {
        connector.updateHospital(updatedHospital.getUuid(), jsonConvertor.toJson(updatedHospital));
        writeDataToDisk(jsonConvertor.toJson(cache.toArray()));
    }

    private Collection<? extends Hospital> readHospitals(HospitalConnector connector) throws ConnectorException {
        return jsonConvertor.fromJson(connector.getHospitals(),
                new TypeToken<List<Hospital>>() {
                }.getType());
    }

    private static void writeDataToDisk(String json) throws ConnectorException {
        try {
            writeStringToFile(new File("src/main/java/data/db/hospitalinfo.json"), json, UTF_8);
        } catch (IOException e) {
            throw new ConnectorException();
        }
    }
}