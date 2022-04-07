package ru.gb.gbspringframework.dao;

import ru.gb.gbspringframework.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OldJdbcProductDao implements ProductDao {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/gb", "geek", "geek");
    }

    private void closeConnection(Connection connection) {
        if (connection == null) {
            return;
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeStatement(PreparedStatement statement) {
        if (statement == null) {
            return;
        }

        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Product resultSetToProduct(ResultSet resultSet) throws SQLException {
        return Product.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .cost(resultSet.getBigDecimal("cost"))
                .build();
    }

    @Override
    public Product findById(Long id) {
        Product product = null;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM product WHERE id = " + id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                product = resultSetToProduct(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return product;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM product");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                products.add(resultSetToProduct(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return products;
    }

    @Override
    public Product saveOrUpdate(Product product) {
        Product newProduct = null;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            String query = String.format(
                "INSERT INTO product (name, cost) VALUES ('%s', %f) RETURNING ID", product.getName(), product.getCost());
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                Long id = resultSet.getLong(1);
                newProduct = findById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return newProduct;
    }

    @Override
    public void deleteById(Long id) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("DELETE FROM product WHERE id = " + id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }
}
