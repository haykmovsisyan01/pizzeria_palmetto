package am.trainings.palmeto_pizza;

import am.trainings.palmeto_pizza.models.Customer;
import am.trainings.palmeto_pizza.models.Order;
import am.trainings.palmeto_pizza.models.OrderItem;
import am.trainings.palmeto_pizza.services.CLOrderInputService;
import am.trainings.palmeto_pizza.services.FileOrderOutputService;
import am.trainings.palmeto_pizza.services.OrderService;
import am.trainings.palmeto_pizza.view.CLView;
import am.trainings.palmeto_pizza.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class Application {
    private final View view;
    private final OrderService orderService;

    public Application(View view, OrderService orderService) {
        this.view = view;
        this.orderService = orderService;
    }

    public void run() {
        Customer customer = view.readCustomer();
        OrderItem orderItem = view.readOrderItem();

        Optional<Order> order = orderService.order(customer, orderItem);
        view.writeOrder(order.orElseThrow());
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Application application = new Application(
                    new CLView(
                            new CLOrderInputService(reader),
                            new FileOrderOutputService()
                    ),
                    new OrderService()
            );

            application.run();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
