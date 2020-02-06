package data.db;

import java.sql.*;
import java.util.UUID;

import static utility.FRSOptions.*;

public class HospitalDatabase implements HospitalConnector {

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    @Override
    public void addHospital(UUID id, String info) {
        String sql = "INSERT INTO hospital(name, info) " + "VALUES(?,?)";

        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, id.toString());
            statement.setString(2, info);
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void updateHospital(UUID uuid, String info) {
        String sql = "UPDATE hospital " + "SET info = ? " + "WHERE name = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String name = uuid.toString();
            pstmt.setString(1, info);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
            System.out.println("updated hospital (" + name + ") information.");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String getHospitals() {
        String sql = "SELECT * " + "FROM Hospital ";

        String info = "";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            info = rs.getString("info");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return info;
    }
}
