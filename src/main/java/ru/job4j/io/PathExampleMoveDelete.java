package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExampleMoveDelete {
    public static void main(String[] args) throws IOException {
        Path dir = Paths.get("C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\io\\files\\directory2");
        Files.createDirectories(dir);
        Path path = Path.of("C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\io\\files\\directory2\\testfile.json");
        Files.createFile(path);
        //Files.move(path, Path.of("C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\io\\files\\directory2\\newdir\\testfile.json"));

        Files.delete(path);
        Files.delete(dir);
    }
}
