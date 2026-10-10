package am.trainings.palmeto_pizza.services;

import am.trainings.palmeto_pizza.models.Customer;
import am.trainings.palmeto_pizza.models.OrderItem;
import am.trainings.palmeto_pizza.models.Pizza;
import am.trainings.palmeto_pizza.models.enums.Ingredient;
import am.trainings.palmeto_pizza.models.enums.PizzaType;
import am.trainings.palmeto_pizza.util.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CLOrderInputService implements OrderInputService {
    private final BufferedReader reader;

    public CLOrderInputService(BufferedReader reader) {
        this.reader = reader;
    }

    public Customer inputCustomer()  {
        while (true) {
            try {
                System.out.print("Input customer id: ");
                String input = reader.readLine();
                return new Customer(Integer.parseInt(input.trim()));
            } catch (NumberFormatException e) {
                System.out.println("Please input number!");
            } catch (IOException e){
                System.out.println("Something went wrong!");
            }
        }
    }

    @Override
    public Optional<OrderItem> inputOrderItem() {
        try {
            Pizza inputPizza = getInputPizza(reader);
            int inputPizzaQuantity = getInputPizzaQuantity(reader);

            return Optional.of(new OrderItem(inputPizza, inputPizzaQuantity));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    private Pizza getInputPizza(BufferedReader reader) throws IOException {
        String pizzaName = getInputPizzaName(reader);
        PizzaType pizzaType = getInputPizzaType(reader);
        Ingredient[] ingredients = getInputPizzaIngredients(reader);
        return new Pizza(pizzaName, pizzaType, ingredients);
    }

    private int getInputPizzaQuantity(BufferedReader reader) throws IOException {
        while (true) {
            try {
                System.out.print("Input pizza quantity: ");
                return Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Please input number!");
            }
        }
    }

    private String getInputPizzaName(BufferedReader reader) throws IOException {
        while (true) {
            System.out.print("Input pizza name: ");
            String name = reader.readLine().trim();

            if (Validator.validateName(name)) {
                return name;
            }
            System.out.println("Invalid pizza name: " + name + ". Must be between 5 and 19 characters.");
        }
    }

    private Ingredient[] getInputPizzaIngredients(BufferedReader reader) throws IOException {
        while (true) {
            try {
                System.out.print("Input ingredients: ");
                String input = reader.readLine();
                if (input == null) {
                    throw new IOException("Something went wrong!");
                }
                List<String> ingredientsList = Arrays.stream(input.split(",")).map((el) -> el.trim().toUpperCase()).toList();

                return ingredientsList.stream().map(Ingredient::valueOf).toArray(Ingredient[]::new);
            } catch (IllegalArgumentException e) {
                System.out.println("Please input valid ingredients!");
            }
        }
    }

    private PizzaType getInputPizzaType(BufferedReader reader) throws IOException {
        while (true) {
            try {
                System.out.print("Input pizza type: ");
                String input = reader.readLine();

                if (input == null) {
                    throw new IOException("Something went wrong!");
                }
                return PizzaType.valueOf(input.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid pizza type");
            }
        }
    }

}
