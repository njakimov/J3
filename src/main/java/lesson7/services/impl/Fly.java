package lesson7.services.impl;

import lesson7.services.Flyable;

public class Fly implements Flyable {

    @Override
    public void fly() {
        System.out.println("fly fly");
    }

}
