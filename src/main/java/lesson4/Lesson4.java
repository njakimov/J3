package lesson4;

public class Lesson4 {
    private final Object monitor = new Object();

    public Lesson4() {

    }

    public void sendData() {
        synchronized (monitor) {
            System.out.println("Отправка данных и ожидание");
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void prepareData() {
        synchronized (monitor) {
            System.out.println("Данные подготовлены");
            monitor.notify();
        }
    }
}
