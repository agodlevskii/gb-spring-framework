package ru.gb.gbspringframework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.gbspringframework.dao.ProductDao;
import ru.gb.gbspringframework.entity.Product;

import java.util.List;

@Service
public class ProductService {
//    // Old JDBC approach
//    private ProductDao productDao = new OldJdbcProductDao();

    private ProductDao productDao;

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }

    public Product findOne(Long id) {
        return productDao.findById(id);
    }

    public Product save(Product product) {
        return productDao.saveOrUpdate(product);
    }

    public void delete(Long id) {
        productDao.deleteById(id);
    }
}
