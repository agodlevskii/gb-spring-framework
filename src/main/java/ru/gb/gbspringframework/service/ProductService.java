package ru.gb.gbspringframework.service;

import ru.gb.gbspringframework.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findOne(Long id);
    void save(Product product);
    void delete(Long id);
}
