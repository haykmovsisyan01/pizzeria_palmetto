package am.trainings.palmeto_pizza.services;

import am.trainings.palmeto_pizza.models.Order;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileOrderOutputService implements OrderOutputService {
    public void saveOrder(Order order) {
        try {
            Files.writeString(Paths.get("").toAbsolutePath().resolve("check.txt"), order.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
