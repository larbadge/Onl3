package com.mikhalov.container;

import java.util.Comparator;
import java.util.Objects;

public class BinaryTreeRealisation<T> {
    Comparator<T> comparator;

    public BinaryTreeRealisation() {
        comparator = Comparator.comparingInt(Object::hashCode);
    }

    public BinaryTreeRealisation(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    protected Node root;


    public void add(T value) {
        root = addRecursive(root, value);
    }

    public boolean isContainsValue(T value) {
        return containsValueRecursive(root, value);
    }

    public void delete(T value) {
        root = deleteRecursive(root, value);
    }

    public void traverseInOrder() {
        traverseInOrder(root);
    }

    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.println(node.value);
            traverseInOrder(node.right);
        }
    }

    private Node deleteRecursive(Node current, T value) {
        if (current == null) {
            return null;
        }
        if (current.compareTo(value) == 0) {
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.right == null) {
                return current.left;
            }
            if (current.left == null) {
                return current.right;
            }
            T extremeLeftValue = findSmallestValue(current.right);
            current.value = extremeLeftValue;
            current.right = deleteRecursive(current.right, extremeLeftValue);
            return current;
        }
        if (current.compareTo(value) > 0) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }

    private T findSmallestValue(Node root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }

    private boolean containsValueRecursive(Node current, T value) {
        if (current == null) {
            return false;
        }
        if (current.compareTo(value) == 0) {
            return true;
        }
        return current.compareTo(value) > 0
                ? containsValueRecursive(current.left, value)
                : containsValueRecursive(current.right, value);
    }

    private Node addRecursive(Node current, T value) {
        if (current == null) {
            return new Node(value);
        }
        if (current.compareTo(value) > 0) {
            current.left = addRecursive(current.left, value);
        } else if (current.compareTo(value) < 0) {
            current.right = addRecursive(current.right, value);
        } else {
            return current;
        }
        return current;
    }

    protected class Node implements Comparable<T> {
        protected Node left;
        protected Node right;
        protected T value;

        private Node(T value) {
            this.value = value;
        }

        @Override
        public int compareTo(T o) {
            return Objects.compare(value, o, comparator);
        }
    }

}
