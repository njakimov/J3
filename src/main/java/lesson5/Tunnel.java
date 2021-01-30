package lesson5;

import static lesson5.MainClass.SEMAPHORE;
import static lesson5.MainClass.MAX_TUNNEL_CAR_COUNT;

public class Tunnel extends Stage {
    private static final boolean[] TUNEL_PLACES = new boolean[MAX_TUNNEL_CAR_COUNT];

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);

                SEMAPHORE.acquire();

                int tunnelPlaceNumber = -1;

                synchronized (TUNEL_PLACES) {
                    for (int i = 0; i < MAX_TUNNEL_CAR_COUNT; i++)
                        if (!TUNEL_PLACES[i]) {
                            TUNEL_PLACES[i] = true;
                            tunnelPlaceNumber = i;
                            System.out.printf("%s въехал в туннель %d.\n", c.getName(), i);
                            break;
                        }
                }

                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);

                synchronized (TUNEL_PLACES) {
                    TUNEL_PLACES[tunnelPlaceNumber] = false;
                }

                SEMAPHORE.release();
                System.out.printf("%s покинул туннель.\n", c.getName());


            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
