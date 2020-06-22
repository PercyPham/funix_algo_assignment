package vn.edu.funix.algo.data;

public class DoublyLinkedList<T extends Comparable<T>> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoublyLinkedList() {
    }

    public boolean add(T data) {
        addLast(data);
        return true;
    }

    public void add(DoublyLinkedList<T> list) {
        Node<T> node = list.getHead();
        while(node != null) {
            add(node.getData());
            node = node.next();
        }
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

    public DoublyLinkedList<T> clone() {
        DoublyLinkedList<T> cloned = new DoublyLinkedList<>();
        Node<T> node = head;
        while (node != null) {
            cloned.add(node.getData());
            node = node.next();
        }
        return cloned;
    }

    public boolean contains(T data) {
        Node<T> node = head;
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

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
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
        head.setPrevious(null);
        size--;
        return data;
    }

    public T pollLast() {
        if (size == 0) return null;
        T data = tail.getData();
        tail = tail.previous();
        tail.setNext(null);
        size--;
        return data;
    }

    public T set(int index, T data) {
        Node<T> node = getNodeAt(index);
        T dataToReturn = node.getData();
        node.setData(data);
        return dataToReturn;
    }

    public void delete(T data) {
        Node<T> node = head;
        while (node != null) {
            if (node.getData().equals(data)) break;
            node = node.next();
        }

        if (node == null) return;

        if (node == head && node == tail) {
            clear();
            return;
        }

        if (node == head) {
            pollFirst();
            return;
        }

        if (node == tail) {
            pollLast();
            return;
        }

        node.previous().setNext(node.next());
        node.next().setPrevious(node.previous());
        size--;
    }

    public void sort() {
        Node<T> i, j;
        i = head;
        while (i != null) {
            j = i.next();
            while (j != null) {
                if (j.getData().compareTo(i.getData()) < 0) {
                    T t = i.getData();
                    i.setData(j.getData());
                    j.setData(t);
                }
                j = j.next();
            }
            i = i.next();
        }
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<T> node = head;
        for (int i = 0; i < size; i++) {
            array[i] = node.getData();
            node = node.next();
        }
        return array;
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";

        String s = "[" + head.getData().toString();

        Node<T> node = head.next();
        while (node != null) {
            s += "," + node.getData().toString();
            node = node.next();
        }
        s += "]";

        return s;
    }

}
