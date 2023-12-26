package ru.job4j.io.filefinder;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import ru.job4j.io.ArgsName;

import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/* -d=c:  -n=*.?xt -t=mask -o=log.txt*/
public class FileFinder extends SimpleFileVisitor<Path> {
    private static Path searchDirectory; /* директория, в которой начинать поиск.*/
    private static String match; /* имя файла, маска, либо регулярное выражение. */
    private static String searchType; /* mask искать по маске, name по полному совпадение имени,
    regex по регулярному выражению. */
    private static Path destinationFile; /* результат записать в файл. */
    private static Predicate<Path> condition;


    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        if (condition.test(file)) {
            logFile(file);
        }

        return super.visitFile(file, attributes);
    }

    public static Path findFile(Path path) throws IOException {
        FileFinder fileFinder = new FileFinder();
        setCondition(match);
        Files.walkFileTree(searchDirectory, fileFinder);
        return null;
    }

    public static void logFile(Path path) throws IOException {
        try (FileWriter fileWriter = new FileWriter(destinationFile.toFile(), true)) {
            fileWriter.append(path.toString()).append(System.lineSeparator());
        }

    }

    private static void setCondition(String match) {
        if ("name".equals(searchType)) {
            condition = p -> p.toFile().getName().equals(match);
        }
        if ("mask".equals(searchType)) {
            FileFilter fileFilter = new WildcardFileFilter(match);
            condition = p -> fileFilter.accept(p.toFile());
        }
        if ("regex".equals(searchType)) {
            Pattern pattern = Pattern.compile(match);
            //Matcher matcher = pattern.matcher()
            condition = p -> pattern.matcher(p.getFileName().toString()).find();
            //(f -> pattern.matcher(f).matches())
        }
    }


    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        searchDirectory = Path.of(argsName.get("d"));
        match = argsName.get("n");
        searchType = argsName.get("t");
        destinationFile = Path.of(argsName.get("o"));
        findFile(destinationFile);
    }
}
