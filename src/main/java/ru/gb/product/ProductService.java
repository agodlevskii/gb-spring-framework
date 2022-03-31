package ru.gb.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findOne(String id) {
        return productRepository.findOne(id);
    }

    public int count() {
        return productRepository.count();
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void delete(String id) {
        productRepository.delete(id);
    }
}
