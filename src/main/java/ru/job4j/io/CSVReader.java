package ru.job4j.io;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;


import static ru.job4j.io.ArgsName.checkArgsValidity;

public class CSVReader { /* -path=file.csv -delimiter=;  -out=stdout -filter=name,age */
    public static void handle(ArgsName argsName) throws IOException {

        List<String> filters = Arrays.stream(argsName.get("filter").split(",")).toList(); //[name, age]
        List<String> filtersIndexes = new ArrayList<>();

        try {
            int i = 0;
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Paths.get("C:\\Users\\Pasku\\TestDir\\target.csv").toFile()));
            var scanner = new Scanner(Paths.get(argsName.get("path"))).useDelimiter(System.lineSeparator());
            while (scanner.hasNextLine()) {
                String[] splitLine = scanner.nextLine().split(argsName.get("delimiter"));
                if (i == 0) {
                    for (int j = 0; j < splitLine.length; j++) {
                        if (filters.contains(splitLine[j])) {
                            bufferedWriter.write(splitLine[j] + ";");
                            filtersIndexes.add(String.valueOf(j));
                        }
                    }
                    bufferedWriter.newLine();
                    i++;
                } else {
                    for (int k = 0; k < splitLine.length; k++) {
                        if (filtersIndexes.contains(String.valueOf(k))) {
                            bufferedWriter.write(splitLine[k] + ";");
                        }
                    }
                    bufferedWriter.newLine();
                }

            }
            bufferedWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        /* здесь добавьте валидацию принятых параметров*/
        checkArgsValidity(args);
        ArgsName argsName = ArgsName.of(args);
        try {
            handle(argsName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}