package ru.job4j.io.serialization.json;

public class Characteristics {
    private final double sizeWidth;
    private final double sizeLength;
    private final boolean hasUSB;
    public Characteristics(double sizeWidth, double sizeLength, boolean hasUSB) {
        this.sizeWidth = sizeWidth;
        this.sizeLength = sizeLength;
        this.hasUSB = hasUSB;
    }
    public double getSizeWidth() {
        return sizeWidth;
    }
    public double getSizeLength() {
        return sizeLength;
    }
    public boolean isHasUSB() {
        return hasUSB;
    }
    @Override
    public String toString() {
        return "Characteristics{"
                + "sizeWidth=" + sizeWidth
                + ", sizeLength=" + sizeLength
                + ", hasUSB=" + hasUSB
                + '}';
    }
}
