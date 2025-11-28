package ciphers;

import encodings.Alphabet;

public class Test {
    public static void main(String[] args) {
        String plainText = new String("din mamam");
        String key = new String("fart");
        Alphabet alphabet = new Alphabet('a', 'z');
        Ceasar cipher = new Ceasar(2, alphabet);
        Vigenere vigenere = new Vigenere(alphabet, key);
        Atbash atbash = new Atbash(alphabet);
        System.out.println(cipher.encrypt(plainText));
        System.out.println(cipher.decrypt(cipher.encrypt(plainText)));
        System.out.println(vigenere.encrypt(plainText));
        System.out.println(vigenere.decrypt(vigenere.encrypt(plainText)));
        System.out.println(atbash.encrypt(plainText));
        System.out.println(atbash.decrypt(atbash.encrypt(plainText)));
        int i = 0;
        for (char c : alphabet) {
            System.out.printf("(%c, %d) ", c, i);
            i++;
        }

    }
}
