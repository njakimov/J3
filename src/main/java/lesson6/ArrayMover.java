package lesson6;

import java.util.Arrays;
import java.util.logging.Level;

public class ArrayMover {
    // 1. Добавить на серверную сторону чата логирование, с выводом информации о действиях на сервере
    //      (запущен, произошла ошибка, клиент подключился, клиент прислал сообщение/команду).

    // 2. Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
    //      Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов,
    //      идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку,
    //      иначе в методе необходимо выбросить RuntimeException. Написать набор тестов для этого метода
    //      (по 3-4 варианта входных данных).
    //      Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].

    // 3. Написать метод, который проверяет состав массива из чисел 1 и 4.
    //      Если в нем нет хоть одной четверки или единицы, то метод вернет false;
    //      Написать набор тестов для этого метода (по 3-4 варианта входных данных).

    // 4. *Добавить на серверную сторону сетевого чата логирование событий - не сделано, т.к. события в данной версии
    //      чата не реализованы, а переписывать данный вариант чата мне лень. В связи с чем и прошу понять и простить)))

    private static final LogEvent logger = new LogEvent(ArrayMover.class.getName());

    public static void main(String[] args) throws RuntimeException {

        getArray4(new int[]{2, 1, 7, 2, 3});
    }

    // 2. Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
    //      Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов,
    //      идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку,
    //      иначе в методе необходимо выбросить RuntimeException. Написать набор тестов для этого метода
    //      (по 3-4 варианта входных данных).
    //      Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
    public static int[] getArray4(int[] ints) {
        logger.log(Level.INFO, "---------------");
        logger.log(Level.INFO, Arrays.toString(ints));
        boolean writeStart = false;
        int[] newArray = new int[0];
        int j = -1;

        for (int i = 0; i < ints.length; i++) {
            if (ints[i] == 4) {
                writeStart = true;
                j = i + 1;
            }
        }

        logger.log(Level.INFO, "" + j);

        if (writeStart) {
            newArray = new int[ints.length - j];
            System.arraycopy(ints, j, newArray, 0, ints.length - j);
            logger.log(Level.INFO, Arrays.toString(newArray));
        }
        if (!writeStart) {
            logger.log(Level.SEVERE, "Входной массив не содержит 4");
            throw new RuntimeException("Входной массив не содержит 4");
        }

        return newArray;
    }

    // 3. Написать метод, который проверяет состав массива из чисел 1 и 4.
    //      Если в нем нет хоть одной четверки или единицы, то метод вернет false;
    //      Написать набор тестов для этого метода (по 3-4 варианта входных данных).
    public static boolean findArray1or4(int[] ints) {
        boolean isset1 = false;
        boolean isset4 = false;

        for (int i = 0; i < ints.length; i++) {
            switch (ints[i]) {
                case 1: {
                    isset1 = true;
                    break;
                }
                case 4: {
                    isset4 = true;
                    break;
                }
            }
            if (isset4 && isset1) {
                return true;
            }
        }
        return false;
    }
}
