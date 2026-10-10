package am.trainings.palmeto_pizza.services;

import am.trainings.palmeto_pizza.models.Customer;
import am.trainings.palmeto_pizza.models.OrderItem;

import java.util.Optional;

public interface OrderInputService {
    Customer inputCustomer();
    Optional<OrderItem> inputOrderItem();
}
