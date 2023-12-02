package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(target)))) {
            for (Path s : sources) {
                zipOutputStream.putNextEntry(new ZipEntry(s.toFile().getPath()));
                try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(s.toFile()))) {
                    zipOutputStream.write(bufferedInputStream.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getName()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkParameters(ArgsName argsName) {
        if (!Path.of(argsName.get("d")).toFile().isDirectory()
                || !Path.of(argsName.get("d")).toFile().exists()) {
            throw new IllegalArgumentException(
                    String.format("Source folder doesn't exist: %s", argsName.get("d"))
            );
        }
        if (!argsName.get("e").contains(".") || argsName.get("e").equals(".")) {
            throw new IllegalArgumentException(
                    String.format("This is not a valid file extension: %s", argsName.get("e"))
            );
        }
        if (!argsName.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException(
                    String.format("This is not an archive file: %s", argsName.get("o"))
            );
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        checkParameters(argsName);
        Zip zip = new Zip();
        List<Path> pathList = SearchFiles.search(Path.of(argsName.get("d")),
                f -> !f.toFile().getName().endsWith(argsName.get("e")));
        zip.packFiles(pathList, Path.of(argsName.get("o")).toFile());
    }
}