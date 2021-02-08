package lesson7;

import lesson7.annotations.StartUp;
import lesson7.annotations.Test;
import lesson7.tests.MainTest;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// Создать класс, который может выполнять «тесты».
//  В качестве тестов выступают классы с наборами методов, снабженных аннотациями @Test.
//      Класс, запускающий тесты, должен иметь статический метод start(Class testClass), которому в качестве
//      аргумента передается объект типа Class. Из «класса-теста» вначале должен быть запущен метод с аннотацией
//      @BeforeSuite, если он присутствует. Далее запускаются методы с аннотациями @Test,
//      а по завершении всех тестов – метод с аннотацией @AfterSuite.
//  К каждому тесту необходимо добавить приоритеты (int-числа от 1 до 10),
//      в соответствии с которыми будет выбираться порядок их выполнения.
//      Если приоритет одинаковый, то порядок не имеет значения. Методы с аннотациями @BeforeSuite и @AfterSuite
//      должны присутствовать в единственном экземпляре.
//      Если это не так – необходимо бросить RuntimeException при запуске «тестирования».
//  P.S. Это практическое задание – проект, который пишется «с нуля».
//      Данная задача не связана напрямую с темой тестирования через JUnit

@StartUp(scanPackage = "lesson7.tests")
public class GlobalTestRun {

    public static void main(String[] args) throws Exception {
        start(MainTest.class);
    }

    public static void start(Class c) {
        try {
            Object tmp = c.getConstructor().newInstance();

            Method[] methods = c.getDeclaredMethods();
            List<Method> sortMethods = new ArrayList<>();
            Method afterMethod = null;
            Method beforeMethod = null;

            for (Method method : methods) {
                if (method.isAnnotationPresent(Test.class)) {
                    sortMethods.add(method);
                }
                if (method.isAnnotationPresent(BeforeSuite.class)) {
                    if (beforeMethod == null) beforeMethod = method;
                    else throw new RuntimeException("> одного BeforeSuite");
                }
                if (method.isAnnotationPresent(AfterSuite.class)) {
                    if (afterMethod == null) afterMethod = method;
                    else throw new RuntimeException("> одного AfterSuite");
                }
            }

            sortMethods.sort((m1, m2) -> m2.getAnnotation(Test.class).priority() - m1.getAnnotation(Test.class).priority());

            if (beforeMethod != null) {
                invokeMethod(tmp, beforeMethod);
            }
            for (Method method : sortMethods) {
                invokeMethod(tmp, method);
            }
            if (afterMethod != null) {
                invokeMethod(tmp, afterMethod);
            }

        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void invokeMethod(Object tmp, Method afterMethod) throws IllegalAccessException, InvocationTargetException {
        afterMethod.setAccessible(true);
        afterMethod.invoke(tmp, null);
    }
}
