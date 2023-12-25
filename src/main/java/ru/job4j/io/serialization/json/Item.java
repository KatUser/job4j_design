package ru.job4j.io.serialization.json;

import java.util.Arrays;

public class Item {
    private final boolean onSale;
    private final int amountLeft;
    private final String description;
    private final Characteristics characteristics;
    private final String[] composition;
    public Item(boolean onSale, int amountLeft,
                String description, Characteristics characteristics, String... composition) {
        this.onSale = onSale;
        this.amountLeft = amountLeft;
        this.description = description;
        this.characteristics = characteristics;
        this.composition = composition;
    }
    public boolean isOnSale() {
        return onSale;
    }
    public int getAmountLeft() {
        return amountLeft;
    }
    public String getDescription() {
        return description;
    }
    public Characteristics getCharacteristics() {
        return characteristics;
    }
    public String[] getComposition() {
        return composition;
    }
    @Override
    public String toString() {
        return "Item{"
                + "onSale=" + onSale
                + ", amountLeft=" + amountLeft
                + ", description='" + description + '\''
                + ", characteristics=" + characteristics
                + ", composition=" + Arrays.toString(composition)
                + '}';
    }
}
