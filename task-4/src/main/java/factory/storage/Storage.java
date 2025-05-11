package factory.storage;

import factory.controller.CarStorageController;
import factory.product.Product;

import java.util.LinkedList;
import java.util.Queue;

public class Storage<T extends Product> {
    private final Queue<T> products;
    private final int capacity;
    private final Object lock = new Object();
    private CarStorageController controller;

    public Storage(int capacity) {
        this.capacity = capacity;
        this.products = new LinkedList<>();
    }

    public void setController(CarStorageController controller) {
        this.controller = controller;
    }

    public void put(T product) throws InterruptedException {
        synchronized (lock) {
            while (products.size() >= capacity) {
                lock.wait();
            }
            products.add(product);
            lock.notifyAll();
        }
    }

    public T take() throws InterruptedException {
        T product;
        synchronized (lock) {
            while (products.isEmpty()) {
                lock.wait();
            }
            product = products.poll();
            lock.notifyAll();
        }
        if (controller != null) {
            controller.notifyController();
        }
        return product;
    }

    public int getSize() {
        synchronized (lock) {
            return products.size();
        }
    }
}