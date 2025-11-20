public interface Cipher {
    /**
     * Encrypts a string given the parameters of the constructor, see individual
     * classes for more info
     * 
     * @param plaintext Plaintext to encrypt
     * @return The resulting ciphertext
     */
    public String encrypt(String plaintext);

    /**
     * Decrypts a string given the parameters of the constructor, see individual
     * classes for more info
     * 
     * @param ciphertext Ciphertext to decrypt
     * @return The resulting plaintext
     */
    public String decrypt(String ciphertext);
}