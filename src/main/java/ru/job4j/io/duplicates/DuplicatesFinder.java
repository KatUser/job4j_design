package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j"), duplicatesVisitor);
        duplicatesVisitor.printFiles();
    }
}
