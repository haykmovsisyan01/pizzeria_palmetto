package am.trainings.palmeto_pizza;

import am.trainings.palmeto_pizza.common.ApplicationContext;
import am.trainings.palmeto_pizza.models.Customer;
import am.trainings.palmeto_pizza.models.Order;
import am.trainings.palmeto_pizza.models.OrderItem;
import am.trainings.palmeto_pizza.services.*;
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

    private static void initialize(ApplicationContext context, BufferedReader reader) {
        context.register(
                OrderInputService.class,
                () -> new CLOrderInputService(reader)
        );
        context.register(
                OrderOutputService.class,
                FileOrderOutputService::new
        );
        context.register(View.class, () -> new CLView(
                context.get(OrderInputService.class),
                context.get(OrderOutputService.class)
        ));
        context.register(
                OrderService.class,
                OrderService::new
        );
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ApplicationContext();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            initialize(ctx, reader);

            Application application = new Application(
                    ctx.get(View.class),
                    ctx.get(OrderService.class)
            );

            application.run();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
