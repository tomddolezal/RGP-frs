package security;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 * A {@code TokenAuthenticator} is used to evaluate the state of users for
 * the {@link servlet.AuthenticatedUserFilter}.
 */
@SuppressWarnings("UtilityClass")
public class TokenAuthenticator {

    private Map<String, String> storedTokens = new HashMap<>();

    /**
     * Generates a secure token.
     *
     * @return A 32-byte token.
     */
    private static byte[] generateToken() {
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            byte[] token = new byte[32];
            sr.nextBytes(token);
            return token;
        } catch (NoSuchAlgorithmException ex) {
            System.err.println(ex.getMessage());
            System.exit(-1);
            return null;
        }
    }

    /**
     * Creates a token for the specified user. Assumes that user has already been authenticated.
     * @param user Creates a value to persist the state of the user
     * @return The token generated.
     */
    public String createTokenFor(String user) {
        String token = (new HexBinaryAdapter()).marshal(generateToken());
        storedTokens.put(token, user);
        return token;
    }

    /**
     * Deletes the token so that it is no longer valid (no longer allows login).
     * @param token The token to delete.
     */
    public void deleteToken(String token)
    {
        storedTokens.remove(token);
    }

    /**
     * @param user The user to check.
     * @param token The token to check.
     * @return True if the token is a valid token
     */
    public boolean verify(String user, String token) {
        return user.equals(storedTokens.getOrDefault(token, null));
    }
}