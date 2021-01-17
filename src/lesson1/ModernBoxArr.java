package lesson1;

public class ModernBoxArr<T extends Number> {
    private T[] arr;

    // нельзя:
    // T t = new T();
    // T[] t = new T[0];
    // исключения нельзя (отнаследоваться от исключений)
    // static fields

    public ModernBoxArr(T... arr) {
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

    public boolean isAverageEquals(ModernBoxArr<?> box) {
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
}
