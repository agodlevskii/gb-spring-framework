package ru.gb.product;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {
    private final List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public Product getProductById(String id) {
        return products
                .stream()
                .filter(product -> id.equals(product.getId()))
                .findFirst()
                .orElse(null);
    }

    public List<String> getAvailableProductIds() {
        return products
                .stream()
                .map(Product::getId)
                .collect(Collectors.toList());
    }
}
