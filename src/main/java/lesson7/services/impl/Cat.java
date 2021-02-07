package lesson7.services.impl;

import lesson7.services.Animal;

public class Cat implements Animal {

    @Override
    public void saySmth() {
        System.out.println("Meow!!!");
    }

}
