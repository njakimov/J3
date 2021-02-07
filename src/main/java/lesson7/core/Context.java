package lesson7.core;

import lesson7.annotations.Autowired;
import lesson7.annotations.Service;
import lesson7.annotations.StartUp;
import lesson7.exception.CannotCreateClassObjectException;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Context {

    public static <T> T getInstanceObject(Class<T> template) {
        if (template.isAnnotationPresent(StartUp.class)) {
            StartUp startUpAnnotation = template.getAnnotation(StartUp.class);
            String packageForScan = startUpAnnotation.scanPackage();
            Set<Class<?>> services = scanPackageAndFindClassesByAnnotation(packageForScan, Service.class);
            Map<String, Object> instancesMap = getInstanceOfService(services);

            return configAndGetObjectOfClass(template, instancesMap);

        } else {
            throw new IllegalArgumentException("It is not start up class");
        }
    }

    private static <T> T configAndGetObjectOfClass(Class<T> template, Map<String, Object> instancesMap) {

        try {
            T instance = getClassInstanceByEmptyConstructor(template);
            Field[] fields = template.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    Object dependency = instancesMap.get(field.getType().getName());

                    field.setAccessible(true);
                    field.set(instance, dependency);
                }
            }

            return instance;

        } catch (Exception e) {
            throw new CannotCreateClassObjectException("Cannot create object of " + template.getName() + " class");
        }

    }

    private static Map<String, Object> getInstanceOfService(Set<Class<?>> services) {
        Map<String, Object> servicesMap = new HashMap<>();

        for (Class<?> serviceClass : services) {
            Class<?>[] interfaces = serviceClass.getInterfaces();
            Object serviceInstance = getClassInstanceByEmptyConstructor(serviceClass);

            for (Class<?> anInterface : interfaces) {
                servicesMap.put(anInterface.getName(), serviceInstance);
            }
        }

        return servicesMap;
    }

    private static <T> T getClassInstanceByEmptyConstructor(Class<T> aClass) {
        try {
            return aClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new CannotCreateClassObjectException("Cannot create object of " + aClass.getName() + " class");
        }
    }

    private static Set<Class<?>> scanPackageAndFindClassesByAnnotation(String scanPackage,
                                                                       Class<? extends Annotation> annotationClass) {

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(scanPackage)));

        return reflections.getTypesAnnotatedWith(annotationClass);
    }

}
