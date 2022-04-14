package ru.gb.gbspringframework.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.gb.gbspringframework.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Page<Product> findAll(Pageable pageable);
    Product findOne(Long id);
    void save(Product product);
    void delete(Long id);
}
