package ru.job4j.serialization.json;

import java.util.Arrays;

public class Order {
    private final boolean paid;
    private final int shipmentCost;
    private final String shipmentCity;
    private final Product product;
    private final String[] categories;
    private final Statuses[] statuses;
    public Order(boolean paid, int shipmentCost, String shipmentCity,
                 Product product, String[] categories, Statuses[] statuses) {
        this.paid = paid;
        this.shipmentCost = shipmentCost;
        this.shipmentCity = shipmentCity;
        this.product = product;
        this.categories = categories;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Order{"
                + "paid=" + paid
                + ", shipmentCost=" + shipmentCost
                + ", shipmentCity='" + shipmentCity + '\''
                + ", product=" + product
                + ", status=" + Arrays.toString(categories)
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }
}
