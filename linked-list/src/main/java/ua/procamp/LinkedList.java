package ua.procamp;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}. In order to keep track on nodes, {@link LinkedList} keeps a reference to a head node.
 *
 * @param <T> generic type parameter
 */
public class LinkedList<T> implements List<T> {

    private Node<T> head;
    private int size;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> List<T> of(T... elements) {
        LinkedList list = new LinkedList();
        Node<T> n = null;
        for (T element : elements) {
            list.size++;
            Node<T> node = new Node<>(element);
            if (list.head == null) {
                list.head = node;
                n = list.head;
            } else {
                n.next = node;
                n = node;
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
        Node<T> tNode = new Node<>(element);
        if (head == null) {
            head = tNode;
        } else {
            if (index == 0) {
                tNode.next = head;
                head = tNode;
            } else {
                Node<T> t = head;
                while (index != 1) {
                    t = t.next;
                    index--;
                }
                Node<T> temp = t.next;
                t.next = tNode;
                tNode.next = temp;
            }
        }
        size++;
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
        Node<T> t = head;
        while (index != 1) {
            t = t.next;
            index--;
        }
        Node<T> temp = t.next;
        t.next = node;
        node.next = temp.next;
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
        Node t = head;
        while (index != 0) {
            t = t.next;
            index--;
        }

        return (T) t.value;
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
        Node<T> t = head;
        if (index == 0) {
            head = head.next;
            size--;
            return;
        }
        while (index != 1) {
            t = t.next;
            index--;
        }
        t.next = t.next.next;
        size--;
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
        Node<T> node = head;
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
        if (head == null) {
            return;
        }
        while (head.next != null) {
            head = head.next;
        }
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
