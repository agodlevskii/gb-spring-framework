package ru.gb.gbspringframework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gb.gbspringframework.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    List<Product> findAll();

    @Override
    <S extends Product> S save(S entity);

    @Override
    Optional<Product> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Query("select p from Product p where p.cost > :cost")
    List<Product> findByCostGreaterThan(@Param("cost") BigDecimal cost);

    @Query("select p from Product p where p.cost < :cost")
    List<Product> findByCostLessThan(@Param("cost") BigDecimal cost);

    @Query("select p from Product p where p.cost >= :startCost and p.cost <= :endCost")
    List<Product> findByCostBetween(@Param("startCost") BigDecimal startCost, @Param("endCost") BigDecimal endCost);
}
