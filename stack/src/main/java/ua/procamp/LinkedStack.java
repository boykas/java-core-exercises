package ua.procamp;


import ua.procamp.exception.EmptyStackException;

public class LinkedStack<T> implements Stack<T> {
    private Node<T> head;
    private int size;

    @Override
    public void push(T element) {
        if (head == null) {
            head = new Node<>(element);
        } else {
            Node<T> el = new Node<>(element);
            el.next = head;
            head = el;
        }
        size++;
    }

    @Override
    public T pop() {
        if (head == null) {
            throw new EmptyStackException();
        }
        Node<T> el = head;
        head = head.next;
        size--;
        return el.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head==null;
    }

    static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }
}
