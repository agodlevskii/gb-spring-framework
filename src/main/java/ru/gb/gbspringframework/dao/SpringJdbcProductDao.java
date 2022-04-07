package ru.gb.gbspringframework.dao;

import org.springframework.beans.factory.annotation.Autowired;
import ru.gb.gbspringframework.entity.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//@Component("springJdbc")
public class SpringJdbcProductDao implements ProductDao {
    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
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

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE id = " + id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                product = resultSetToProduct(resultSet);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM product");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                products.add(resultSetToProduct(resultSet));
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public Product saveOrUpdate(Product product) {
        Product newProduct = null;

        try {
            Connection connection = dataSource.getConnection();
            String query = String.format(
                    "INSERT INTO product (name, cost) VALUES ('%s', %f) RETURNING ID", product.getName(), product.getCost());
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                Long id = resultSet.getLong(1);
                newProduct = findById(id);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newProduct;
    }

    @Override
    public void deleteById(Long id) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM product WHERE id = " + id);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
