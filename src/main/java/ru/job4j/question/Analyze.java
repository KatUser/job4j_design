package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analyze {
    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int removed;
        Map<Integer, String> previousUsers = new HashMap<>();
        for (User previousUser : previous) {
            previousUsers.put(previousUser.getId(), previousUser.getName());
        }
        for (User currentUser : current) {
            int currUserId = currentUser.getId();
            if (!previousUsers.containsKey(currUserId)) {
                added++;
            } else if (!previousUsers.containsValue(currentUser.getName())) {
                changed++;
            }
            previousUsers.remove(currUserId);
        }
        removed = previousUsers.size();
        return new Info(added, changed, removed);
    }
}