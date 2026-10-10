package am.trainings.palmeto_pizza.models.enums;

import am.trainings.palmeto_pizza.models.Priceable;

import java.math.BigDecimal;

public enum PizzaType implements Priceable {
    REGULAR("Regular", BigDecimal.valueOf(1.0)),
    CALZONE("Calzone", BigDecimal.valueOf(1.5));

    final private String name;
    final private BigDecimal price;

    PizzaType(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
