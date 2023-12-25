package ru.job4j.io.serialization.json;

public class Product {
    private final String name;
    private final int productPrice;
    public Product(String name, int productPrice) {
        this.name = name;
        this.productPrice = productPrice;
    }
    @Override
    public String toString() {
        return "Product{"
                + "name='" + name + '\''
                + ", productPrice=" + productPrice
                + '}';
    }
}
