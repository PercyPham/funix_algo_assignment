package vn.edu.funix.algo.data;

public class Stack {
    private static final int SIZE = 100;
    private Product[] stack = new Product[SIZE];
    private int top = -1;

    public int size() {
        return top + 1;
    }

    public boolean isFull() {
        return top == SIZE - 1;
    }

    public boolean isEmpty() {
        return top < 0;
    }

    public void push(Product data) {
        stack[++top] = data;
    }

    public Product pop() {
        return stack[top--];
    }
}
