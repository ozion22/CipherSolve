package ciphers;

import java.util.HashMap;

import encodings.Alphabet;

/**
 * A class representing the classical atbash (reversed alphabet) cipher
 * 
 * @author Tim Saaranen
 * @version 2025-11-30
 */
public class Atbash implements Cipher<String, String> {
    private Alphabet alphabet;
    private HashMap<Character, Character> encDecHashMap; // Enc: plaintext -> cipher, dec: cipher -> plaintext

    public Atbash(Alphabet ALPHABET) {
        this.alphabet = new Alphabet(ALPHABET.getAlphabetArrayList());
        Alphabet reversedAlphabet = Alphabet.reverse(alphabet);
        this.encDecHashMap = new HashMap<>();
        int i = 0;
        for (char c : alphabet) {
            encDecHashMap.put(c, reversedAlphabet.get(i));
            i++;
        }

    }

    public String encrypt(String stringToEncrypt) {
        StringBuilder result = new StringBuilder();

        for (char c : stringToEncrypt.toCharArray()) {
            if (encDecHashMap.containsKey(c)) {
                result.append(encDecHashMap.get(c));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public String decrypt(String stringToDecrypt) {
        return encrypt(stringToDecrypt); // Atbash is symmetric ;3

    }
}
