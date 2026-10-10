package am.trainings.palmeto_pizza.models;

import java.math.BigDecimal;
import java.util.List;

public class Order implements Priceable {
    private final Customer customer;
    private final int orderId;
    private final List<OrderItem> orderItems;

    public Order(Customer customer, int orderId, List<OrderItem> orderItems) {
        this.customer = customer;
        this.orderId = orderId;
        this.orderItems = orderItems;
    }

    public int getOrderId() {
        return orderId;
    }

    @Override
    public BigDecimal getPrice() {
        return orderItems.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Client: ").append(customer.getId()).append("\n");
        sb.append("Order: ").append(getOrderId()).append("\n");
        for (OrderItem orderItem : orderItems) {
            sb.append(orderItem).append("\n");
        }

        return sb.toString();
    }
}
