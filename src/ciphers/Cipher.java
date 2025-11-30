package ciphers;

/**
 * Generic cipher interface with parameterised plaintext and ciphertext types.
 *
 * @param <P> plaintext type
 * @param <C> ciphertext type
 * @author Tim Saaranen
 * @version 2025-11-30
 */
public interface Cipher<P, C> {

    /**
     * Encrypts a plaintext value to a ciphertext value.
     *
     * @param plaintext Plaintext to encrypt
     * @return The resulting ciphertext
     */
    C encrypt(P plaintext);

    /**
     * Decrypts a ciphertext value to a plaintext value.
     *
     * @param ciphertext Ciphertext to decrypt
     * @return The resulting plaintext
     */
    P decrypt(C ciphertext);

}