package ciphers;

import java.util.ArrayList;

/**
 * A class representing the Railfence transpostion cipher
 */
public class Railfence implements Cipher<String, String> {
    private final int rails;
    private Direction direction;

    private enum Direction {
        Up, Down
    };

    public String encrypt(String plaintext) {
        this.direction = Direction.Down;
        int currentRail = 0;
        if (rails == 1 || plaintext.isEmpty()) {
            return plaintext;
        }
        ArrayList<StringBuilder> splitRails = new ArrayList<>();
        for (int i = 0; i < rails; i++) {
            splitRails.add(new StringBuilder());
        }

        for (char c : plaintext.toCharArray()) {
            splitRails.get(currentRail).append(c);
            if (currentRail == 0) {
                direction = Direction.Down;
            } else if (currentRail == rails - 1) {
                direction = Direction.Up;
            }
            currentRail += (direction == Direction.Down) ? 1 : -1;
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : splitRails) {
            result.append(sb);
        }

        return result.toString();

    }

    public String decrypt(String Ciphertext) {

    }

    public Railfence(int rails) {
        this.rails = rails;
    }

}