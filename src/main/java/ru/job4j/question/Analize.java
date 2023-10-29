package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        Map<Integer, String> previousMap = new HashMap<>();
        for (User user : previous) {
            previousMap.put(user.getId(), user.getName());
        }

        for (Map.Entry<Integer, String> prevEntry : previousMap.entrySet()) {
            if (current.stream().anyMatch(c -> Objects.equals(c.getId(), prevEntry.getKey())
                    && !Objects.equals(c.getName(), prevEntry.getValue()))) {
                info.setChanged(info.getChanged() + 1);
            }
            if (!current.stream().anyMatch(c -> Objects.equals(c.getId(), prevEntry.getKey()))) {
                info.setDeleted(info.getDeleted() + 1);
            }
        }




        return info;
    }
}