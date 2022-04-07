package ru.gb.gbspringframework.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.gb.gbspringframework.entity.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Component
@RequiredArgsConstructor
public class NamedJdbcTemplateProductDao implements ProductDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Product findById(Long id) {
        String query = "SELECT * FROM product WHERE id = :productId";
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("productId", id);
        return (Product) jdbcTemplate.queryForObject(query, namedParameters, new BeanPropertyRowMapper(Product.class));
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM product", new BeanPropertyRowMapper(Product.class));
    }

    @Override
    public Product saveOrUpdate(Product product) {
        String query = "INSERT INTO product (name, cost) VALUES (:productName, :productCost) RETURNING ID";
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("productName", product.getName());
        namedParameters.put("productCost", product.getCost());
        Long id = jdbcTemplate.queryForObject(query, namedParameters, Long.class);
        return findById(id);
    }

    @Override
    public void deleteById(Long id) {
        String query = "DELETE FROM product WHERE id = :productId";
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("productId", id);
        jdbcTemplate.update(query, namedParameters);
    }
}
