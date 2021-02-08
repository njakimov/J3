package lesson6;

import lesson7.MainExample;
import lesson7.services.Animal;
import lesson7.services.Flyable;
import lesson7.services.impl.Cat;
import lesson7.services.impl.Fly;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MainTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void test() {
        Animal cat = Mockito.mock(Cat.class);
        Flyable flyable = Mockito.mock(Fly.class);

        MainExample main = new MainExample(flyable, cat);

        Mockito.when(cat.saySmth())
                .thenReturn("Meow test");

        main.sayAnimal();
    }
}