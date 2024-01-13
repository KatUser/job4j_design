package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private Properties config;
    private String dump;
    Connection connection;

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public void createDB(String dbName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("CREATE %s", dbName);
            statement.execute(sql);
        }
    }

    public void createTable(String tableName) throws Exception {
        try (PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE ? IF NOT EXISTS"
        )) {
            statement.execute();
        }
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            reader.lines().forEach(s ->
                    users.add(new User(s.split(";")[0], s.split(";")[1])));
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
            for (User user : users) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("INSERT INTO ... ")) {
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
        try (InputStream input = ImportDB.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            config.load(input);
        }
        ImportDB dataBase = new ImportDB(config, "./dump.txt");
        dataBase.createDB("spammer");
        dataBase.createTable("users");
        dataBase.save(dataBase.load());
    }
}
