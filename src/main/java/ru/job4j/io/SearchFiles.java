package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchFiles implements FileVisitor<Path> {

    private List<Path> pathList;
    private Predicate<Path> condition;
    public SearchFiles(Predicate<Path> condition) {
        this.pathList = new ArrayList<>();
        this.condition = condition;
    }

    public List<Path> getPaths() {
        return pathList;
    }

    public static void validateArguments(String[] args) {
        if (args.length == 2) {
            if (!Files.isDirectory(Path.of(args[0])) || !Files.exists(Path.of(args[0]))) {
                throw new IllegalArgumentException(
                        String.format("Not a valid argument for a directory : %s", args[0]));
            }
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9.]+$");
            Matcher matcher = pattern.matcher(args[1]);
            if (!matcher.find()) {
                throw new IllegalArgumentException(
                        String.format("Not a valid argument for a file extension : %s", args[1]));
            }
        } else {
            throw new IllegalArgumentException(
                    String.format("Please check that you have two arguments, not : %s", args.length));
        }
    }

    public static void main(String[] args) throws IOException {
        validateArguments(args);
        Path start = Paths.get("C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\io");
        search(start, p -> p.toFile().getName().endsWith(".java")).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (condition.test(file)) {
            pathList.add(file);
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}