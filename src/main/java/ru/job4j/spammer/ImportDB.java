package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private Properties config;
    private String dump;

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(";");
                if (splitLine.length < 2
                    || splitLine[0].isBlank()
                    || splitLine[1].isBlank()) {
                    throw new IllegalArgumentException(
                           String.format("Must be 2 arguments, you passed %d",
                                   splitLine.length)
                    );
                }
                users.add(new User(splitLine[0], splitLine[1]));
            }
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
        )) {
            try (Statement statement = connection.createStatement()) {
                statement.execute("CREATE TABLE IF NOT EXISTS users"
                        + "(name TEXT,"
                        + "email TEXT);");
            }
            for (User user : users) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("INSERT INTO "
                                     + "users (name, email) VALUES (?, ?)")) {
                    preparedStatement.setString(1, user.name);
                    preparedStatement.setString(2, user.email);
                    preparedStatement.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (FileReader in = new FileReader("C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\spammer\\app.properties")) {
            config.load(in);
        }
        ImportDB dataBase = new ImportDB(config, "C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\spammer\\dump.txt");
        dataBase.save(dataBase.load());
    }
}
