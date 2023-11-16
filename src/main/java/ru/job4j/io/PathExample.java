package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample {
    public static void main(String[] args) throws IOException {
        Path pathth = Path.of("src/main/java/ru/job4j/io/files/directory/file.txt");
        File file = pathth.toFile();
        System.out.println(file);
        Path pathAgain = file.toPath();

        Path dir = Paths.get("C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\io\\files\\directory2");
        Files.createDirectory(dir);
        Path path = Path.of("C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\io\\files\\directory2\\path.txt");
        Files.createFile(path);
        System.out.println("Does this file/directory exist?: " + Files.exists(path));
        System.out.println("Is this a directory?: " + Files.isDirectory(path));
        System.out.println("Is this a file?: " + Files.isRegularFile(path));
        System.out.println("File's name is: " + path.getFileName());
        System.out.println("File path is absolute?: " + path.toAbsolutePath());
        System.out.println("Parent directory is: " + path.getParent());
        System.out.println("Absolute path to the file is: " + path.toAbsolutePath());
        System.out.println("Absolute path to the dir is: " + dir.toAbsolutePath());
        System.out.println("Is this file readable?: " + Files.isReadable(path));
        System.out.println("Is this file writable?: " + Files.isWritable(path));

    }
}
