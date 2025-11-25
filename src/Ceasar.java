import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Ceasar extends Cipher {
    private final int shift;
    private final List<Character> ALPHABET;

    /**
     * Initialises a Ceasar chipher with standard <b>lowercase</b> english alphabet
     * 
     * @param shift The shift to use
     */
    public Ceasar(int shift) {
        this.shift = ((shift % 26) + 26) % 26;
        this.ALPHABET = List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
    }

    /**
     * Initialises a Ceasar cipher with Custom alphabet
     * 
     * @param shift    The shift to use
     * @param ALPHABET Alphabet to use
     */
    public Ceasar(int shift, ArrayList<Character> ALPHABET) {
        this.ALPHABET = ALPHABET;
        this.shift = ((shift % ALPHABET.size()) + ALPHABET.size()) % ALPHABET.size();
        /* Double modulo to deal with negative shifts */
    }

    public String encrypt(String stringToEncrypt) {
        HashMap<Character, Character> encHashMap = new HashMap<>();
        for (Character character : ALPHABET) {
            int shiftedIndex = (((ALPHABET.indexOf(character) + shift) % ALPHABET.size()) + ALPHABET.size())
                    % ALPHABET.size();
            encHashMap.put(character, ALPHABET.get(shiftedIndex));
        }
        StringBuilder result = new StringBuilder();
        for (Character character : stringToEncrypt.toCharArray()) {
            if (ALPHABET.contains(character)) {
                result.append(encHashMap.get(character));
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    public String decrypt(String stringTodecrypt) {
        HashMap<Character, Character> decHashMap = new HashMap<>();
        for (Character character : ALPHABET) {
            int shiftedIndex = (((ALPHABET.indexOf(character) - shift) % ALPHABET.size()) + ALPHABET.size())
                    % ALPHABET.size();
            decHashMap.put(character, ALPHABET.get(shiftedIndex));
        }
        StringBuilder result = new StringBuilder();
        for (Character character : stringTodecrypt.toCharArray()) {
            if (ALPHABET.contains(character)) {
                result.append(decHashMap.get(character));
            } else {
                result.append(character);
            }
        }
        return result.toString();

    }
}