package ru.job4j.io.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainOrderProduct {
    public static void main(String[] args) {
        final Order order = new Order(false, 200, "Tomsk",
        new Product("Wireless Mouse", 1999),
                new String[] {"accessory", "gaming accessory", "on sale"},
                new Statuses[]{Statuses.DRAFT, Statuses.AWAITING_PAYMENT});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(order));

        final String orderJson =
                "{"
                        + "\"paid\":true,"
                        + "\"shipmentCost\":99,"
                        + "\"shipmentCity\":\"Moscow\","
                        + "\"product\":"
                        + "{"
                        + "\"name\":\"Wireless Keyboard\","
                        + "\"productPrice\":5000"
                        + "},"
                        + "\"categories\":"
                        + "[\"on sale\",\"made in china\"],"
                        + "\"statuses\":"
                        + "[\"PAYED\",\"FINALIZED\"]"
                        + "}";
        final Order orderFromJson = gson.fromJson(orderJson, Order.class);
        System.out.println(orderFromJson);
    }
}
