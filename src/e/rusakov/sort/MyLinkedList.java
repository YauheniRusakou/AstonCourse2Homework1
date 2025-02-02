package e.rusakov.sort;

import java.util.NoSuchElementException;

/**
 * Реализация списка на основе связанного списка
 *
 * @param <E> тип передаваемого значения в список
 */
public class MyLinkedList<E extends Comparable<E>> implements MyList<E> {

    private Node<E> head; //Указатель на головной узел
    private Node<E> tail; //Указатель на хвостовой узел
    private int size = 0; //количество элементов, которые содержит список

    /**
     * Добавляет указанный элемент в конце этого списка.
     *
     * @param e - элемент, который должен быть добавлен в этот список
     */
    @Override
    public void add(E e) {
        Node<E> newNode = new Node<>(e);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * Вставляет указанный элемент в указанную позицию в списке.
     *
     * @param e     - элемент, который должен быть добавлен в этот список
     * @param index - порядковый номер элемента, куда нужно добавить
     */
    @Override
    public void add(E e, int index) {
        indexException(index);
        Node<E> newNode = new Node<>(e);
        int i = 0;
        Node<E> headNew = head;
        while (i < index) {
            headNew = headNew.next;
            i++;
        }
        newNode.next = headNew.next;
        headNew.next = newNode;
        size++;
    }

    /**
     * Возвращает элемент в указанной позиции в этом списке.
     *
     * @param index - индекс элемента для возврата
     * @return элемент в указанной позиции в этом списке
     */
    @Override
    public E get(int index) {
        indexException(index);
        Node<E> node = head.next;
        while (index >= 0) {
            node = node.next;
            index--;
        }
        return node.value;
    }

    /**
     * Возвращает первый элемент в списке.
     *
     * @return первый элемент в списке
     */
    public E getFirst() {
        Node<E> h = head;
        if (h == null)
            throw new NoSuchElementException();
        return h.value;
    }

    /**
     * Возвращает последний элемент в списке.
     *
     * @return последний элемент в списке
     */
    public E getLast() {
        Node<E> t = tail;
        if (t == null)
            throw new NoSuchElementException();
        return t.value;
    }

    /**
     * Удаляет элемент в указанной позиции в этом списке.
     *
     * @param index - порядковый номер элемента, который нужно удалить
     */
    @Override
    public void delete(int index) {
        indexException(index);
        if (index == 0) {
            head = head.next;
            if (head == null) tail = null;
        } else {
            Node<E> node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            node.next = node.next.next;
            if (node.next == null) tail = node;
        }
        size--;
    }

    /**
     * Очищает весь список.
     */
    @Override
    public void clear() {
        if (head != null) {
            Node<E> current = head;
            while (current != null) {
                Node<E> next = current.next;
                current.value = null;
                current.next = null;
                current = next;
            }
        }
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Сортируем список при помощи сортировки "Сортировки пузырьком".
     * Временная сложность O(n^2).
     */
    @Override
    public void sort() {
        Node<E> current;
        Node<E> index = null;
        E temp;
        if (head == null) {
            throw new NullPointerException("List is empty");
        } else {
            // проходимся по списку
            for (current = head; current.next != null; current = current.next) {
                // проходимся по несортированной части списка
                for (index = current.next; index != null; index = index.next) {
                    // Меняем местами данные, если текущий элемент больше следующего
                    if (current.value.compareTo(index.value) > 0) {
                        temp = current.value;
                        current.value = index.value;
                        index.value = temp;
                    }
                }
            }
        }
    }

    /**
     * Метод возвращает длину списка.
     *
     * @return - длина списка
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * Метод для вывода названия и состава списка
     *
     * @return название и состава списка списка
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MyLinkedList: [");
        Node<E> node = head;
        while (node != null) {
            sb.append(node.value).append(" ");
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Вспомогательный метод, который создаёт Exception, если индекс не соответствует.
     *
     * @param index - порядковый номер элемента
     */
    private void indexException(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Not index: " + index);
        }
    }

    /**
     * Класс для узла связанного списка.
     *
     * @param <E> - тип передаваемого значения в список
     */
    private static class Node<E> {

        E value;
        Node<E> next;

        Node(E value) {
            this.value = value;
        }
    }
}
