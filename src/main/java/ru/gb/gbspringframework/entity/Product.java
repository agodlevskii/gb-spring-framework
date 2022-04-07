package ru.gb.gbspringframework.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product")
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "from Product"),
    @NamedQuery(name = "Product.findById", query = "select p from Product p where p.id = :id"),
    @NamedQuery(name = "Product.deleteById", query = "delete from Product p where p.id = :id")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "cost")
    private BigDecimal cost;

    public Product(Long id, String name, double cost) {
        this.id = id;
        this.name = name;
        this.cost = getCost(cost);
    }

    private BigDecimal getCost(double rawCost) {
    return new BigDecimal(rawCost).setScale(2, RoundingMode.HALF_EVEN);
    }
}
