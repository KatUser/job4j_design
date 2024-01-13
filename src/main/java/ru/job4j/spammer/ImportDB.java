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

    public void createDB(String dbName) throws Exception {

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
            try (Statement statement = connection.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS users"
                        + "(name TEXT,"
                        + "email TEXT);";
                statement.execute(sql);
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
//            String drivers = config.getProperty("jdbc.driver");
//            String connectionURL = config.getProperty("jdbc.url");
//            String username = config.getProperty("jdbc.username");
//            String password = config.getProperty("jdbc.password");
//            Class.forName(drivers);
//            connection = DriverManager.getConnection(connectionURL, username, password);
        }
        ImportDB dataBase = new ImportDB(config, "C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\spammer\\dump.txt");
        //dataBase.createDB("spammer");
       // dataBase.createTable("users");
        dataBase.save(dataBase.load());
    }
}
