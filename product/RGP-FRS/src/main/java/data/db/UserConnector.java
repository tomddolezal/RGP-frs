package data.db;

import security.CryptoHash;

/**
 * A {@code UserConnector} separates the database logic from the server. Can be asked to add a user
 * or check if a user has logged in with a valid user and password.
 */
public interface UserConnector {

    /**
     * Check wither the user exists. If not, it is created with the provided password.
     * @param userName The username to check for.
     * @param password The password to use if the user must be created.
     */
    default void ensureHasAdmin(String userName, String password) { }

    /**
     * Add a new user to the database
     * @param userName The username of the new user.
     * @param hash The encrypted password
     * @param info The JSON object containing the user's information, including email, name, telephone, etc.
     * @param isAdmin Whether the new user should be an admin or not.
     * @return
     */
    default boolean addNewUser(String userName, CryptoHash hash, String info, boolean isAdmin) {
        return true;
    }

    default boolean addNewUser(String userName, CryptoHash hash, String info) {
        return addNewUser(userName, hash, info, false);
    }

    default boolean addNewUser(String userName, CryptoHash hash) {
        return addNewUser(userName, hash, "{}", false);
    }

    /**
     * Authenticate the user using the provided username and password.
     * @param userName The username to authenticate with.
     * @param password The password to authenticate with.
     * @return Whether it successfully authenticated.
     */
    default boolean authenticateUser(String userName, String password) {
        return true;
    }

    /**
     * Update a users info.
     * @param userName The user to update.
     * @param info the JSON object containing the user's updated information
     */
    default void updateUser (String userName, String info){}

    default boolean isUserAdmin(String userName) { return false; }
}
