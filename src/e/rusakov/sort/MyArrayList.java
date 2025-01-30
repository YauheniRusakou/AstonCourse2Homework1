package e.rusakov.sort;

/**
 * Реализация списка на основе масиива
 *
 * @param <E> тип передаваемого значения в список
 */
public class MyArrayList<E extends Comparable<E>> implements MyList<E> {

    private final int SIZE_START = 8; //начальная ёмкость по умолчанию
    private Object[] array; //массив для элементов
    private int size; //количество элементов, которые содержит список

    /**
     * Создает пустой список с начальной ёмкостью восемь.
     */
    public MyArrayList() {
        array = new Object[SIZE_START];
    }

    /**
     * Создает пустой список с указанной начальной ёмкостью.
     *
     * @param size - ночальная ёмкость списка
     */
    public MyArrayList(int size) {
        if (size < 0) {
            throw new IndexOutOfBoundsException("Size is negative: " + size);
        }
        this.size = size;
        array = new Object[size];
    }

    /**
     * Добавляет указанный элемент в конце этого списка.
     *
     * @param e - элемент, который должен быть добавлен в этот список
     */
    @Override
    public void add(E e) {
        if (size == array.length) {
            resizeArray(size + 1);
        }
        array[size++] = e;
    }

    /**
     * Вставляет указанный элемент в указанную позицию в списке.
     *
     * @param e     - элемент, который должен быть добавлен в этот список
     * @param index - порядковый номер элемента, куда нужно добавить
     */
    @Override
    public void add(E e, int index) {
        if (size == array.length) {
            resizeArray(index);
        }
        array[index] = e;
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
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Not index: " + index);
        }
        return (E) array[index];
    }

    /**
     * Удаляет элемент в указанной позиции в этом списке.
     *
     * @param index - порядковый номер элемента, который нужно удалить
     */
    @Override
    public void delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Not index: " + index);
        }
        for (int i = index; i < size; i++) {
            array[i] = array[i + 1]; //смещаем элементы после удаления влево
        }
        array[index] = null;
        size--;
    }

    /**
     * Очищает весь список.
     */
    @Override
    public void clear() {
        size = 0;
        array = null;
    }

    /**
     * Сортируем список при помощи сортировки "Сортировка выбором".
     * Временная сложность O(n^2).
     */
    @Override
    public void sort() {
        for (int i = 0; i < size - 1; i++) {
            int indexMin = i;
            for (int j = i + 1; j < size; j++) {
                if (((E) array[j]).compareTo((E) array[indexMin]) < 0) {
                    indexMin = j;
                }
            }
            var temporary = array[i];
            array[i] = array[indexMin];
            array[indexMin] = temporary;
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
     * Вспомогательный метод для масштабирования списка.
     *
     * @param newLength - длина списка
     */
    private void resizeArray(int newLength) {
        Object[] newArray = new Object[(3 * newLength) / 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    /**
     * Метод для вывода названия и состава списка
     *
     * @return название и состава списка списка
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MyArrayList: [");
        for (int i = 0; i < size - 1; i++) {
            sb.append(array[i]).append(" ");
        }
        sb.append(array[size - 1]);
        sb.append("]");
        return sb.toString();
    }
}
