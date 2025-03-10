package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ExecutionContext {
    private final Stack<Double> stack;
    private final Map<String, Double> parameters;

    public ExecutionContext() {
        this.stack = new Stack<>();
        this.parameters = new HashMap<>();
    }

    public Double popStack() {
        return stack.pop();
    }

    public Double peekStack() {
        return stack.peek();
    }

    public void pushStack(Double value) {
        stack.push(value);
    }

    public boolean isStackEmpty() {
        return stack.isEmpty();
    }

    public int getStackSize() {
        return stack.size();
    }

    public void setParameter(String key, Double value) {
        if (value == null) {
            parameters.remove(key);
        } else {
            parameters.put(key, value);
        }
    }

    public Double getParameter(String key) {
        return parameters.get(key);
    }

    public boolean hasParameter(String key) {
        return parameters.containsKey(key);
    }

    public void removeParameter(String key) {
        parameters.remove(key);
    }
}