public class Test {
    public static void main(String[] args) {
        Alphabet alphabet = new Alphabet('a', 'z');
        Ceasar cipher = new Ceasar(2, alphabet);
        Vigenere vigenere = new Vigenere(alphabet, "buffzy");
        System.out.println(cipher.encrypt("mullis"));
        System.out.println(cipher.decrypt(cipher.encrypt("mullis")));
        System.out.println(vigenere.encrypt("mullis"));
        System.out.println(vigenere.decrypt(vigenere.encrypt("mullis")));
        int i = 0;
        for (char c : alphabet) {
            System.out.printf("(%c, %d) ", c, i);
            i++;
        }

    }
}
