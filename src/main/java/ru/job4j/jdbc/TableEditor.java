package ru.job4j.jdbc;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            FileReader reader = new FileReader("C:\\projects\\job4j_design\\src\\main\\resources\\application.properties");
            properties.load(reader);
            String drivers = properties.getProperty("driver");
            String connectionURL = properties.getProperty("url");
            String username = properties.getProperty("login");
            String password = properties.getProperty("password");
            Class.forName(drivers);
            connection = DriverManager.getConnection(connectionURL, username, password);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "CREATE TABLE IF NOT EXISTS %s"
                    + "(id SERIAL PRIMARY KEY,"
                    + "name TEXT);", tableName
            );
            statement.execute(sql);
        }
    }

    public void dropTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "DROP TABLE IF EXISTS %s;", tableName
            );
            statement.execute(sql);
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE  %s  ADD COLUMN %s %s;", tableName, columnName, type
            );
            statement.execute(sql);
        }
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE  %s  DROP COLUMN %s;", tableName, columnName
            );
            statement.execute(sql);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName
            );
            statement.execute(sql);
        }
    }

    public String getTableScheme(String tableName) throws Exception {
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

    public void printTableScheme(String tableName) throws Exception {
        System.out.println(getTableScheme(tableName));
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        TableEditor tableEditor = new TableEditor(new Properties());
        tableEditor.createTable("demotable");
        tableEditor.printTableScheme("demotable");
        tableEditor.addColumn("demotable", "on_sale", "boolean");
        tableEditor.printTableScheme("demotable");
        tableEditor.renameColumn("demotable", "on_sale", "sale");
        tableEditor.printTableScheme("demotable");
        tableEditor.dropColumn("demotable", "sale");
        tableEditor.printTableScheme("demotable");
        tableEditor.dropTable("demotable");
    }
}
