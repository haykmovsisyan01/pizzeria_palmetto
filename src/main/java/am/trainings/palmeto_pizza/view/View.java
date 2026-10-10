package am.trainings.palmeto_pizza.view;

import am.trainings.palmeto_pizza.models.Customer;
import am.trainings.palmeto_pizza.models.Order;
import am.trainings.palmeto_pizza.models.OrderItem;

public interface View {
    Customer readCustomer();
    OrderItem readOrderItem();

    void writeOrder(Order order);
}
