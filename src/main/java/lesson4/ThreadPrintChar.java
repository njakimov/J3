package lesson4;

// 1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.

public class ThreadPrintChar {
    private final Object monitor = new Object();
    private char currentChar = 'C';

    public ThreadPrintChar() {
        System.out.println("1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.");
    }

    public static void main(String[] args) {
        ThreadPrintChar threadPrintChar = new ThreadPrintChar();

        Thread t1 = new Thread(() -> { for (int i = 0; i < 5; i++) threadPrintChar.printChar('A', 'B'); });
        Thread t2 = new Thread(() -> { for (int i = 0; i < 5; i++) threadPrintChar.printChar('B', 'C'); });
        Thread t3 = new Thread(() -> { for (int i = 0; i < 5; i++) threadPrintChar.printChar('C', 'A'); });

        t1.start();
        t2.start();
        t3.start();
    }

    public void printChar(char lastChar, char nextChar) {
        synchronized (monitor) {
            try {
                while (this.currentChar != lastChar) {
                    monitor.wait();
                }
                System.out.print(nextChar);
                this.currentChar = nextChar;
                monitor.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
