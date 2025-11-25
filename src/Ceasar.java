import java.util.HashMap;

/**
 * A class representing a simple ceasar cipher.
 */
public class Ceasar extends Cipher {
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
        this.alphabet = new Alphabet(ALPHABET.getALPHABET());
        this.shift = ((shift % ALPHABET.size()) + ALPHABET.size()) % ALPHABET.size();
        /* Double modulo to deal with negative shifts */
    }

    public String encrypt(String stringToEncrypt) {
        HashMap<Character, Character> encHashMap = new HashMap<>();
        for (Character character : alphabet.getALPHABET()) {
            int shiftedIndex = (((alphabet.getALPHABET().indexOf(character) + shift) % alphabet.size())
                    + alphabet.size())
                    % alphabet.size();
            encHashMap.put(character, alphabet.get(shiftedIndex));
        }
        StringBuilder result = new StringBuilder();
        for (Character character : stringToEncrypt.toCharArray()) {
            if (alphabet.getALPHABET().contains(character)) {
                result.append(encHashMap.get(character));
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    public String decrypt(String stringTodecrypt) {
        HashMap<Character, Character> decHashMap = new HashMap<>();
        for (Character character : alphabet.getALPHABET()) {
            int shiftedIndex = (((alphabet.getALPHABET().indexOf(character) - shift) % alphabet.size())
                    + alphabet.size())
                    % alphabet.size();
            decHashMap.put(character, alphabet.get(shiftedIndex));
        }
        StringBuilder result = new StringBuilder();
        for (Character character : stringTodecrypt.toCharArray()) {
            if (alphabet.getALPHABET().contains(character)) {
                result.append(decHashMap.get(character));
            } else {
                result.append(character);
            }
        }
        return result.toString();

    }
}