package vn.edu.funix.algo.data;

public class BinarySearchTree<T extends Comparable<T>> {
    T value;
    BinarySearchTree<T> left;
    BinarySearchTree<T> right;

    public BinarySearchTree() {}

    public BinarySearchTree(T val) {
        this.value = val;
    }

    public boolean contains(T val) {
        if (this.value == null) return false;

        if (this.value.compareTo(val) > 0) {
            if (this.left == null) return false;
            else return this.left.contains(val);
        }

        if (this.value.compareTo(val) < 0) {
            if (this.right == null) return false;
            else return this.right.contains(val);
        }

        return true;
    }

    public BinarySearchTree<T> insert(T val) {
        if (this.value == null) {
            this.value = val;
            return this;
        }

        if (this.value.compareTo(val) > 0) {
            if (this.left == null) this.left = new BinarySearchTree(val);
            else this.left.insert(val);
        } else {
            if (this.right == null) this.right = new BinarySearchTree(val);
            else this.right.insert(val);
        }

        return this;
    }

    public T search(T val) {
        if (this.value == null) return null;

        if (this.value.compareTo(val) > 0) {
            if (this.left == null) return null;
            else return this.left.search(val);
        }

        if (this.value.compareTo(val) < 0) {
            if (this.right == null) return null;
            else return this.right.search(val);
        }

        return this.value;
    }

    public BinarySearchTree<T> balance() {
        BinarySearchTree<T> balancedBts = new BinarySearchTree<>();

        Traverser<T> traverse = new Traverser<>();
        Object[] arr = traverse.inOrder(this);

        balance(balancedBts, arr, 0, arr.length - 1);

        return balancedBts;
    }

    private void balance(BinarySearchTree<T> bts, Object[] data, int first, int last) {
        if (first > last) return;

        int middle = (first + last) / 2;
        bts.insert((T) data[middle]);

        balance(bts, data, first, middle - 1);
        balance(bts, data, middle + 1, last);
    }

    public void remove(T val) {
        remove(null, this, val);
    }

    private void remove(BinarySearchTree<T> parent, BinarySearchTree<T> node, T val) {
        if (node.value == null) return; // empty BTS

        if (parent == null && node.left == null && node.right == null && node.value.equals(val)) {
            // single node tree
            node.value = null;
            return;
        }

        if (!node.value.equals(val)) {
            if (node.value.compareTo(val) > 0 && node.left != null) {
                remove(node, node.left, val);
            }
            if (node.value.compareTo(val) < 0 && node.right != null) {
                remove(node, node.right, val);
            }
            return;
        }

        if (node.left == null && node.right == null) {
            if (node.equals(parent.left)) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            return;
        }

        if (node.right != null) {
            NodePair pair = findSmallestNode(node, node.right);
            node.value = pair.node.value;
            remove(pair.parent, pair.node, pair.node.value);
        } else {
            NodePair pair = findBiggestNode(node, node.left);
            node.value = pair.node.value;
            remove(pair.parent, pair.node, pair.node.value);
        }
    }

    class NodePair {
        BinarySearchTree<T> parent;
        BinarySearchTree<T> node;

        public NodePair(BinarySearchTree<T> parent, BinarySearchTree<T> node) {
            this.parent = parent;
            this.node = node;
        }
    }

    private NodePair findSmallestNode(BinarySearchTree<T> parent, BinarySearchTree<T>  node) {
        if (node.left != null) {
            return findSmallestNode(node, node.left);
        }
        return new NodePair(parent, node);
    }

    private NodePair findBiggestNode(BinarySearchTree<T> parent, BinarySearchTree<T>  node) {
        if (node.right != null) {
            return findSmallestNode(node, node.right);
        }
        return new NodePair(parent, node);
    }

}

















