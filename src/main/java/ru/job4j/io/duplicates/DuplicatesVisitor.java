package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, Path> allFilesMap = new HashMap<>();
    private Map<FileProperty, Path> duplicatesMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
        FileProperty fileProperty
                = new FileProperty(file.toFile().length(), file.toFile().getName());
        allFilesMap.computeIfAbsent(fileProperty,
                k -> duplicatesMap.put(fileProperty, file.toAbsolutePath()));
        return super.visitFile(file, attributes);
    }
    public void printFiles() {
        for (Map.Entry<FileProperty, Path> duplicateFile : duplicatesMap.entrySet()) {
            for (Map.Entry<FileProperty, Path> file : allFilesMap.entrySet()) {
                if (file.getKey().equals(duplicateFile.getKey())) {
                    System.out.println(duplicateFile.getValue());
                    System.out.println(file.getValue());
                }
            }
        }
    }
}
