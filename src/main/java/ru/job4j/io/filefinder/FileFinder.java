package ru.job4j.io.filefinder;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import ru.job4j.io.ArgsName;
import ru.job4j.io.SearchFiles;

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
    public static void findFile() throws IOException {
        condition = setCondition(match);
        SearchFiles searchFiles = new SearchFiles(condition);
        SearchFiles.search(destinationFile, condition);
        Files.walkFileTree(searchDirectory, searchFiles);
        logFile(searchFiles.getPaths());
    }

    public static void logFile(List<Path> paths) throws IOException {
        try (FileWriter fileWriter = new FileWriter(destinationFile.toFile(), true)) {
            fileWriter.append(paths.toString()).append(System.lineSeparator());
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
    /* -d=c:  -n=*.?xt -t=mask -o=log.txt*/
    private static void checkArgs(ArgsName argsName) {
        if (argsName.get("d").isEmpty() || !Path.of(argsName.get("d")).toFile().isDirectory()) {
            throw new IllegalArgumentException(
                    "Source should be a directory."
            );
        }
        if (argsName.get("n").isEmpty()) {
            throw new IllegalArgumentException(
                    "Please specify match criteria : name, mask or regex."
            );
        }
        if (argsName.get("t").isEmpty()) {
            throw new IllegalArgumentException(
                    "Please specify search criteria : name, mask or regex."
            );
        }
        if (argsName.get("o").isEmpty()) {
            throw new IllegalArgumentException(
                    "Destination should not be empty."
            );
        }
    }


    public static void main(String[] args) throws IOException {
        if (args.length < 4) {
            throw new IllegalArgumentException(
                    String.format("4 arguments should be passed to the app, you passed %s", args.length)
            );
        }
        ArgsName argsName = ArgsName.of(args);
        checkArgs(argsName);
        searchDirectory = Path.of(argsName.get("d"));
        match = argsName.get("n");
        searchType = argsName.get("t");
        destinationFile = Path.of(argsName.get("o"));

        findFile();
    }
}
