package vn.edu.funix.algo.data;

public class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoublyLinkedList() {
    }

    public boolean add(T data) {
        addLast(data);
        return true;
    }

    public void addFirst(T data) {
        Node<T> node = new Node<>(data);

        if (size == 0) {
            head = tail = node;
            size = 1;
            return;
        }

        node.setNext(head);
        head.setPrevious(node);
        head = node;
        size++;
    }

    public void addLast(T data) {
        if (size == 0) {
            addFirst(data);
            return;
        }

        Node<T> node = new Node<>(data);
        node.setPrevious(tail);
        tail.setNext(node);
        tail = node;
        size++;
    }

    public int size() {
        return this.size;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public DoublyLinkedList clone() {
        DoublyLinkedList<T> cloned = new DoublyLinkedList<>();
        Node<T> node = head;
        while (node != null) {
            cloned.add(node.getData());
            node = node.next();
        }
        return cloned;
    }

    public boolean contains(T data) {
        Node node = head;
        while (node != null) {
            if (node.getData().equals(data)) {
                return true;
            }
            node = node.next();
        }
        return false;
    }

    public T get(int index) throws IndexOutOfBoundsException {
        return getNodeAt(index).getData();
    }

    private Node<T> getNodeAt(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Received index " + index + " but list has size of " + size);
        }

        int i = 0;
        Node<T> node = head;
        while (node != null) {
            if (i == index) return node;
            i++;
            node = node.next();
        }

        throw new IndexOutOfBoundsException("Won't reach this line of code");
    }

    public T getFirst() {
        if (size == 0) return null;
        return head.getData();
    }

    public T getLast() {
        if (size == 0) return null;
        return tail.getData();
    }

    public int indexOf(T data) {
        Node<T> node = head;
        int i = -1;
        while (++i < size) {
            if (node.getData().equals(data)) return i;
            node = node.next();
        }
        return -1;
    }

    public int lastIndexOf(T data) {
        Node<T> node = tail;
        int i = size;
        while (--i >= 0) {
            if (node.getData().equals(data)) return i;
            node = node.previous();
        }
        return -1;
    }

    public T peek() {
        return peekFirst();
    }

    public T peekFirst() {
        if (size == 0) return null;
        return head.getData();
    }

    public T peekLast() {
        if (size == 0) return null;
        return tail.getData();
    }

    public T poll() {
        return pollFirst();
    }

    public T pollFirst() {
        if (size == 0) return null;
        T data = head.getData();
        head = head.next();
        if (head != null) head.setPrevious(null);
        size--;
        return data;
    }

    public T pollLast() {
        if (size == 0) return null;
        T data = tail.getData();
        tail = tail.previous();
        if (tail != null) tail.setNext(null);
        size--;
        return data;
    }

    public T set(int index, T data) {
        Node<T> node = getNodeAt(index);
        T dataToReturn = node.getData();
        node.setData(data);
        return dataToReturn;
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<T> node = head;
        for (int i = 0; i < size; i++ ) {
            array[i] = node.getData();
            node = node.next();
        }
        return array;
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";

        String s = "[" + head.getData().toString();

        Node node = head.next();
        while (node != null) {
            s += "," + node.getData().toString();
            node = node.next();
        }
        s += "]";

        return s;
    }

}

class Node<T> {
    private T data;
    private Node next;
    private Node previous;

    Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node next() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node previous() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }
}
