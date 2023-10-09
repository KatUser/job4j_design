package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Map<User, Object> userMap = new HashMap<>(16);
        Calendar birthday = Calendar.getInstance();
        User user1 = new User("Name", 0, birthday);
        User user2 = new User("Name", 0, birthday);
        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 15;
        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int bucket2 = hash2 & 15;
        userMap.put(user1, new Object());
        userMap.put(user2, new Object());
        System.out.println(userMap);
        System.out.printf("user1 - hashCode: %s, hash: %s, bucket: %s\n",
                hashCode1, hash1, bucket1);
        System.out.printf("user2 - hashCode: %s, hash: %s, bucket: %s\n",
                hashCode2, hash2, bucket2);
    }
}
