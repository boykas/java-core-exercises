package ua.procamp.bst;

import java.util.function.Consumer;

public class RecursiveBinarySearchTree<T extends Comparable> implements BinarySearchTree<T> {
    private Node<T> root;
    private int size = 0;

    @Override
    public boolean insert(T element) {
        root = insertRecurcive(root, element);
        if (root == null) {
            return false;
        }
        size++;
        return true;
    }

    private Node insertRecurcive(Node root, T element) {
        if (root == null) {
            return new Node<>(element);
        }
        if (root.value.compareTo(element) > 0) {
            root.left = insertRecurcive(root.left, element);
        } else if (root.value.compareTo(element) < 0) {
            root.right = insertRecurcive(root.right, element);
        } else {
            return null;
        }

        return root;
    }


    @Override
    public boolean search(T element) {
        return containsNodeRecurcive(root, element);
    }

    private boolean containsNodeRecurcive(Node root, T element) {
        if (root == null) {
            return false;
        }
        if (root.value.compareTo(element) == 0) {
            return true;
        }

        return root.value.compareTo(element) > 0
                ? containsNodeRecurcive(root.left, element)
                : containsNodeRecurcive(root.right, element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return root != null ? height(root) - 1 : 0;
    }

    private int height(Node current) {
        if (current == null) {
            return 0;
        }
        return 1 + Math.max(height(current.left), height(current.right));
    }


    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversalRecursion(root, consumer);
    }

    private void inOrderTraversalRecursion(Node<T> current, Consumer<T> consumer) {
        if (current != null) {
            inOrderTraversalRecursion(current.left, consumer);
            consumer.accept(current.value);
            inOrderTraversalRecursion(current.right, consumer);
        }
    }

    static class Node<T extends Comparable> {
        Node left;
        Node right;
        T value;

        Node(T value) {
            this.value = value;
        }
    }

}
