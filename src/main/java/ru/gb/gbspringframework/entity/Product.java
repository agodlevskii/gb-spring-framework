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

    @Override
    public String toString() {
        return getId() + "; " + getName() + "; " + getCost();
    }
}
