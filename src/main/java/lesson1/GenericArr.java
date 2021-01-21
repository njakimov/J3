package lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class GenericArr<T extends Number> {
    private T[] arr;

    // нельзя:
    // T t = new T();
    // T[] t = new T[0];
    // исключения нельзя (отнаследоваться от исключений)
    // static fields

    public GenericArr(T... arr) {
        this.arr = arr;
    }

    public T[] getArr() {
        return arr;
    }

    public void setArr(T[] arr) {
        this.arr = arr;
    }

    public double getAverage() {
        double average = 0.0;
        // обход объекта
        for (T t : arr) {
            average += t.doubleValue();
        }
        // обход массива
        for (int i = 0; i < arr.length; i++) {
            average += arr[i].doubleValue();
        }
        return average / arr.length;
    }

    public boolean isAverageEquals(GenericArr<?> box) {
        return Math.abs(this.getAverage() - box.getAverage()) < 0.001;
    }

    // точность в java кривая
    public static void main(String[] args) {
        double f1 = 0.7;
        double f2 = 0.0;
        for (int i = 0; i < 70; i++) {
            f2 += 0.01;
        }
        System.out.println(f1);
        System.out.println(f2);
        System.out.println(f1 == f2);

    }

    /**
     * изменение элементов массива местам
     *
     * @param idx1 - индекс места 1
     * @param idx2 - индекс места 2
     */
    public void swap(int idx1, int idx2) {
        T temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }


    /**
     * Преобразование array в arrayList
     * @return возвращает перобразованный arrayList
     */
    public ArrayList toArraList() {
        ArrayList arrList = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            arrList.add(arr[i]);
        }
        return arrList;
    }

    @Override
    public String toString() {
        return "GenericArr{" +
                "arr=" + Arrays.toString(arr) +
                '}';
    }
}
