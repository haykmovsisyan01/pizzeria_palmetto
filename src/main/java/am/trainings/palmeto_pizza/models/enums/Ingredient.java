package am.trainings.palmeto_pizza.models.enums;

import am.trainings.palmeto_pizza.models.Priceable;

import java.math.BigDecimal;

public enum Ingredient implements Priceable {
    TOMATO_PASTE("Tomato paste", BigDecimal.valueOf(1.0)),
    CHEESE("Cheese", BigDecimal.valueOf(1.0)),
    SALAMI("Salami", BigDecimal.valueOf(1.5)),
    BACON("Bacon", BigDecimal.valueOf(1.2)),
    GARLIC("Garlic", BigDecimal.valueOf(0.3)),
    CORN("Corn", BigDecimal.valueOf(0.7)),
    PEPPERONI("Pepperoni", BigDecimal.valueOf(0.6)),
    OLIVES("Olives", BigDecimal.valueOf(0.5));

    private final String name;
    private final BigDecimal price;

    Ingredient(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
