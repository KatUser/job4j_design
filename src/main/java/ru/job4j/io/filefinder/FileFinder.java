package ru.job4j.io.filefinder;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FileFinder extends SimpleFileVisitor<Path> {
    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        return super.visitFile(file, attributes);
    }

    public static void findFile(Path path) {

    }

    public static void logFile(Path path) {

    }

    public static void main(String[] args) {

    }
}
