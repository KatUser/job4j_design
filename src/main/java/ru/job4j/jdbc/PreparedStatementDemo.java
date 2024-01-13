package ru.job4j.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreparedStatementDemo implements AutoCloseable {
    private Connection connection;

    public PreparedStatementDemo() throws Exception {
        initConnection();
    }

    public void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/db_m";
        String login = "postgres";
        String password = "password";
        connection = DriverManager.getConnection(url, login, password);
    }

    public City insert(City city) throws SQLException {
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             "INSERT INTO cities(name, population) VALUES(?, ?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    city.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    public boolean update(City city) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE cities SET name = ?, population = ? WHERE id = ?")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.setInt(3, city.getId());
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM cities WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
                String sql = String.format("DROP TABLE IF EXISTS %s;", tableName);
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM cities")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("population")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static void main(String[] args) throws Exception {
        try (PreparedStatementDemo preparedStatementDemo = new PreparedStatementDemo()) {
            preparedStatementDemo.insert(new City(1, "Novosibirsk", 3000000));
            preparedStatementDemo.insert(new City(2, "Yemen", 500000000));
            System.out.println(preparedStatementDemo.update(new City(1, "NY", 889900999)));
            preparedStatementDemo.delete(1);
            System.out.println(preparedStatementDemo.findAll().toString());
            preparedStatementDemo.dropTable("cities");
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

}
