package ciphers;

import encodings.*;

/**
 * A class representing the Classical ROT13-version of the Ceasar cipher
 * 
 * @author Tim Saaranen
 * @version 2025-11-30
 */
public class Rot13 extends Ceasar {

    public Rot13(Alphabet alphabet) {
        super(13, alphabet);
    }

    public Rot13() {
        super(13);
    }

}
