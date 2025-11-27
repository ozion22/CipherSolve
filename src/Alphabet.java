import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;

public class Alphabet implements Iterable<Character> {
    private final ArrayList<Character> ALPHABET;

    /**
     * Constructs an Alphabet handler given the ALPHABET
     * 
     * @param ALPHABET ALPHABET to use.
     */
    public Alphabet(ArrayList<Character> ALPHABET) {
        this.ALPHABET = new ArrayList<>(ALPHABET);
    }

    /**
     * Constructs an Alphabet handler with standard lowercase english alphabet
     */
    public Alphabet() {
        this.ALPHABET = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            this.ALPHABET.add(c);
        }
    }

    public Alphabet(String alphabetString) {
        this.ALPHABET = new ArrayList<>();
        for (char c : alphabetString.toCharArray()) {
            this.ALPHABET.add(c);
        }
    }

    public Alphabet(char from, char to) {
        this.ALPHABET = new ArrayList<>();
        for (char c = from; c <= to; c++) {
            this.ALPHABET.add(c);
        }
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
    }

    /**
     * Constructs an Alphabet handler given the Charset
     * 
     * @param charset Charset to use
     */
    public Alphabet(Charset charset) {
        this.ALPHABET = new ArrayList<>();
        for (byte b : charset.name().getBytes()) {
            this.ALPHABET.add((char) b);
        }

    }

    public static Alphabet reverse(Alphabet alphabetToReverse) {
        return new Alphabet((ArrayList<Character>) alphabetToReverse.getAlphabetArrayList().reversed());

    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Character character : ALPHABET) {
            result.append(character);
        }
        return result.toString();
    }

    public boolean contains(char c) {
        return ALPHABET.contains(c);
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