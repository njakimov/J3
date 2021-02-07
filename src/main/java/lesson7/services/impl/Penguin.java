package lesson7.services.impl;

import lesson7.annotations.Service;
import lesson7.services.Animal;
import lesson7.services.Flyable;

@Service
public class Penguin implements Flyable, Animal {

    @Override
    public void saySmth() {
        System.out.println("I am a Penguin");
    }

    @Override
    public void fly() {
        System.out.println("I can't fly (((");
    }
}
