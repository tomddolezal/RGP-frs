package security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Objects;

/**
 * The hash of a password with an associated salt.
 */
public class CryptoHash {

    private final byte[] hash;
    private final byte[] salt;

    /**
     * Given a password, creates the necessary information to store the password securely.
     * <p>
     * This is based off of the following source:
     * https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
     *
     * @param password The password to securely store.
     * @return A hash with a salt.
     */
    public static CryptoHash createFromPassword(String password) {
        byte[] salt = generateSalt();
        byte[] hash = hashPassword(password, salt);

        return new CryptoHash(hash, salt);
    }

    /**
     * Simply create a CryptoHash from a previously created hash+salt combo.
     *
     * @param hash The hash of the password.
     * @param salt The salt used to secure the hash.
     * @return A hash with a salt.
     */
    public static CryptoHash createFromHashSalt(byte[] hash, byte[] salt) {
        return new CryptoHash(hash, salt);
    }

    private CryptoHash(byte[] hash, byte[] salt) {
        this.hash = hash;
        this.salt = salt;
    }

    /**
     * Generates a secure salt.
     *
     * @return A 32-byte salt.
     */
    private static byte[] generateSalt() {
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[32];
            sr.nextBytes(salt);
            return salt;
        } catch (NoSuchAlgorithmException ex) {
            System.err.println(ex.getMessage());
            System.exit(-1);
            // Do this to prevent warnings about salts being null
            return new byte[1];
        }
    }

    /**
     * Hashes the password with a given salt
     *
     * @param password The password to be hashed.
     * @param salt     The salt to be used.
     * @return A string of the hashed password.
     */
    private static byte[] hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            return md.digest(password.getBytes());
        } catch (NoSuchAlgorithmException ex) {
            System.err.println(ex.getMessage());
            System.exit(-1);
            return null;
        }
    }


    /**
     * Compares the input password to the stored hash.
     *
     * @param inputPassword The password that we want to test if it matches.
     * @return Whether this is the correct password
     */
    public boolean checkPassword(String inputPassword) {
        byte[] testHash = hashPassword(inputPassword, salt);
        return Arrays.equals(hash, testHash);
    }


    public byte[] getHash() {
        return hash;
    }

    public byte[] getSalt() {
        return salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CryptoHash that = (CryptoHash) o;
        return Arrays.equals(hash, that.hash) &&
                Arrays.equals(salt, that.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash, salt);
    }
}