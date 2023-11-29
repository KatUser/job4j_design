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
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
        return res;
    }

    private void parse(String[] arguments) {
        values.putAll(Arrays.stream(arguments)
                        .map(String::trim)
                        .map(str -> str.split("=", 2))
                .collect(Collectors.toMap(e -> e[0], e -> e[1])));
    }

    public static ArgsName of(String[] arguments) {
        checkArgsValidity(arguments);
        ArgsName names = new ArgsName();
        names.parse(arguments);

        return names;
    }

    public static void checkArgsValidity(String[] arguments) {
        if (arguments.length < 1) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String arg : arguments) {
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not start with a '-' character", arg)
                );
            }
            if (!arg.contains("=")) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not contain an equal sign", arg)
                );

            }
            if (arg.startsWith("-=")) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not contain a key", arg)
                );
            }
            if (arg.endsWith("=") && arg.chars().filter(ch -> ch == '=').count() < 2
                    && arg.startsWith("-")) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not contain a value", arg)
                );
            }
        }



    }


    public static void main(String[] args) {
        String[] arguments = {"-Xmx=512", "-encoding=UTF-8"};
        ArgsName jvm = ArgsName.of(arguments);
        System.out.println(jvm.get("Xmx"));

    }
}