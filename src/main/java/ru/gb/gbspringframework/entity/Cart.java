package ru.gb.gbspringframework.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("cart")
@Scope("prototype")
public class Cart {
    List<Product> products = new ArrayList<>();
    Map<Long, Integer> productsQuantity = new HashMap<>();

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
        productsQuantity.put(product.getId(), 1);
    }

    public int quantity(Long id) {
        return has(id) ? productsQuantity.get(id) : 0;
    }

    public void increase(Long id) {
        if (!has(id)) {
            return;
        }

        productsQuantity.put(id, productsQuantity.get(id) + 1);
    }

    public void decrease(Long id) {
        if (!has(id)) {
            return;
        }

        int newQuantity = (productsQuantity.get(id) - 1);

        productsQuantity.put(id, newQuantity > 0 ? newQuantity : 1);
    }

    public void removeById(Long id) {
        Product product = findById(id);

        if (product != null) {
            products.remove(product);
            productsQuantity.remove(product.getId());
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
