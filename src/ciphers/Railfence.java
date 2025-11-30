package ciphers;

import java.util.ArrayList;

/**
 * A class representing the Railfence transpostion cipher
 * 
 * @author Tim Saaranen
 * @version 2025-11-30
 */
public class Railfence implements Cipher<String, String> {
    private final int rails;
    private Direction direction;

    private enum Direction {
        Up, Down
    };

    @Override
    public String encrypt(String plainText) {
        if (plainText == null) {
            throw new IllegalArgumentException("plainText cannot be Null!");
        }
        if (plainText.isEmpty() || plainText.isBlank()) {
            return new String("");
        }
        this.direction = Direction.Down;
        int currentRail = 0;
        if (rails == 1 || plainText.isEmpty()) {
            return plainText;
        }
        ArrayList<StringBuilder> splitRails = new ArrayList<>();
        for (int i = 0; i < rails; i++) {
            splitRails.add(new StringBuilder());
        }

        for (char c : plainText.toCharArray()) {
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

    @Override
    public String decrypt(String cipherText) {

    }

    /**
     * Creates a new Railfence handler given rails
     * 
     * @param rails Number of rails (>=1)
     */
    public Railfence(int rails) {
        if (rails < 1) {
            throw new IllegalArgumentException("Rails cannot be < 0 or null!");
        }
        this.rails = rails;
    }

}