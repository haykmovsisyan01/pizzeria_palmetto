package am.trainings.palmeto_pizza.view;

import am.trainings.palmeto_pizza.models.Customer;
import am.trainings.palmeto_pizza.models.Order;
import am.trainings.palmeto_pizza.models.OrderItem;
import am.trainings.palmeto_pizza.services.*;

public class CLView implements View {
    private final OrderInputService orderInputService;
    private final OrderOutputService orderOutputService;

    public CLView(OrderInputService orderInputService, OrderOutputService orderOutputService) {
        this.orderInputService = orderInputService;
        this.orderOutputService = orderOutputService;
    }

    @Override
    public Customer readCustomer() {
        return orderInputService.inputCustomer();
    }

    // Later will be added exceptions and them handling
    @Override
    public OrderItem readOrderItem() {
        return orderInputService.inputOrderItem().orElseThrow();
    }

    @Override
    public void writeOrder(Order order) {
        orderOutputService.saveOrder(order);
    }
}
