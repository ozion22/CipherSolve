package encodings;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A class representing alphabets used in encoding/decoding within CipherSolve.
 * 
 * @author Tim Saaranen
 * @version 2025-11-30
 */
public class Alphabet implements Iterable<Character> {
    private final List<Character> ALPHABET;

    private final CustomBinMap binMap;

    /**
     * Constructs an Alphabet handler given the ALPHABET
     * 
     * @param ALPHABET ALPHABET to use.
     */
    public Alphabet(ArrayList<Character> ALPHABET) {
        this.ALPHABET = new ArrayList<>(ALPHABET);
        binMap = new CustomBinMap(ALPHABET);
    }

    /**
     * Constructs an Alphabet handler with standard lowercase english alphabet
     */
    public Alphabet() {
        this.ALPHABET = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            this.ALPHABET.add(c);
        }
        binMap = new CustomBinMap(ALPHABET);
    }

    public Alphabet(String alphabetString) {
        this.ALPHABET = new ArrayList<>();
        for (char c : alphabetString.toCharArray()) {
            this.ALPHABET.add(c);
        }
        binMap = new CustomBinMap(ALPHABET);
    }

    public Alphabet(char from, char to) {
        this.ALPHABET = new ArrayList<>();
        for (char c = from; c <= to; c++) {
            this.ALPHABET.add(c);
        }
        binMap = new CustomBinMap(ALPHABET);
    }

    /**
     * Constructs an Alphabet handler given the ASCII values from and to
     * 
     * @param from ASCII value to start from
     * @param to   ASCII value to end at
     */
    public Alphabet(int from, int to) {
        this.ALPHABET = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            this.ALPHABET.add((char) i);
        }
        binMap = new CustomBinMap(ALPHABET);
    }

    public static Alphabet reverse(Alphabet alphabetToReverse) {
        ArrayList<Character> charList = new ArrayList<>(alphabetToReverse.getAlphabetArrayList());
        // Sliding-swap
        for (int i = 0; i < (charList.size() / 2); i++) {
            char char1 = charList.get(i);
            char char2 = charList.get((charList.size() - 1) - i);
            charList.set(i, char2);
            charList.set((charList.size() - 1) - i, char1);
        }
        return new Alphabet(charList);

    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Character character : ALPHABET) {
            result.append(character);
        }
        return result.toString();
    }

    public boolean contains(char c) {
        return binMap.contains(c);
    }

    public int IndexOf(char c) {
        return binMap.getIndex(c);
    }

    public CustomBinMap getBinMap() {
        return binMap;
    }

    public ArrayList<Character> getAlphabetArrayList() {
        return new ArrayList<>(ALPHABET);
    }

    public int size() {
        return ALPHABET.size();
    }

    public char get(int index) {
        return ALPHABET.get(index);
    }

    public Iterator<Character> iterator() {
        return this.getAlphabetArrayList().iterator();
    }

}