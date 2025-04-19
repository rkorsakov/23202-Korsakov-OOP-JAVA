package factory.storage;

import factory.product.Product;

import java.util.LinkedList;
import java.util.Queue;

public class Storage<T extends Product> {
    private final Queue<T> products;
    private final int capacity;
    private final Object lock = new Object();

    public Storage(int capacity) {
        this.capacity = capacity;
        this.products = new LinkedList<>();
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
        synchronized (lock) {
            while (products.isEmpty()) {
                lock.wait();
            }
            T product = products.poll();
            lock.notifyAll();
            return product;
        }
    }

    public int getSize() {
        synchronized (lock) {
            return products.size();
        }
    }
}