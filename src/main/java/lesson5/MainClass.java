package lesson5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainClass {
    // Все участники должны стартовать одновременно, несмотря на то, что на подготовку у каждого их них уходит разное время.
    // В тоннель не может заехать одновременно больше половины участников (условность).
    // Попробуйте все это синхронизировать.
    // Только после того, как все завершат гонку, нужно выдать объявление об окончании.
    // Можете корректировать классы (в т.ч. конструктор машин) и добавлять объекты классов из пакета util.concurrent.

    // Все участники должны стартовать одновременно, несмотря на разное время подготовки.
    // В тоннель не может одновременно заехать больше половины участников (условность).
    // Попробуйте все это синхронизировать.
    // Первый участник, пересекший финишную черту, объявляется победителем (в момент пересечения этой самой черты).
    // Победитель должен быть только один (ситуация с 0 или 2+ победителями недопустима).
    // Когда все завершат гонку, нужно выдать объявление об окончании.
    // Можно корректировать классы (в том числе конструктор машин) и добавлять объекты классов из пакета java.util.concurrent.

    public static final int CARS_COUNT = 4;
    public static final int MAX_TUNNEL_CAR_COUNT = 2;
    public static boolean WINNER_IS_SET = false;
    public static final Lock FINISH_LINE = new ReentrantLock();
    public static final CountDownLatch END_RACE = new CountDownLatch(CARS_COUNT);
    public static final Semaphore SEMAPHORE = new Semaphore(MAX_TUNNEL_CAR_COUNT, true);
    public static final CyclicBarrier BARRIER = new CyclicBarrier(CARS_COUNT, new StartRace());

    public static void main(String[] args) throws InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        while (END_RACE.getCount() > 0) {
            Thread.sleep(1);
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }

    public static void startRace() {

    }
    public static class StartRace implements Runnable {
        @Override
        public void run() {
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        }
    }
}
