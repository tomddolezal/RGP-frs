package data.db;

import java.util.*;

import static java.util.Collections.unmodifiableList;

public class Hospital {

    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getWebsite() {
        return website;
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<Service> getServicesProvided() {
        return unmodifiableList(servicesProvided);
    }

    private String website;
    private UUID uuid;

    private List<Service> servicesProvided;

    private Hospital(HospitalBuilder builder) {
        this.name = builder.name;
        this.uuid = builder.uuid;
        this.website = builder.website;
        this.address = builder.address;
        this.servicesProvided = builder.servicesProvided;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hospital hospital = (Hospital) o;
        return Objects.equals(name, hospital.name) &&
                Objects.equals(address, hospital.address) &&
                Objects.equals(website, hospital.website) &&
                Objects.equals(uuid, hospital.uuid) &&
                Objects.equals(servicesProvided, hospital.servicesProvided);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, website, uuid, servicesProvided);
    }

    public static class HospitalBuilder {

        private String name;
        private String address;
        private String website;
        private UUID uuid;

        private List<Service> servicesProvided;

        public static HospitalBuilder getNewHospitalBuilder(String name, UUID uuid) {
            return new HospitalBuilder(name, uuid);
        }

        private HospitalBuilder(String name, UUID uuid) {
            this.name = name;
            this.uuid = uuid;
        }

        public HospitalBuilder withServices(List<Service> services) {
            this.servicesProvided = new ArrayList<>(services);
            return this;
        }

        public HospitalBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public HospitalBuilder withWebsite(String website) {
            this.website = website;
            return this;
        }

        public Optional<Hospital> build() {
            if (address == null) {
                return Optional.empty();
            }
            return Optional.of(new Hospital(this));
        }

    }

}
