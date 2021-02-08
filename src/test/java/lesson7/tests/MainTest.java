package lesson7.tests;

import lesson7.MainSout;
import lesson7.annotations.MyTest;
import lesson7.annotations.Test;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


@MyTest
public class MainTest {
    private MainSout main;

    @BeforeSuite
    void setUp() {
        main = new MainSout();
        main.getHello();
    }

    @AfterSuite
    void tearDown() {
        main.getBy();
    }


    @Test(priority = 0)
    public void getMessage1() {
        Assertions.assertEquals("Сообщение1", main.getMessage1());
    }

    @Test(priority = 1)
    public void getMessage2() {
        Assertions.assertEquals("Сообщение2", main.getMessage2());
    }

    @Test(priority = 2)
    public void getMessage3() {
        Assertions.assertEquals("Сообщение3", main.getMessage3());
    }

    @Test(priority = 3)
    public void getMessage4() {
        Assertions.assertEquals("Сообщение4", main.getMessage4());
    }


}