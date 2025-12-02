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

    @Override
    public String encrypt(String plainText) {
        Boolean goingDown = true;
        int currentRail = 0;
        if (plainText == null) {
            throw new IllegalArgumentException("plainText cannot be Null!");
        }
        if (plainText.isEmpty() || plainText.isBlank()) {
            return new String("");
        }
        if (rails == 1) {
            return plainText;
        }
        ArrayList<StringBuilder> splitRails = new ArrayList<>();
        for (int i = 0; i < rails; i++) {
            splitRails.add(new StringBuilder());
        }

        for (char c : plainText.toCharArray()) {
            splitRails.get(currentRail).append(c);
            if (currentRail == 0) {
                goingDown = true;
            } else if (currentRail == rails - 1) {
                goingDown = false;
            }
            currentRail += (goingDown) ? 1 : -1;
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : splitRails) {
            result.append(sb);
        }

        return result.toString();

    }

    @Override
    public String decrypt(String cipherText) {
        if (cipherText == null) {
            throw new IllegalArgumentException("cipherText cannot be Null!");
        }
        if (cipherText.isEmpty() || cipherText.isBlank()) {
            return "";
        }
        if (rails == 1) {
            return cipherText;
        }

        // Step 1: Build rail pattern for each position
        ArrayList<Integer> pattern = new ArrayList<>(cipherText.length());
        boolean goingDown = true;
        int curRail = 0;
        for (int i = 0; i < cipherText.length(); i++) {
            pattern.add(curRail);
            if (curRail == 0) {
                goingDown = true;
            } else if (curRail == rails - 1) {
                goingDown = false;
            }
            curRail += goingDown ? 1 : -1;
        }

        // Step 2: Count chars per rail
        int[] railCounts = new int[rails];
        for (int r : pattern) {
            railCounts[r]++;
        }

        // Step 3: Slice ciphertext into rail segments
        ArrayList<String> railSlices = new ArrayList<>(rails);
        int idx = 0;
        for (int r = 0; r < rails; r++) {
            railSlices.add(cipherText.substring(idx, idx + railCounts[r]));
            idx += railCounts[r];
        }

        // Step 4: Reconstruct plaintext by consuming from each rail slice
        int[] railPositions = new int[rails];
        StringBuilder plainText = new StringBuilder(cipherText.length());
        for (int r : pattern) {
            plainText.append(railSlices.get(r).charAt(railPositions[r]));
            railPositions[r]++;
        }

        return plainText.toString();
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