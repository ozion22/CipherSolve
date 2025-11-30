package ciphers;

import java.util.HashMap;
import encodings.*;

/**
 * A class representing a simple vigenére cipher.
 * 
 * Please be aware that encryption/decryption <b>preserves</b> whitespace unless
 * using discardWhitespace
 * 
 * @author Tim Saaranen
 * @version 2025-11-30
 */
public class Vigenere implements Cipher<String, String> {

    private Alphabet alphabet;
    private final String key;
    /* key: "hallå" */
    private final HashMap<Character, Integer> alphabetMap;

    /**
     * Constructs this handler for a vigenére cipher given the ALPHABET and key
     * 
     * @param ALPHABET ALPHABET to use.
     * @param key      Key to use
     */
    public Vigenere(Alphabet ALPHABET, String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("Key must not be empty/null!");
        }
        for (char c : key.toCharArray()) {
            if (!ALPHABET.getAlphabetArrayList().contains(c)) {
                throw new IllegalArgumentException("Key contains characters not in alphabet!");
            }
        }
        this.alphabet = ALPHABET;
        this.key = new String(key);
        this.alphabetMap = new HashMap<>();
        for (int i = 0; i < ALPHABET.size(); i++) {
            alphabetMap.put(ALPHABET.get(i), i);
        }
    }

    /**
     * Makes a repeating key of length length
     * 
     * @param key    The key
     * @param length Length
     * @return The repeated Key
     */
    public static String makeRepeatingKey(String key, int length) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key must not be empty/null!");
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(key.charAt(i % key.length()));
        }
        return result.toString();
    }

    /**
     * Encrypts given key specified in constructor, !!preserves whitespace!!
     */
    @Override
    public String encrypt(String stringToEncrypt) {
        StringBuilder result = new StringBuilder();
        String repeatedKey = makeRepeatingKey(key, stringToEncrypt.length());

        for (int i = 0; i < stringToEncrypt.length(); i++) {
            char plainChar = stringToEncrypt.charAt(i);
            char keyChar = repeatedKey.charAt(i);

            if (!alphabetMap.containsKey(plainChar)) {
                result.append(plainChar); // Preserve unknown characters
                continue;
            }

            int plainIndex = alphabetMap.get(plainChar);
            int shift = alphabetMap.get(keyChar);
            int encryptedIndex = ((plainIndex + shift) + alphabet.size()) % alphabet.size();

            result.append(alphabet.get(encryptedIndex));
        }

        return result.toString();
    }

    /**
     * Encrypts a string given the key used in the constructor, removes whitespace
     * after encryption
     * 
     * @param stringToEncrypt Plaintext to encrypt
     * @return Ciphertext with whitespace removed
     */
    public String encryptDiscardWhitespace(String stringToEncrypt) {
        return encrypt(stringToEncrypt).replaceAll("\\s+", "");
    }

    @Override
    public String decrypt(String stringToDecrypt) {
        StringBuilder result = new StringBuilder();
        String repeatedKey = makeRepeatingKey(this.key, stringToDecrypt.length());
        for (int i = 0; i < stringToDecrypt.length(); i++) {
            char encryptedChar = stringToDecrypt.charAt(i);
            char keyChar = repeatedKey.charAt(i);
            if (!alphabetMap.containsKey(encryptedChar)) {
                result.append(encryptedChar);
                continue;
            }
            int cipherIndex = alphabetMap.get(encryptedChar);
            int keyIndex = alphabetMap.get(keyChar);
            int decryptedIndex = ((cipherIndex - keyIndex) + alphabet.size()) % alphabet.size();
            result.append(alphabet.get(decryptedIndex));
        }
        return result.toString();
    }

    public String decryptDiscardWhitespace(String stringToDecrypt) {
        return decrypt(stringToDecrypt).replaceAll("\\s+", "");
    }
}
