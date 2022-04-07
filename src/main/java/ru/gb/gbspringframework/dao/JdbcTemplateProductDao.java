package ru.gb.gbspringframework.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.gb.gbspringframework.entity.Product;

import java.util.List;

//@Component
@RequiredArgsConstructor
public class JdbcTemplateProductDao implements ProductDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Product findById(Long id) {
        return (Product) jdbcTemplate.queryForObject(
            "SELECT * FROM product WHERE id = " + id,
                new BeanPropertyRowMapper(Product.class)
        );
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM product", new BeanPropertyRowMapper(Product.class));
    }

    @Override
    public Product saveOrUpdate(Product product) {
        String query = "INSERT INTO product (name, cost) VALUES (?, ?) RETURNING ID";
        Long id = jdbcTemplate.queryForObject(query, Long.class, product.getName(), product.getCost());
        return findById(id);
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM product WHERE id = ?", new Object[]{id});
    }
}
