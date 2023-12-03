package Week2.Interview;

import java.util.Stack;

public class MaxStack {
    private Stack<Double> stack;        // Stack to store the elements
    private Stack<Double> maxStack;     // Stack to store the max elements
    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(double value) {
        stack.push(value);

        if (maxStack.isEmpty() || value >= maxStack.peek()) {
            maxStack.push(value);
        }
    }

    public double pop() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty.");
        }

        double value = stack.pop();

        if (value == maxStack.peek()) {
            maxStack.pop();
        }

        return value;
    }

    public double getMax() {
        if (maxStack.isEmpty()) {
            throw new IllegalStateException("Stack is empty.");
        }

        return maxStack.peek();
    }
}
