package ru.gb.gbspringframework.repository;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ru.gb.gbspringframework.entity.Product;

@Component
public class ProductRepositoryBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ProductRepository) {
            ((ProductRepository) bean).save(new Product(1L, "Product #1", 99.99));
            ((ProductRepository) bean).save(new Product(2L, "Product #2", 149.99));
            ((ProductRepository) bean).save(new Product(3L, "Product #3", 199.99));
            ((ProductRepository) bean).save(new Product(4L, "Product #4", 49.99));
            ((ProductRepository) bean).save(new Product(5L, "Product #5", 99.99));
        }
        return bean;
    }
}
