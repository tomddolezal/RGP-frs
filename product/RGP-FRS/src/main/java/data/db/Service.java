package data.db;

import java.util.*;

/**
 * A {@code Service} is a service provided by a {@link Hospital} and unique and has a wait time.
 */
public class Service {
    private final UUID uuid;
    private final String type, service, waitTime;
    private final Collection<String> contactDetails;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service1 = (Service) o;
        return Objects.equals(uuid, service1.uuid) &&
                Objects.equals(type, service1.type) &&
                Objects.equals(service, service1.service) &&
                Objects.equals(waitTime, service1.waitTime) &&
                Objects.equals(contactDetails, service1.contactDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, type, service, waitTime, contactDetails);
    }

    /**
     * Creates a new service
     *
     * @param uuid           unique identifier
     * @param type           service type
     * @param service        the name
     * @param waitTime       the end of the waiting time for this service from today
     * @param contactDetails contact information for this service
     */
    public Service(UUID uuid, String type, String service, String waitTime, List<String> contactDetails) {
        this.uuid = uuid;
        this.type = type;
        this.service = service;
        this.waitTime = waitTime;
        this.contactDetails = new ArrayList<>(contactDetails);
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getType() {
        return type;
    }

    public String getService() {
        return service;
    }

    public String getWaitTime() {
        return waitTime;
    }

    public Collection<String> getContactDetails() {
        return new ArrayList<>(contactDetails);
    }
}
