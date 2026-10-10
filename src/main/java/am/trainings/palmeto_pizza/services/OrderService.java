package am.trainings.palmeto_pizza.services;

import am.trainings.palmeto_pizza.models.Customer;
import am.trainings.palmeto_pizza.models.Order;
import am.trainings.palmeto_pizza.models.OrderItem;
import am.trainings.palmeto_pizza.util.Strings;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

public class OrderService {
    private static int orderCounter = 10000;
    private Order order;

    public OrderService() {
        if (orderCounter > 100000) orderCounter = 10000;
    }

    public Optional<Order> order( Customer customer,OrderItem... orderItems) {
        if (orderItems == null || orderItems.length == 0) {
            System.out.println("You didnt select any orders!");
            return Optional.empty();
        }

        order = new Order(
                customer,
                orderCounter++,
                Arrays.stream(orderItems).toList()
        );

        return Optional.of(order);
    }

    private void validateOrder() {
        if (order == null) throw new UnsupportedOperationException("Order is not specified");
    }

    public BigDecimal getTotalAmount() {
        validateOrder();
        return order.getPrice();
    }

    public String getCheck() {
        validateOrder();
        return Strings.CHECK_BORDER + "\n" +
                order + "\n" +
                "Total amount: " + getTotalAmount() + "$" + "\n" +
                Strings.CHECK_BORDER + "\n";
    }
}