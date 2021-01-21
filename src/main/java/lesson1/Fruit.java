package lesson1;

import java.util.ArrayList;

// класс фруктов
public class Fruit {
    private String typeFruit;           // тип фрукта (апельсин/яблоко)
    private float weight;               // вес фрукта

    public Fruit(float weight) {
        this.weight = weight;
    }

    public Fruit(String typeFruit, float weight) {
        this.typeFruit = typeFruit;
        this.weight = weight;
    }

    public String getTypeFruit() {
        return typeFruit;
    }

    public void setTypeFruit(String typeFruit) {
        this.typeFruit = typeFruit;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

}
