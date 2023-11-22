package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    public static Map<Long, String> pathHashMap = new HashMap<>();
    public static Set<Path> pathSet = new HashSet<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
            FileProperty fileProperty
                    = new FileProperty(file.toFile().length(), file.toFile().getName());
            if (pathHashMap.containsKey(fileProperty.getSize())
                    && pathHashMap.get(fileProperty.getSize()).equals(file.toFile().getName())) {
                pathSet.add(file);
            } else {
                pathHashMap.put(fileProperty.getSize(), fileProperty.getName());
            }
        return super.visitFile(file, attributes);
    }

    public static void printFiles() {
        for (Path f : pathSet) {
            System.out.println(f.toAbsolutePath());
        }
    }
}
