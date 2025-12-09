package utilities;

import encodings.*;

public class BitInterpreter {

    public static String interpret(Alphabet alphabet, String input, NumberOfBits scheme) {
        int chunkSize = scheme.getBits();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i += chunkSize) {
            String chunk = input.substring(i, Math.min(i + chunkSize, input.length()));

            // Pad incomplete chunks with zeros
            if (chunk.length() < chunkSize) {
                chunk = String.format("%-" + chunkSize + "s", chunk).replace(' ', '0');
            }

            try {
                int value = Integer.parseInt(chunk, 2);

                if (value >= 0 && value < alphabet.size()) {
                    result.append(alphabet.get(value));
                } else {
                    // Skip or log invalid values
                    System.err.println("Value " + value + " out of range for alphabet");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid binary chunk: " + chunk);
            }
        }
        return result.toString();
    }
}
