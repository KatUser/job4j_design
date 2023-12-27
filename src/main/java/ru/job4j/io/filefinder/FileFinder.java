package ru.job4j.io.filefinder;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import ru.job4j.io.ArgsName;
import ru.job4j.io.SearchFiles;

import java.io.BufferedWriter;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FileFinder {
    private static Path searchDirectory; /* директория, в которой начинать поиск.*/
    private static String match; /* имя файла, маска, либо регулярное выражение. */
    private static String searchType; /* mask искать по маске, name по полному совпадение имени,
    regex по регулярному выражению. */
    private static Path destinationFile; /* результат записать в файл. */
    private static Predicate<Path> condition;
    public static void findFiles() throws IOException {
        condition = setCondition(match);
        SearchFiles searchFiles = new SearchFiles(condition);
        SearchFiles.search(destinationFile, condition);
        Files.walkFileTree(searchDirectory, searchFiles);
        logFiles(searchFiles.getPaths());
    }

    public static void logFiles(List<Path> paths) {
        try (BufferedWriter bufferedWriter
                = new BufferedWriter(new FileWriter(destinationFile.toFile(), true))) {
            for (Path p : paths) {
                bufferedWriter.write(p.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Predicate<Path> setCondition(String match) {
        if ("name".equals(searchType)) {
            condition = p -> p.toFile().getName().equals(match);
        }
        if ("mask".equals(searchType)) {
            FileFilter fileFilter = new WildcardFileFilter(match);
            condition = p -> fileFilter.accept(p.toFile());
        }
        if ("regex".equals(searchType)) {
            Pattern pattern = Pattern.compile(match);
            condition = p -> pattern.matcher(p.getFileName().toString()).find();
        }
        return condition;
    }

    private static void checkArgs(ArgsName argsName) {
        if (!Path.of(argsName.get("d")).toFile().isDirectory()) {
            throw new IllegalArgumentException(
                    "Source should be a directory."
            );
        }
        if (!("mask".equals(argsName.get("t")) || "name".equals(argsName.get("t"))
                || "regex".equals(argsName.get("t")))) {
            throw new IllegalArgumentException(
                    "Please specify search pattern : name, mask or regex."
            );
        }
        if (!Path.of(argsName.get("o")).toFile().isFile()) {
            throw new IllegalArgumentException(
                    "Destination should be a file."
            );
        }
    }

    private static void setSearchCriteria(ArgsName argsName) {
        searchDirectory = Path.of(argsName.get("d"));
        match = argsName.get("n");
        searchType = argsName.get("t");
        destinationFile = Path.of(argsName.get("o"));
    }


    public static void main(String[] args) throws IOException {
        if (args.length < 4) {
            throw new IllegalArgumentException(
                    String.format("4 arguments should be passed to the app, you passed %s", args.length)
            );
        }
        ArgsName argsName = ArgsName.of(args);
        checkArgs(argsName);
        setSearchCriteria(argsName);
        findFiles();
    }
}
