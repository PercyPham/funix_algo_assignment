package vn.edu.funix.algo.data;

import vn.edu.funix.algo.Person;

public class Traverser<T extends Comparable<T>> {
    public Object[] inOrder(BinarySearchTree<T> bts) {
        DoublyLinkedList<T> list = new DoublyLinkedList<>();
        return traverseInOrder(bts, list).toArray();
    }

    private DoublyLinkedList<T> traverseInOrder(BinarySearchTree<T> bts, DoublyLinkedList<T> list) {
        if (bts == null) return list;
        if (bts.left != null) {
            traverseInOrder(bts.left, list);
        }
        list.add(bts.value);
        if (bts.right != null) {
            traverseInOrder(bts.right, list);
        }
        return list;
    }

    public Object[] breadthFirstTraversal(BinarySearchTree<T> bts) {
        DoublyLinkedList<T> list = new DoublyLinkedList<>();
        Queue<BinarySearchTree<T>> queue = new Queue<>();
        queue.enqueue(bts);
        return traverseBreadthFirstTraversal(queue, list).toArray();
    }

    private DoublyLinkedList<T> traverseBreadthFirstTraversal(
            Queue<BinarySearchTree<T>> queue,
            DoublyLinkedList<T> list
    ) {
        BinarySearchTree<T> bts = queue.dequeue();
        if (bts == null) return list;

        list.add(bts.value);
        if (bts.left != null) queue.enqueue(bts.left);
        if (bts.right != null) queue.enqueue(bts.right);

        traverseBreadthFirstTraversal(queue, list);

        return list;
    }
}
