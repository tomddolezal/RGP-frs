package data.db;

import java.io.IOException;
import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

/**
 * The {@code HospitalConnector} is for changing or adding hospital data.
 */
public interface HospitalConnector {

    default void addHospital(UUID uuid, String json) throws ConnectorException {
        // Add a hospital
    }

    default void updateHospital(UUID uuid, String json) throws ConnectorException {
        // Update the db
    }

    default String getHospitals() throws ConnectorException {
        try {
            return new String(readAllBytes(get("src/main/java/data/db/hospitalinfo.json")), UTF_8);
        } catch (IOException e) {
            throw new ConnectorException();
        }
    }

}
