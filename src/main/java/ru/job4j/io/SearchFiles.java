package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

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
        if (args.length < 2) {
            throw new IllegalArgumentException("Not enough arguments provided");
        }
        if (!Objects.equals(args[0], "C:")) {
            throw new IllegalArgumentException("Not a valid argument for a directory");
        }
        if (!Objects.equals(args[1], "java")) {
            throw new IllegalArgumentException("Not a valid argument for a file extension");
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
