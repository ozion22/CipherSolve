public class Test {
    public static void main(String[] args) {
        Alphabet alphabet = new Alphabet('a', 'z');
        Ceasar cipher = new Ceasar(2, alphabet);
        Vigenere vigenere = new Vigenere(alphabet, "Vadelosenordet");
        System.out.println(cipher.encrypt("din mammammama ligger pa pizza"));
        System.out.println(cipher.decrypt(cipher.encrypt("din mammammama ligger pa pizza")));
        System.out.println(vigenere.encrypt("din mammammama ligger pa pizza"));
        System.out.println(vigenere.decrypt(vigenere.encrypt("din mammammama ligger pa pizza")));

    }
}
