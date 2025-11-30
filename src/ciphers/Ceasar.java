package ciphers;

import java.util.HashMap;
import encodings.Alphabet;

/**
 * A class representing a simple ceasar cipher.
 * 
 * @author Tim Saaranen
 * @version 2025-11-30
 * 
 */
public class Ceasar implements Cipher<String, String> {
    private final int shift;
    private Alphabet alphabet;

    /**
     * Initialises a Ceasar chipher with standard <b>lowercase</b> english alphabet
     * 
     * @param shift The shift to use
     */
    public Ceasar(int shift) {
        this.shift = ((shift % 26) + 26) % 26;
        this.alphabet = new Alphabet();
    }

    /**
     * Initialises a Ceasar cipher with Custom alphabet
     * 
     * @param shift    The shift to use
     * @param ALPHABET Alphabet to use
     */
    public Ceasar(int shift, Alphabet ALPHABET) {
        this.alphabet = new Alphabet(ALPHABET.getAlphabetArrayList());
        this.shift = ((shift % ALPHABET.size()) + ALPHABET.size()) % ALPHABET.size();
        /* Double modulo to deal with negative shifts */
    }

    @Override
    public String encrypt(String stringToEncrypt) {
        HashMap<Character, Character> encHashMap = new HashMap<>();
        for (Character character : alphabet.getAlphabetArrayList()) {
            int shiftedIndex = (((alphabet.getAlphabetArrayList().indexOf(character) + shift) % alphabet.size())
                    + alphabet.size())
                    % alphabet.size();
            encHashMap.put(character, alphabet.get(shiftedIndex));
        }
        StringBuilder result = new StringBuilder();
        for (Character character : stringToEncrypt.toCharArray()) {
            if (alphabet.getAlphabetArrayList().contains(character)) {
                result.append(encHashMap.get(character));
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    @Override
    public String decrypt(String stringTodecrypt) {
        HashMap<Character, Character> decHashMap = new HashMap<>();
        for (Character character : alphabet.getAlphabetArrayList()) {
            int shiftedIndex = (((alphabet.getAlphabetArrayList().indexOf(character) - shift) % alphabet.size())
                    + alphabet.size())
                    % alphabet.size();
            decHashMap.put(character, alphabet.get(shiftedIndex));
        }
        StringBuilder result = new StringBuilder();
        for (Character character : stringTodecrypt.toCharArray()) {
            if (alphabet.getAlphabetArrayList().contains(character)) {
                result.append(decHashMap.get(character));
            } else {
                result.append(character);
            }
        }
        return result.toString();

    }
}