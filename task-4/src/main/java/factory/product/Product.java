package factory.product;

import java.util.UUID;

public abstract class Product {
    private final UUID id = UUID.randomUUID();

    @Override
    public String toString() {
        return String.format("%s <%s>", getClass().getSimpleName(), id);
    }
}