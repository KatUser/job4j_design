package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    public static Map<FileProperty, Path> pathMap = new HashMap<>();
    public static Map<FileProperty, Path> duplicatesMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
        FileProperty fileProperty
                = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (pathMap.containsKey(fileProperty)) {
            duplicatesMap.put(fileProperty, file.toAbsolutePath());

        } else {
            pathMap.put(fileProperty, file.toAbsolutePath());
        }
        return super.visitFile(file, attributes);
    }

    public static void printFiles() {
        FileProperty fp = null;
        for (Map.Entry<FileProperty, Path> file : duplicatesMap.entrySet()) {
            fp = file.getKey();
            System.out.println(file.getValue());
            for (Map.Entry<FileProperty, Path> filedupl : pathMap.entrySet()) {
                if (filedupl.getKey().equals(fp)) {
                    System.out.println(filedupl.getValue());
                }
            }
        }
    }
}
