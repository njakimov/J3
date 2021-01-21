package lesson1;

import java.io.*;
import java.util.*;

public class HomeWorkLesson1 {
    //1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
    //2. Написать метод, который преобразует массив в ArrayList;
    //3. Большая задача:
    //
    //    Есть классы Fruit -> Apple, Orange (больше фруктов не надо);
    //    Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта,
    //      поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
    //    Для хранения фруктов внутри коробки можно использовать ArrayList;
    //    Сделать метод getWeight(), который высчитывает вес коробки, зная количество фруктов и вес одного фрукта
    //      (вес яблока – 1.0f, апельсина – 1.5f. Не важно, в каких это единицах);
    //    Внутри класса Коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут
    //      в compare в качестве параметра, true – если она равны по весу, false – в противном случае (коробки с яблоками
    //      мы можем сравнивать с коробками с апельсинами);
    //    Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую (помним про сортировку фруктов:
    //      нельзя яблоки высыпать в коробку с апельсинами). Соответственно, в текущей коробке фруктов не остается,
    //          а в другую перекидываются объекты, которые были в этой коробке;
    //    Не забываем про метод добавления фрукта в коробку.
    public static void main(String[] args) {
        GenericArr arr = new GenericArr(1, 2d, 3f, 4, 5);

        //1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
        System.out.println("\nДо замены: " + arr.toString());
        arr.swap(1, 2);
        System.out.println("После замены: " + arr.toString());

        //2. Написать метод, который преобразует массив в ArrayList;
        ArrayList arrList = arr.toArraList();
        System.out.println("\nArrayList: " + arrList.toString());

        //3. Большая задача:
        Apple apple = new Apple();
        Orange orange = new Orange();


        BoxArray box1 = new BoxArray();
        BoxArray box2 = new BoxArray();
        BoxArray box3 = new BoxArray();

        box1.addContentInBox(apple);
        box1.addContentInBox(apple);

        box2.addContentInBox(orange);
        box2.addContentInBox(orange);

        System.out.println("\nПроверка на типы фруктов при добавлении: ");
        box2.addContentInBox(apple);

        System.out.println("\nВес коробки box1: " + box1.getWeight());
        System.out.println("Вес коробки box2: " + box2.getWeight());
        System.out.println("Вес коробки box3: " + box3.getWeight());

        System.out.println("\nВес box1 равен box2: " + box1.compare(box2));

        box3.moveBox(box1);
        System.out.println("\nПереместил коробку с фруктами");
        System.out.println("Вес коробки box1: " + box1.getWeight());
        System.out.println("Вес коробки box2: " + box2.getWeight());
        System.out.println("Вес коробки box3: " + box3.getWeight());

    }
}
