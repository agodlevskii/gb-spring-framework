package ru.gb.gbspringframework.dao;

import ru.gb.gbspringframework.entity.Product;

import java.util.List;

public interface ProductDao {
    Product findById(Long id);
    List<Product> findAll();
    Product saveOrUpdate(Product product);
    void deleteById(Long id);
}
