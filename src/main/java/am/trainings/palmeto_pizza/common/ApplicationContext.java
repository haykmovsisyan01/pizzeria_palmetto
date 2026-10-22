package am.trainings.palmeto_pizza.common;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class ApplicationContext {
    private final Map<Class<?>, Object> singletons;
    private final Map<Class<?>, Supplier<?>> factories;

    public ApplicationContext() {
        singletons = new ConcurrentHashMap<>();
        factories = new ConcurrentHashMap<>();
    }

    public <T> void register(Class<? extends T> type, Supplier<? extends T> supplier) {
        Objects.requireNonNull(type, "type must not be null");
        Objects.requireNonNull(supplier, "factory must not be null");

        if (factories.putIfAbsent(type, supplier) != null) {
            throw new IllegalStateException("Already registered: " + type.getName());
        }
    }

    public <T> T get(Class<? extends T> type) {
        Objects.requireNonNull(type, "type must not be null");
        Object object = singletons.get(type);

        if (object == null) {
            Supplier<?> supplier = factories.get(type);
            if (supplier == null) {
                throw new IllegalStateException("Factory is null");
            }

            Object newObject = supplier.get();
            if (newObject == null) {
                throw new IllegalStateException("Object is null");
            }

            synchronized (singletons) {
                Object existingObject = singletons.get(type);
                if (existingObject == null) {
                    object = newObject;
                    singletons.put(type, newObject);
                } else {
                    object = existingObject;
                }
            }
        }
        return type.cast(object);
    }
}
