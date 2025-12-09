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
    private final CustomBinMap binMap;

    /**
     * Constructs an Alphabet handler given the alphabet
     * 
     * @param alphabet alphabet to use.
     */
    public Alphabet(ArrayList<Character> alphabet) {
        binMap = new CustomBinMap(alphabet);
    }

    public Alphabet(Alphabet alphabetToCopy) {
        this.binMap = new CustomBinMap(alphabetToCopy.binMap);
    }

    /**
     * Constructs an Alphabet handler with standard lowercase english alphabet
     */
    public Alphabet() {
        ArrayList<Character> alphabet = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            alphabet.add(c);
        }
        binMap = new CustomBinMap(alphabet);
    }

    public Alphabet(String alphabetString) {
        ArrayList<Character> alphabet = new ArrayList<>();
        for (char c : alphabetString.toCharArray()) {
            alphabet.add(c);
        }
        binMap = new CustomBinMap(alphabet);
    }

    public Alphabet(char from, char to) {
        ArrayList<Character> alphabet = new ArrayList<>();
        for (char c = from; c <= to; c++) {
            alphabet.add(c);
        }
        binMap = new CustomBinMap(alphabet);
    }

    /**
     * Constructs an Alphabet handler given the ASCII values from and to
     * 
     * @param from ASCII value to start from
     * @param to   ASCII value to end at
     */
    public Alphabet(int from, int to) {
        ArrayList<Character> alphabet = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            alphabet.add((char) i);
        }
        binMap = new CustomBinMap(alphabet);
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
        for (Character character : getAlphabetArrayList()) {
            result.append(character);
        }
        return result.toString();
    }

    public boolean contains(char c) {
        return binMap.contains(c);
    }

    public int indexOf(char c) {
        return binMap.indexOf(c);
    }

    public CustomBinMap getBinMap() {
        return binMap;
    }

    /**
     * Returns an unmodifiable List of chars in the alphabet
     * (see CustomBinMap)
     * 
     * @return The list
     */
    public List<Character> getAlphabetArrayList() {
        return binMap.asList();
    }

    public int size() {
        return binMap.size();
    }

    public char get(int index) {
        return binMap.get(index);
    }

    public Iterator<Character> iterator() {
        return this.getAlphabetArrayList().iterator();
    }

}