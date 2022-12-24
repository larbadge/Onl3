package com.mikhalov.container;

import java.util.Iterator;
import java.util.Optional;

public class LinkedListRealisation<T> implements Iterable<T> {

    private final Node<T> first = new Node<>();
    private final Node<T> last = new Node<>();
    private int size = 0;

    public LinkedListRealisation() {
        first.next = last;
        last.prev = first;
    }

    private static class Node<T> {
        private Node<T> prev;
        private Node<T> next;
        private T value;
    }

    private class Itr implements Iterator<T> {
        Node<T> cursor;

        Itr() {
            cursor = first;
        }

        @Override
        public boolean hasNext() {
            return cursor.next != null;
        }

        @Override
        public T next() {
            cursor = cursor.next;
            return cursor.value;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    public void printAll() {
        Optional.of(this)
                .filter(list -> size > 0)
                .ifPresentOrElse(a -> a.forEach(System.out::println),
                        () -> System.out.println("List is empty"));
    }

    public void add(T value) {
        Node<T> node = new Node<>();
        node.value = value;
        size++;

        Node<T> lastNode = last.prev;
        if (lastNode != first) {
            node.prev = lastNode;
        }
        lastNode.next = node;
        last.prev = node;
    }

    public void addFirst(T value) {
        if (size == 0) {
            add(value);
            return;
        }

        Node<T> node = new Node<>();
        node.value = value;
        size++;

        node.next = first.next;
        first.next = node;
    }

    public T get(int index) {
        return checkSizeAndGetNode(index).value;
    }

    public void delete(int index) {
        Node<T> node = checkSizeAndGetNode(index);
        Node<T> prevNode = node.prev;
        Node<T> nextNode = node.next;

        if (size == 1) {
            first.next = last;
            last.prev = first;
            size--;
        } else if (index == 0) {
            first.next = nextNode;
            nextNode.prev = null;
            node.next = null;
            size--;
        } else if (index == getSize() - 1) {
            last.prev = prevNode;
            prevNode.next = null;
            node.prev = null;
            size--;
        } else {
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            node.next = null;
            node.prev = null;
            size--;
        }
    }

    public void deleteFirst() {
        delete(0);
    }

    public void deleteLast() {
        delete(getSize() - 1);
    }

    public void deleteAllNullValues() {
        Node<T> node = first.next;
        for (int i = 0; node != null; i++) {
            if (node.value == null) {
                node = node.next;
                delete(i--);
                continue;
            }
            node = node.next;
        }
    }

    public void insert(T value, int index) {
        if (size == 0 && index == 0) {
            add(value);
            return;
        }
        if (size != 0 && index == 0) {
            addFirst(value);
            return;
        }

        Node<T> node = checkSizeAndGetNode(index);
        Node<T> newNode = new Node<>();
        Node<T> prevNode = node.prev;
        newNode.value = value;

        newNode.next = node;
        newNode.prev = prevNode;
        prevNode.next = newNode;
        node.prev = newNode;

        size++;
    }

    public int getSize() {
        return size;
    }

    private Node<T> checkSizeAndGetNode(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("List's size =" + " " + size);
        }
        Node<T> node = first.next;
        while (index-- > 0) {
            node = node.next;
        }
        return node;
    }

}
