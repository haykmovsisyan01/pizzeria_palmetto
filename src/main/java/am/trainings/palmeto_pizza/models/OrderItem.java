package am.trainings.palmeto_pizza.models;

import am.trainings.palmeto_pizza.util.Strings;

import java.math.BigDecimal;

public class OrderItem implements Priceable {
    private final MenuItem menuItem;
    private final int quantity;

    public OrderItem(MenuItem menuItem, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("You inputted negative quantity");
        }
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    @Override
    public String toString() {
        return getMenuItem().toString() + "\n" +
                "Amount: " + getPrice() + "$" + "\n" +
                "Quantity: " + quantity + "\n" +
                Strings.DIVIDER + "\n";
    }

    @Override
    public BigDecimal getPrice() {
        return menuItem.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
