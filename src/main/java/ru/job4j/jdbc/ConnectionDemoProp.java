package ru.job4j.jdbc;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class ConnectionDemoProp {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "CREATE TABLE IF NOT EXISTS (%s, %s);",
                        "id SERIAL PRIMARY KEY",
                        "name TEXT"
                );
                statement.execute(sql);
                System.out.println(getTableScheme(connection, "demo_table"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection con;
        try  {
            FileReader reader = new FileReader("C:\\projects\\job4j_design\\src\\main\\resources\\application.properties");
            Properties prop = new Properties();
            prop.load(reader);

            String drivers = prop.getProperty("driver");
            String connectionURL = prop.getProperty("url");
            String username = prop.getProperty("login");
            String password = prop.getProperty("password");
            Class.forName(drivers);
            con = DriverManager.getConnection(connectionURL, username, password);
            System.out.println("Connection Successful");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }
}