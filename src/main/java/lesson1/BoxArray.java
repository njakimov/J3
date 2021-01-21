package lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class BoxArray<T extends Fruit> {
    private ArrayList<T> contentBox;            // содержимое коробки
    private Float weight = 0f;                  // вес коробки


    public BoxArray() {
        this.contentBox = new ArrayList<>();
    }

    /**
     * Добавить фрукт в коробку
     *
     * @param content
     */
    public void addContentInBox(T content) {
        if (isEqualsTypeFruit(content.getTypeFruit())) {
            contentBox.add(content);
            weight += content.getWeight();
        } else {
            System.out.println("Фрукт не может быть добавлен в данную коробку");
        }

    }

    /**
     * удалить фрукт из коробки
     *
     * @param content
     */
    public void removeContentInBox(T content) {
        contentBox.remove(content);
        weight -= content.getWeight();
    }

    /**
     * очистить коробку
     */
    public void clearBox() {
        this.contentBox = new ArrayList<>();
        weight = 0f;
    }

    public ArrayList<T> getContentBox() {
        return contentBox;
    }

    public void setContentBox(ArrayList<T> contentBox) {
        this.contentBox = contentBox;
    }

    /**
     * получить вес коробки
     *
     * @return
     */
    public Float getWeight() {
        Float weight = 0f;
        for (T content : contentBox) {
            weight += content.getWeight();
        }
        return weight;
    }

    /**
     * Сравнение веса коробок
     *
     * @param boxArray
     * @return
     */
    public boolean compare(BoxArray boxArray) {
        return Math.abs(getWeight() - boxArray.getWeight()) < 0.001;
    }

    /**
     * Сравнение типов фрутов в коробке
     *
     * @param typeFruit
     * @return
     */
    public boolean isEqualsTypeFruit(String typeFruit) {
        String currentTypeFruit = getTypeFruitBox();
        return currentTypeFruit.length() == 0 || currentTypeFruit.equals(typeFruit);
    }

    /**
     * Тип коробки по лежащим в ней фруктам
     *
     * @return тип фрукта
     */
    public String getTypeFruitBox() {
        String currentTypeFruit = "";
        for (T contentItem : contentBox) {
            currentTypeFruit = contentItem.getTypeFruit();
            break;
        }
        return currentTypeFruit;
    }

    /**
     * Переместить фрукты из коробки в коробку
     *
     * @param boxArray - коробка, которую перемещаем
     */
    public void moveBox(BoxArray boxArray) {
        if (isEqualsTypeFruit(boxArray.getTypeFruitBox())) {
            for (Object contentItem : boxArray.getContentBox()) {
                addContentInBox((T) contentItem);
            }
            boxArray.clearBox();
        } else {
            System.out.println("Перемещать нельзя");
        }
    }
}
