package ua.procamp;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}. In order to keep track on nodes, {@link LinkedList} keeps a reference to a head node.
 *
 * @param <T> generic type parameter
 */
public class LinkedList<T> implements List<T> {

    private Node head;
    private int size;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> List<T> of(T... elements) {
        LinkedList<T> list = new LinkedList<>();
        Node prev = null;
        for (T element : elements) {
            list.size++;
            Node<T> node = new Node<>(element);
            if (list.head == null) {
                list.head = node;
                prev = list.head;
            } else {
                prev.next = node;
                prev = node;
            }

        }
        return list;
    }

    /**
     * Adds an element to the end of the list
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        add(0, element);
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        size++;
        Node newElem = new Node<>(element);

        if (head == null) {
            head = newElem;
            return;
        }

        if (index == 0) {
            newElem.next = head;
            head = newElem;
            return;
        }

        Node prevElem = getPrevElem(index);
        newElem.next = prevElem.next;
        prevElem.next = newElem;

    }

    private Node getPrevElem(int index) {
        Node tempNode = head;
        for (int i = 1; i < index; i++) {
            tempNode = tempNode.next;
        }
        return tempNode;
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> node = new Node<>(element);
        if (index == 0) {
            node.next = head.next;
            head = node;
            return;
        }

        Node prevNode = getPrevElem(index);
        node.next = prevNode.next != null ? prevNode.next.next : null;
        prevNode.next = node;
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
        if (head == null || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return index == 0 ? (T) head.value : (T) getPrevElem(index).next.value;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     */
    @Override
    public void remove(int index) {
        if (head == null) {
            throw new IndexOutOfBoundsException();
        }

        size--;
        if (index == 0) {
            head = head.next;
        }

        Node prevNode = getPrevElem(index);
        prevNode.next = prevNode.next.next;
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        if (head == null) {
            return false;
        }
        Node node = head;
        while (node.next != null) {
            if (node.value == element) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        Node(T value) {
            this.value = value;
        }

    }
}
