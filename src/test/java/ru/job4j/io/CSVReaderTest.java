package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Files;

class CSVReaderTest {

    @Test
    void whenFilterTwoColumns() throws Exception {
        Path folder = Path.of("C:\\Users\\Pasku\\TestDir");
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File target = folder.resolve("target.csv").toFile();
        File file = folder.resolve("source.csv").toFile();
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;",
                "-out=" + target.getAbsolutePath(), "-filter=name,education"});
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "name;education",
                "Tom;Bachelor",
                "Jack;Undergraduate",
                "William;Secondary special"
        ).concat(System.lineSeparator());
        CSVReader.handle(argsName);
        assertThat(Files.readString(target.toPath())).isEqualTo(expected);
    }

    @Test
    void whenFilterThreeColumns() throws Exception {
        Path folder = Path.of("C:\\Users\\Pasku\\TestDir");
        String data = String.join(
                System.lineSeparator(),
                "name,age,last_name,education",
                "Tom,20,Smith,Bachelor",
                "Jack,25,Johnson,Undergraduate",
                "William,30,Brown,Secondary special"
        );
        File file = folder.resolve("source.csv").toFile();
        File target = folder.resolve("target.csv").toFile();
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=,",
                "-out=" + target.getAbsolutePath(), "-filter=education,age,last_name"
        });
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "education,age,last_name",
                "Bachelor,20,Smith",
                "Undergraduate,25,Johnson",
                "Secondary special,30,Brown"
        ).concat(System.lineSeparator());
        CSVReader.handle(argsName);
        assertThat(Files.readString(target.toPath())).isEqualTo(expected);
    }

    @Test
    void whenFilterOneColumn() throws Exception {
        Path folder = Path.of("C:\\Users\\Pasku\\TestDir");
        String data = String.join(
                System.lineSeparator(),
                "name,age,last_name,education",
                "Tom,20,Smith,Bachelor",
                "Jack,25,Johnson,Undergraduate",
                "William,30,Brown,Secondary special"
        );
        File file = folder.resolve("source.csv").toFile();
        File target = folder.resolve("target.csv").toFile();
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=,",
                "-out=" + target.getAbsolutePath(), "-filter=education"
        });
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "education",
                "Bachelor",
                "Undergraduate",
                "Secondary special"
        ).concat(System.lineSeparator());
        CSVReader.handle(argsName);
        assertThat(Files.readString(target.toPath())).isEqualTo(expected);
    }

    @Test
    void whenFilterFourColumns() throws Exception {
        Path folder = Path.of("C:\\Users\\Pasku\\TestDir");
        String data = String.join(
                System.lineSeparator(),
                "name,age,last_name,education",
                "Tom,20,Smith,Bachelor",
                "Jack,25,Johnson,Undergraduate",
                "William,30,Brown,Secondary special"
        );
        File file = folder.resolve("source.csv").toFile();
        File target = folder.resolve("target.csv").toFile();
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=,",
                "-out=" + target.getAbsolutePath(), "-filter=education,last_name,age,name"
        });
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "education,last_name,age,name",
                "Bachelor,Smith,20,Tom",
                "Undergraduate,Johnson,25,Jack",
                "Secondary special,Brown,30,William"
        ).concat(System.lineSeparator());
        CSVReader.handle(argsName);
        assertThat(Files.readString(target.toPath())).isEqualTo(expected);
    }
}