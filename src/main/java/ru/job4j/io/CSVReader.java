package ru.job4j.io;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws IOException {
        List<Integer> filtersIndexes = new ArrayList<>();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Paths.get(argsName.get("out")).toFile()))) {
            var scanner = new Scanner(Paths.get(argsName.get("path"))).useDelimiter(System.lineSeparator());
            List<String> filters = Arrays.stream(argsName.get("filter").split(",")).toList();
            while (filtersIndexes.size() < filters.size()) {
                List<String> line = new ArrayList<>();
                List<String> splitLine = Arrays.stream(scanner.nextLine().split(argsName.get("delimiter"))).toList();
                for (String filter : filters) {
                    if (splitLine.contains(filter)) {
                        line.add(filter);
                        filtersIndexes.add(splitLine.indexOf(filter));
                    }
                }
                bufferedWriter.write(line.toString().replace(", ", argsName.get("delimiter")).replaceAll("^\\[|]$", ""));
                bufferedWriter.newLine();
            }
            while (scanner.hasNextLine()) {
                List<String> line = new ArrayList<>();
                List<String> splitLine = Arrays.stream(scanner.nextLine().split(argsName.get("delimiter"))).toList();
                for (int in : filtersIndexes) {
                    line.add(splitLine.get(in));
                }
                bufferedWriter.write(line.toString().replace(", ", argsName.get("delimiter")).replaceAll("^\\[|]$", ""));
                bufferedWriter.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void checkArgs(String[] args) {
        for (String arg : args) {
            if (arg.endsWith("=")) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not contain a value.", arg)
                );
            }
        }
        if (!"csv".equals(args[0].split("=")[1].split("\\.")[1])) {
            throw new IllegalArgumentException(
                    "File with csv extension should be provided as a path."
            );
        }
        if (!("stdout".equals(args[2].split("=")[1]) || Paths.get(args[2].split("=")[1]).toFile().isFile())) {
            throw new IllegalArgumentException(
                    "Destination should be either 'stdout' or a file."
            );
        }
    }

    public static void main(String[] args) {
        checkArgs(args);
        System.out.println(args[0].split("=")[0]);
        ArgsName argsName = ArgsName.of(args);
        try {
            handle(argsName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}