package ru.gb.gbspringframework.dao;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.gbspringframework.entity.Product;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HibernateProductDao implements ProductDao {
    private final SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        Query<Product> query = sessionFactory.getCurrentSession().getNamedQuery("Product.findById");
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return sessionFactory.getCurrentSession().getNamedQuery("Product.findAll").list();
    }

    @Override
    @Transactional
    public Product saveOrUpdate(Product product) {
        sessionFactory.getCurrentSession().persist(product);
        return product;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Query<Product> query = sessionFactory.getCurrentSession().getNamedQuery("Product.deleteById");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
