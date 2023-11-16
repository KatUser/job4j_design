package ru.job4j.io;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExampleListExample {
    public static void main(String[] args) throws IOException {
        Path dir = Paths.get("C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\io");
        Files.createDirectories(dir);
        Path target = Paths.get("C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j");
        Path path1 = Path.of("C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\io\\Example.java");
        Files.createFile(path1);
        Path path2 = Path.of("C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\path2.txt");
        Files.createFile(path2);
        DirectoryStream<Path> paths = Files.newDirectoryStream(target);
        paths.forEach(System.out::println);
    }
}
