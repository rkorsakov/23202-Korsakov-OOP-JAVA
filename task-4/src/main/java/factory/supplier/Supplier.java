package factory.supplier;

import factory.product.Product;
import factory.storage.Storage;

public class Supplier<T extends Product> extends Thread {
    private final Storage<T> storage;
    private final Class<T> productClass;
    private volatile boolean isRunning = true;
    private int productionTimeMs;
    private int producedCount = 0;

    public Supplier(Storage<T> storage, Class<T> productClass, int productionTimeMs) {
        if (storage == null || productClass == null || productionTimeMs <= 0) {
            throw new IllegalArgumentException("Invalid supplier parameters");
        }
        this.storage = storage;
        this.productClass = productClass;
        this.productionTimeMs = productionTimeMs;
    }

    @Override
    public void run() {
        try {
            while (isRunning && !isInterrupted()) {
                Thread.sleep(productionTimeMs);
                T product = productClass.getDeclaredConstructor().newInstance();
                storage.put(product);
                producedCount++;
                System.out.printf("[%s] Produced: %s\n", getName(), product);
            }
        } catch (InterruptedException e) {
            System.out.printf("[%s] Supplier interrupted\n", getName());
        } catch (Exception e) {
            System.err.printf("[%s] Production error: %s\n", getName(), e.getMessage());
        }
    }

    public void stopSupplier() {
        isRunning = false;
        interrupt();
    }

    public void setProductionTime(int productionTimeMs) {
        if (productionTimeMs <= 0) {
            throw new IllegalArgumentException("Time must be positive");
        }
        this.productionTimeMs = productionTimeMs;
    }

    public int getProducedCount() {
        return producedCount;
    }
}