package ru.gb.product;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String id;
    private String name;
    private BigDecimal cost;

    public Product(String id, String name, double cost) {
        this.id = id;
        this.name = name;
        this.cost = getCost(cost);
    }

    private BigDecimal getCost(double rawCost) {
    return new BigDecimal(rawCost).setScale(2, RoundingMode.HALF_EVEN);
    }
}