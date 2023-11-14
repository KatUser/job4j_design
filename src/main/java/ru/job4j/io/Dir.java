package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Does not exists %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Is not a directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        checkFiles(file);
    }
    public static void checkFiles(File file) {
        for (File subfile : file.listFiles()) {
            if (subfile.isFile()) {
                System.out.println(String.format("File name is : %s, file size is: %s", subfile.getName(), subfile.length()));
            } else {
                checkFiles(subfile);
            }
        }
    }
}