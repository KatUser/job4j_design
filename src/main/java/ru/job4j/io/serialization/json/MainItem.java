package ru.job4j.io.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainItem {
    public static void main(String[] args) {
        JSONObject jsonCharacteristics
                = new JSONObject("{\"sizeWidth\":20.05, \"sizeLength\":30.15, \"hasUSD\":true}");

        List<String> listComposition = new ArrayList<>();
        listComposition.add("plastics");
        listComposition.add("leather");
        JSONArray jsonComposition = new JSONArray(listComposition);

        final Item item = new Item(true, 0, "Item description",
                new Characteristics(10.09, 14.87, false), "metal", "paper");
        JSONObject jsonItemObject = new JSONObject();
        jsonItemObject.put("onSale", item.isOnSale());
        jsonItemObject.put("amountLeft", item.getAmountLeft());
        jsonItemObject.put("description", item.getDescription());
        jsonItemObject.put("characteristics", jsonCharacteristics);
        jsonItemObject.put("composition", jsonComposition);

        System.out.println(jsonItemObject);
        System.out.println(new JSONObject(item));
    }
}
