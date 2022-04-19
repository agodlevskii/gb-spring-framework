package ru.gb.gbspringframework.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("cart")
@Scope("prototype")
public class Cart {
    List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return List.copyOf(products);
    }

    public Product findById(Long id) {
        return products
                .stream()
                .filter(filteredProduct -> filteredProduct.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean has(Long id) {
        return products.contains(findById(id));
    }

    public int size() {
        return products.size();
    }

    public void save(Product product) {
        products.add(product);
    }

    public void removeById(Long id) {
        Product product = findById(id);

        if (product != null) {
            products.remove(product);
        }
    }

    @Override
    public String toString() {
        final var sb = new StringBuilder();

        for (Product product: products) {
            sb.append(product.getId() + "; ");
        }

        return sb.toString();
    }
}
