package lesson7;

import lesson7.annotations.Autowired;
import lesson7.annotations.StartUp;
import lesson7.core.Context;
import lesson7.services.Animal;
import lesson7.services.Flyable;

@StartUp(scanPackage = "lesson7.services.impl")
public class MainExample {

    @Autowired
    private Flyable flyable;

    @Autowired
    private Animal animal;
    

    public static void main(String[] args) {
        MainExample instanceObject = Context.getInstanceObject(MainExample.class);
        instanceObject.flyable.fly();
        instanceObject.animal.saySmth();
    }



}
