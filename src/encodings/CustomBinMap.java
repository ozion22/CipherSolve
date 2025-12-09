package encodings;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.io.Serializable;

/**
 * @author Tim Saaranen
 * @version 2025-12-02
 *          A class representing a custom mapping of integer -> char
 *          Useful for example in the Bacon cipher, or when using custom / odd
 *          encodings
 */
public class CustomBinMap implements Iterable<Character>, Serializable {

    private final List<Character> alphabet;
    private final Map<Character, Integer> revIndex;

    /**
     * Construct a new binmap given a list of chars.
     * 
     * @param alphabet The alphabet to use
     */
    public CustomBinMap(List<Character> alphabet) {
        if (alphabet == null || alphabet.isEmpty())
            throw new IllegalArgumentException("Alphabet cannot be null or empty");

        this.alphabet = List.copyOf(alphabet);
        this.revIndex = new HashMap<>(alphabet.size());

        for (int i = 0; i < this.alphabet.size(); i++) {
            char c = this.alphabet.get(i);
            if (revIndex.put(c, i) != null)
                throw new IllegalArgumentException("Duplicate char: " + c);
        }
    }

    public CustomBinMap(CustomBinMap binMapToCopy) {
        this.alphabet = binMapToCopy.alphabet;
        this.revIndex = binMapToCopy.revIndex;
    }

    /**
     * Construct a binmap from an already-completed map
     * 
     * @param map The map to use
     */
    public CustomBinMap(Map<Integer, Character> map) {
        if (map == null || map.isEmpty())
            throw new IllegalArgumentException("Map cannot be null or empty.");

        int max = Collections.max(map.keySet());
        List<Character> list = new ArrayList<>(max + 1);

        for (int i = 0; i <= max; i++) {
            if (!map.containsKey(i))
                throw new IllegalArgumentException("Missing index: " + i);

            list.add(map.get(i));
        }

        this.alphabet = List.copyOf(list);
        this.revIndex = new HashMap<>(alphabet.size());

        for (int i = 0; i < alphabet.size(); i++) {
            char c = alphabet.get(i);
            if (revIndex.put(c, i) != null)
                throw new IllegalArgumentException("Duplicate character: " + c);
        }
    }

    /**
     * Get index of char c
     * 
     * @param c Char to lookup
     * @return The index
     * @throws IllegalArgumentException if not in alphabet
     */
    public int indexOf(char c) {
        Integer idx = revIndex.get(c);
        if (idx == null)
            throw new IllegalArgumentException("Character not in alphabet: " + c);
        return idx;
    }

    public List<Character> asList() {
        return Collections.unmodifiableList(alphabet);
    }

    /**
     * Get char at index <b>i</b>
     * 
     * @param i index
     * @return The char
     */
    public char get(int i) {
        try {
            return alphabet.get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Index not in alphabet");
        }
    }

    /**
     * Does the binmap contain char c
     * 
     * @param c The char
     * @return isIn
     */
    public boolean contains(char c) {
        return revIndex.containsKey(c);
    }

    public int size() {
        return alphabet.size();
    }

    @Override
    public Iterator<Character> iterator() {
        return alphabet.iterator();
    }

    @Override
    public String toString() {
        return alphabet.toString();
    }
}