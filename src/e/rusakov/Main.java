package e.rusakov;

import e.rusakov.sort.*;

public class Main {

    public static void main(String[] args) {

        MyList<String> myArrayList= new MyArrayList<>();
        myArrayList.add("fff"); //добаление элемета
        myArrayList.add("ppp");
        myArrayList.add("aaa");
        myArrayList.add("yyy", 3); //добавление по индексу
        myArrayList.add("www", 4);
        myArrayList.add("еее", 5);
        myArrayList.add("aaa", 6);
        myArrayList.add("vvv", 7);
        System.out.println("Создан список = " + myArrayList);

        System.out.println("Получить элемент по индексу \"2\" = " + myArrayList.get(2));
        System.out.println("Длина myArrayList = " + myArrayList.getSize());
        System.out.println("До сортировки = " + myArrayList);
        myArrayList.sort();
        System.out.println("После сортировки = " + myArrayList);

        myArrayList.add("RRR", 18); //расширение myArrayList через индекс за пределами
        System.out.println("Новая длина = " + myArrayList.getSize());
        myArrayList.delete(1); //удаление по индексу
        System.out.println(myArrayList);

        System.out.println("=======================================================");

        MyList<String> myLinkedList = new MyLinkedList<>();
        myLinkedList.add("fff"); //добаление элемета
        myLinkedList.add("ppp");
        myLinkedList.add("bbb");
        myLinkedList.add("aaa");
        myLinkedList.add("yyy", 0); //добавление по индексу
        myLinkedList.add("www", 3);
        System.out.println("Создан список = " + myLinkedList);

        System.out.println("Получить элемент по индексу \"2\" = " + myLinkedList.get(2));
        System.out.println("Длина myLinkedList = " + myLinkedList.getSize());
        myLinkedList.delete(2); //удаляем элемент

        System.out.println("До сортировки = " + myLinkedList);
        myLinkedList.sort();
        System.out.println("После сортировки = " + myLinkedList);
    }
}
