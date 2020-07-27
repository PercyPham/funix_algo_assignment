package vn.edu.funix.algo.data;

public class Stack<T> {
    DoublyLinkedList<T> list = new DoublyLinkedList<>();

    public int size() {
        return list.size();
    }

    public void push(T val) {
        list.addLast(val);
    }

    public T pull() {
        return list.pollLast();
    }

    public T peek() {
        return list.getLast();
    }
}
