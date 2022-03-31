package ru.gb.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.gb.product.Product;
import ru.gb.product.ProductService;

import java.util.*;
import java.util.stream.Collectors;

@Component("cart")
@Scope("prototype")
public class Cart {
    private ProductService productService;
    private final List<Product> products = new ArrayList<>();

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public List<Product> getCart() {
        return products;
    }

    public Product getFromCartById(String id) {
        return products
                .stream()
                .filter(product -> id.equals(product.getId()))
                .findFirst()
                .orElse(null);
    }

    public void addToCart(String id) {
        Product product = productService.findOne(id);
        products.add(product);
    }

    public void removeFromCart(String id) {
        Product product = getFromCartById(id);
        products.remove(product);
    }

    public void clearCart() {
        products.clear();
    }

    public List<String> getAddedProductIds() {
        return products
                .stream()
                .map(Product::getId)
                .collect(Collectors.toList());
    }

    public List<String> getAvailableProductIds() {
        return productService
                .findAll()
                .stream()
                .map(Product::getId)
                .collect(Collectors.toList());
    }
}
