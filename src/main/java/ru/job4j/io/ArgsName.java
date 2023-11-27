package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String res = values.get("-" + key);
        if (res == null) {
            throw new IllegalArgumentException();
        }
        return res;
    }

    private void parse(String[] args) {
        values.putAll(Arrays.stream(args)
                        .map(String::trim)
                        .map(str -> str.split("=", 2))
                .collect(Collectors.toMap(e -> e[0], e -> e[1])));
    }

    public static ArgsName of(String[] args) {
        checkArgsValidity(args);
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void checkArgsValidity(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        if (!args[0].startsWith("-") || !args[1].startsWith("-")) {
            throw new IllegalArgumentException("No hyphen");
        }
        if (!args[0].contains("=") || !args[1].contains("=")) {
            throw new IllegalArgumentException("No equal");
        }
        if (args[0].startsWith("-=") || args[1].startsWith("-=")) {
            throw new IllegalArgumentException("This argument has no key");
        }
    }


    public static void main(String[] args) {
        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("encoding"));
    }
}
