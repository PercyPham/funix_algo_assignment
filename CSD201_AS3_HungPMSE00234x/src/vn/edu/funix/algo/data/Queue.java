package vn.edu.funix.algo.data;

public class Queue<T> {
    DoublyLinkedList<T> list = new DoublyLinkedList<>();

    public int size() {
        return list.size();
    }

    public boolean enqueue(T val) {
        return list.add(val);
    }

    public T dequeue() {
        return list.pollFirst();
    }
}
