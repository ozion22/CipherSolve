package utilities;

public enum NumberOfBits {
    Binary(1), Trit(2), Nibble(4), Five(5), Six(6), ASCII(7), Byte(8);

    private final int bits;

    NumberOfBits(int bits) {
        this.bits = bits;
    }

    public int getBits() {
        return bits;
    }
}