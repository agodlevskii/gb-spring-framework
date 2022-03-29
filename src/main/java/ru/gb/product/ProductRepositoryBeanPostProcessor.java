package ru.gb.product;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProductRepositoryBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ProductRepository) {
            ((ProductRepository) bean).addProduct(new Product("1", "Product #1", 99.99));
            ((ProductRepository) bean).addProduct(new Product("2", "Product #2", 149.99));
            ((ProductRepository) bean).addProduct(new Product("3", "Product #3", 199.99));
            ((ProductRepository) bean).addProduct(new Product("4", "Product #4", 49.99));
            ((ProductRepository) bean).addProduct(new Product("5", "Product #5", 99.99));
        }
        return bean;
    }
}
