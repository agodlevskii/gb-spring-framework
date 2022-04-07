package ru.gb.gbspringframework.repository;

import org.springframework.stereotype.Repository;
import ru.gb.gbspringframework.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private final List<Product> products = new ArrayList<>();

    public List<Product> findAll() {
        return products;
    }

    public Product findOne(Long id) {
        return products
                .stream()
                .filter(product -> id.equals(product.getId()))
                .findFirst()
                .orElse(null);
    }

    public int count() {
        return products.size();
    }

    public void save(Product product) {
        products.add(product);
    }

    public void delete(Long id) {
        Product product = findOne(id);
        products.remove(product);
    }
}
