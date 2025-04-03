package threadpool;

import java.util.ArrayDeque;
import java.util.Queue;

public class ThreadPool {
    private final Queue<Task> taskQueue = new ArrayDeque<>();
    private final Thread[] workers;
    private volatile boolean isRunning = true;

    public ThreadPool(int threadCount) {
        this.workers = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            workers[i] = new WorkerThread("WorkerThread-" + (i + 1));
            workers[i].start();
        }
    }

    public synchronized void addTask(Task task) {
        if (!isRunning) throw new IllegalStateException("ThreadPool is stopped");
        taskQueue.add(task);
        notify();
    }

    public synchronized void shutdown() {
        isRunning = false;
        for (Thread worker : workers) {
            worker.interrupt();
        }
        notifyAll();
    }

    private class WorkerThread extends Thread {
        public WorkerThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (isRunning || !taskQueue.isEmpty()) {
                Task task;
                synchronized (ThreadPool.this) {
                    while (taskQueue.isEmpty() && isRunning) {
                        try {
                            ThreadPool.this.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    if (taskQueue.isEmpty()) return;
                    task = taskQueue.poll();
                }
                task.execute();
            }
        }
    }
}