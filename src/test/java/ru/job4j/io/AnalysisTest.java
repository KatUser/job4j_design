package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {

    @Test
    void whenServerDiedOnce(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("tempserverlog.log").toFile();
        try (PrintWriter printWriter = new PrintWriter(source)) {
            printWriter.println("200 10:56:01");
            printWriter.println("400 10:56:02");
            printWriter.println("300 10:56:04");
        }
        File target = tempDir.resolve("temptarget.csv").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("10:56:02;10:56:04;").hasToString(rsl.toString());
    }

    @Test
    void whenServerDiedMoreThanOnce(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("tempserver.log").toFile();
        try (PrintWriter printWriter = new PrintWriter(source)) {
            printWriter.println("300 10:56:01");
            printWriter.println("400 10:56:02");
            printWriter.println("300 10:56:04");
            printWriter.println("500 10:56:06");
            printWriter.println("200 10:56:08");
            printWriter.println("300 10:56:11");
        }
        File target = tempDir.resolve("temptarget.csv").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("10:56:02;10:56:04;10:56:06;10:56:08;").hasToString(rsl.toString());
    }

    @Test
    void whenServerNeverDied(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("tempserver.log").toFile();
        try (PrintWriter printWriter = new PrintWriter(source)) {
            printWriter.println("200 14:00:00");
            printWriter.println("300 14:00:02");
        }
        File target = tempDir.resolve("temptarget.csv").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("").hasToString(rsl.toString());
    }
}
