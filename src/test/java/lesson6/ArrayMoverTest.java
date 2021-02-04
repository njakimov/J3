package lesson6;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayMoverTest {
    private final ArrayMover arrayMover = new ArrayMover();

    @Test
    void getArray41() {
        int[] resultArray = arrayMover.getArray4(new int[]{2, 1, 7, 4, 3});
        Assertions.assertArrayEquals(new int[]{3}, resultArray);
    }

    @Test
    void getArray42() {
        int[] resultArray = arrayMover.getArray4(new int[]{2, 1, 7, 1, 4});
        Assertions.assertArrayEquals(new int[]{}, resultArray);
    }

    @Test
    void getArray43() {
        int[] resultArray = arrayMover.getArray4(new int[]{4, 1, 7, 1, 1});
        Assertions.assertArrayEquals(new int[]{1, 7, 1, 1}, resultArray);
    }

    @Test
    void getArray44() {
        int[] resultArray = arrayMover.getArray4(new int[]{4, 1, 4, 1, 1});
        Assertions.assertArrayEquals(new int[]{1, 1}, resultArray);
    }


    @Test
    void getArray45() {
        Assertions.assertThrows(RuntimeException.class,()->arrayMover.getArray4(new int[]{0, 1, 1, 1, 1}));
    }

    @Test
    void findArray1or4True() {
        assertTrue(arrayMover.findArray1or4(new int[]{4, 1, 4, 1, 1}));
    }
    @Test
    void findArray1or4False1() {
        assertFalse(arrayMover.findArray1or4(new int[]{4, 2, 4, 3, 5}));
    }
    @Test
    void findArray1or4False4() {
        assertFalse(arrayMover.findArray1or4(new int[]{1, 2, 1, 3, 5}));
    }
    @Test
    void findArray1or4False14() {
        assertFalse(arrayMover.findArray1or4(new int[]{8, 2, 5, 3, 5}));
    }

}