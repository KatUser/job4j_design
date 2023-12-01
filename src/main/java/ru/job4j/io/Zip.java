package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import ru.job4j.io.ArgsName;
import ru.job4j.io.SearchFiles;

public class Zip {

    public void packFiles(List<Path> sources, File target) {

    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkParameters(String[] arguments) {
        if (!Files.isDirectory(Path.of(arguments[0].split("=")[1]))
                || !Files.exists(Path.of(arguments[0].split("=")[1]))) {
            throw new IllegalArgumentException(
                    String.format("This directory: %s doesn't exist", arguments[0].split("=")[1])
            );
        }
        if (!arguments[1].contains("=.")
                || !arguments[1].split("=")[1].equals(".")) {
            throw new IllegalArgumentException(
                    String.format("This is not a file: %s", arguments[1].split("=")[1])

            );
        }
        if (!arguments[2].split("=")[1].endsWith(".zip")) {
            throw new IllegalArgumentException(
                    String.format("This directory: %s needs to have zip extension", arguments[2].split("=")[1])

            );
        }
    }

    /* -d=c:\project\job4j\ -e=.class -o=project.zip **/
    public static void main(String[] args) {
        checkParameters(args);
        ArgsName.checkArgsValidity(args);
        Zip zip = new Zip();
        /*
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
         **/

        System.out.println(args[0].split("=")[1]);


    }
}
