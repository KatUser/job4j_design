package ru.job4j.io.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;


public class AttributesExample {
    public static void main(String[] args) throws IOException {
        Path file = Path.of("ttributes2.txt");
        Files.createFile(file);
        BasicFileAttributeView attrView = Files.getFileAttributeView(file, BasicFileAttributeView.class);
        BasicFileAttributes attributes = attrView.readAttributes();
     }
}
