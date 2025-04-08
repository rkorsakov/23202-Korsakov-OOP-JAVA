package factory.worker;

import threadpool.Task;

public class Worker {
    private Task assemblyTask;

    public void performTask() {
        assemblyTask.execute();
    }

    public void setTask(Task task) {
        assemblyTask = task;
    }
}