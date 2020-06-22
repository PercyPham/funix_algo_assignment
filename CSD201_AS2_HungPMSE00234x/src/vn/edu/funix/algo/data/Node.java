package vn.edu.funix.algo.data;

public class Node<T extends Comparable<T>> {
    private T data;
    private Node<T> next;
    private Node<T> previous;

    Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> next() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> previous() {
        return previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }
}