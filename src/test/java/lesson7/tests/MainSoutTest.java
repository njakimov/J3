package lesson7.tests;

import lesson7.annotations.MyTest;
import lesson7.MainSout;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@MyTest
public class MainSoutTest {
    private MainSout main;

    @BeforeEach
    void setUp() {
        main = new MainSout();
        main.getHello();
    }

    @AfterEach
    void tearDown() {
        main.getBy();
    }


    @Test
    void getMessage1() {
        Assertions.assertEquals("Сообщение1", main.getMessage1());
    }

    @Test
    void getMessage2() {
        Assertions.assertEquals("Сообщение2", main.getMessage2());
    }

    @Test
    void getMessage3() {
        Assertions.assertEquals("Сообщение3", main.getMessage3());
    }

    @Test
    void getMessage4() {
        Assertions.assertEquals("Сообщение4", main.getMessage4());
    }




}