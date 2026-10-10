package am.trainings.palmeto_pizza.models;

import am.trainings.palmeto_pizza.models.enums.Ingredient;
import am.trainings.palmeto_pizza.models.enums.PizzaType;
import am.trainings.palmeto_pizza.util.Strings;
import am.trainings.palmeto_pizza.util.Validator;

import java.math.BigDecimal;
import java.util.HashSet;

public class Pizza implements MenuItem {
    private final String name;
    private final PizzaType type;
    private final HashSet<Ingredient> ingredients;

    public Pizza(String name, PizzaType type, Ingredient... ingredients) {
        if (!Validator.validateName(name)) {
            throw new IllegalArgumentException("Invalid pizza name " + name + ". Must be between 5 and 19 characters.");
        }
        if (type == null) type = PizzaType.REGULAR;
        this.name = name;
        this.type = type;

        this.ingredients = new HashSet<>();
        for (Ingredient ingredient : ingredients) {
            addIngredient(ingredient);
        }
    }


    public void addIngredient(Ingredient ingredient) {
        if (ingredient == null) return;

        if (ingredients.size() == 7) {
            System.out.println("Pizza is full");
            return;
        }

        if (!ingredients.add(ingredient)) {
            System.out.println("Pizza already has specified ingredient");
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getPrice() {
        return type.getPrice().add(ingredients.stream().
                map(Ingredient::getPrice).
                reduce(BigDecimal::add).
                orElse(BigDecimal.ZERO));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Strings.PIZZA_DIVIDER).append("\n");
        stringBuilder.append("Name: ").append(getName()).append("\n");
        stringBuilder.append("Type: ").append(type).append("\n");
        stringBuilder.append(Strings.DIVIDER).append("\n");
        for (Ingredient ingredient : ingredients) {
            stringBuilder.append(ingredient.getName()).append(" ").append(ingredient.getPrice()).append("$").append("\n");
        }
        stringBuilder.append(Strings.PIZZA_DIVIDER);

        return stringBuilder.toString();
    }
}
