import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Vigenere implements Cipher {

    private final ArrayList<Character> ALPHABET;
    private final String key;
    private final HashMap<Character, Integer> alphabetMap;

    public Vigenere(ArrayList<Character> ALPHABET, String key) {
        if (key.length() == 0 || key == null) {
            throw new IllegalArgumentException("Key must not be empty/null!");
        }
        this.ALPHABET = ALPHABET;
        this.key = key;
        this.alphabetMap = new HashMap<>();
        for (int i = 0; i < ALPHABET.size(); i++) {
            alphabetMap.put(ALPHABET.get(i), i);
        }
    }

    private String makeRepeatingKey(String key, int length) {
        if (key.isEmpty() || key == null) {
            throw new StringIndexOutOfBoundsException("Key must not be empty/null!");
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(key.charAt(i % key.length()));
        }
        return result.toString();
    }

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
            int encryptedIndex = (plainIndex + shift) % ALPHABET.size();

            result.append(ALPHABET.get(encryptedIndex));
        }

        return result.toString();
    }

    public String decrypt(String stringToDecrypt) {

    }

}
