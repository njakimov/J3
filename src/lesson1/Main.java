package lesson1;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Box box1 = new Box(1);
        Box box2 = new Box(2);
        int intBox = 0;

        intBox = (int) box1.getObj() + (int) box2.getObj();
        System.out.println(intBox);

        ModernBox<Integer> modernBox1 = new ModernBox<>(3);
        ModernBox<Integer> modernBox2 = new ModernBox<>(3);
        intBox = modernBox1.getObj() + modernBox2.getObj();
        System.out.println(intBox);

        GenericArr<Integer> genericArr1 = new GenericArr<>(1, 2, 2);
        GenericArr<Double> genericArr2 = new GenericArr<>(1D, 2D, 2D);

        System.out.println(genericArr1.isAverageEquals(genericArr2));
        List<String> string1 = List.of("Alice");
        System.out.println(getElementFromCollection(string1));
        List<String> string = Arrays.asList("Alice", "Bob", "Charlie");
        System.out.println(getElementFromCollection(string));
    }

    private static <T> T getType(T type) {
        return type;
    }
    private static <T> T getElementFromCollection(List<T> list) {
        return list.get(0);
    }

    // super можно сам класс и детей, но нельзя родителей
    private static void getElementFromCollection1(List<? super Number> list) {
//        list.add(new Object());
        list.add(0);

    }
    // exteds - имунабельный (неизменяемый, но удалять можно, добавлять нельзя)
    private static void getElementFromCollection2(List<? extends Number> list) {
//        list.add(new Object());
//        list.add(0);
    }
}
