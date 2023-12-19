package ru.job4j.io;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws IOException {
        List<Integer> filtersIndexes = new ArrayList<>();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Paths.get(argsName.get("out")).toFile()));
            var scanner = new Scanner(Paths.get(argsName.get("path"))).useDelimiter(System.lineSeparator())) {
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
/* -path=file.csv -delimiter=;  -out=stdout -filter=name,age*/
    private static void checkArgs(ArgsName argsName) {
        if (!argsName.get("path").endsWith("csv")) {
            throw new IllegalArgumentException(
                    "Source should be a file with csv extension."
            );
        }
        if (!";".equals(argsName.get("delimiter"))) {
            throw new IllegalArgumentException(
                    "Delimiter should be a semicolon."
            );
        }
        if (!("stdout".equals(argsName.get("out"))
                    || Paths.get(argsName.get("out")).toFile().isFile())) {
            throw new IllegalArgumentException(
                    "Destination should be either a file or 'stdout'."
            );
        }
        if (!("name".equals(argsName.get("filter").split(",")[0])
                    || "age".equals(argsName.get("filter").split(",")[1]))) {
            throw new IllegalArgumentException(
                    "Filter should contain 'age' and 'name'."
            );
        }
    }

    public static void main(String[] args) {
        if (args.length < 4) {
            throw new IllegalArgumentException(
                    String.format("4 arguments should be passed to the app, you passed %s", args.length)
            );
        }
        ArgsName argsName = ArgsName.of(args);
        checkArgs(argsName);
        try {
            handle(argsName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}