package ru.job4j.io;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;


import static ru.job4j.io.ArgsName.checkArgsValidity;

public class CSVReader { /* -path=file.csv -delimiter=;  -out=stdout -filter=name,age */
    public static void handle(ArgsName argsName) throws IOException {

        List<String> filters = Arrays.stream(argsName.get("filter").split(",")).toList();
        List<String> filtersIndexes = new ArrayList<>();


        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Paths.get(argsName.get("out")).toFile()));
            var scanner = new Scanner(Paths.get(argsName.get("path"))).useDelimiter(System.lineSeparator());
            int i = 0;
            while (scanner.hasNextLine()) {
                List<String> line = new ArrayList<>();
                List<String> splitLine = Arrays.stream(scanner.nextLine().split(argsName.get("delimiter"))).toList();

                if (i == 0) {
                    for (int j = 0; j < filters.size(); j++) { //-filter=education,age,last_name"
                        if (splitLine.contains(filters.get(j))) { //splitline = "name,age,last_name,education",
                            line.add(filters.get(j));
                            filtersIndexes.add(String.valueOf(splitLine.indexOf(filters.get(j))));
                        }
                    }
                    bufferedWriter.write(line.toString().replace(", ", argsName.get("delimiter")).replaceAll("^\\[|\\]$", ""));
                    bufferedWriter.newLine();
                    i++;
                } else {
                    for (String str : filtersIndexes) {

                            line.add(splitLine.get(Integer.parseInt(str)));

                    }
                    bufferedWriter.write(line.toString().replace(", ", argsName.get("delimiter")).replaceAll("^\\[|\\]$", ""));
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