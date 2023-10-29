package ru.job4j.question;

import java.util.*;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        HashMap<Integer, String> previousMap = (HashMap<Integer, String>) previous.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        HashMap<Integer, String> currentMap = (HashMap<Integer, String>) current.stream()
                .collect(Collectors.toMap(User::getId, User::getName));

        for (Map.Entry<Integer, String> currEntry : currentMap.entrySet()) {
            if (previousMap.containsKey(currEntry.getKey())
                    && !Objects.equals(currEntry.getValue(), previousMap.get(currEntry.getKey()))) {
                info.setChanged(info.getChanged() + 1);
            }
            if (!previousMap.containsKey(currEntry.getKey())) {
                info.setAdded(info.getAdded() + 1);
            }
        }
        for (Map.Entry<Integer, String> prevEntry : previousMap.entrySet()) {
            if (!currentMap.containsKey(prevEntry.getKey())) {
                info.setDeleted(info.getDeleted() + 1);
            }
        }
        return info;
    }
}