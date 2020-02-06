package data.db;

import security.CryptoHash;
import utility.FRSOptions;

import java.sql.*;

public class UserDatabase implements UserConnector {

    private boolean initialEnsureAdmin = false;

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(FRSOptions.DB_URL, FRSOptions.DB_USER, FRSOptions.DB_PASS);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.exit(-2);
        }

        return conn;
    }

    @Override
    public void ensureHasAdmin(String userName, String password) {
        String sql = "SELECT username FROM users WHERE username = ?";

        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();
            // Align the results correctly
            if(rs.next())
            {
                // If this is valid, then we don't need to create the admin.
                return;
            }

            CryptoHash hash = CryptoHash.createFromPassword(password);
            addNewUser(userName, hash, "{}", true);

        } catch (SQLException ex) {
            System.err.println("ensureHasAdmin: " + ex.getMessage());
        }
    }

    @Override
    public boolean addNewUser(String userName, CryptoHash hash, String info, boolean isAdmin) {
        String sql = "INSERT INTO Users "
                + "VALUES (?, ?, ?, ?, to_json(?))";

        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, userName);
            statement.setBytes(2, hash.getHash());
            statement.setBytes(3, hash.getSalt());
            statement.setBoolean(4, isAdmin);
            statement.setString(5, info);
            statement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.err.println("addNewUser: " + ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean authenticateUser(String userName, String password) {
        if(!initialEnsureAdmin)
        {
            ensureHasAdmin(FRSOptions.ADMIN_USER, FRSOptions.ADMIN_PASS);
            initialEnsureAdmin = true;
        }
        String sql = "SELECT passhash, passsalt FROM Users WHERE username = ?";

        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();
            // Align the results correctly
            if(!rs.next())
            {
                // If there are no entries, then the user is invalid.
                return false;
            }

            CryptoHash hash = CryptoHash.createFromHashSalt(rs.getBytes(1), rs.getBytes(2));

            if (hash.checkPassword(password)) {
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("authenticateUser: " + ex.getMessage());
        }
        return false;
    }

    @Override
    public void updateUser(String userName, String info) {
        String sql = "UPDATE Users "
                + "SET info = ? "
                + "WHERE username = ?;";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, info);
            pstmt.setString(2, userName);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public boolean isUserAdmin(String userName) {
        if(!initialEnsureAdmin)
        {
            ensureHasAdmin(FRSOptions.ADMIN_USER, FRSOptions.ADMIN_PASS);
            initialEnsureAdmin = true;
        }
        String sql = "SELECT isadmin FROM Users WHERE username = ?";
        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();
            // Align the results correctly
            rs.next();

            return rs.getBoolean(1);
        } catch (SQLException ex) {
            System.err.println("authenticateUser: " + ex.getMessage());
        }
        return false;
    }
}
