package code.Pet;

public class Pet {
    String animal;  // живтное
    String name;    // имя
    int age;        // возраст
    double weight;  // вес

    // Конструктор 1. Животное и кличка
    public Pet() {
        animal = "Dog";
        name = "Undefined";
    }

    // Конструктор 2. Животное, кличка и вес

    public  Pet(String animal, double weight) {
        this.animal = animal;
        name = "Undefined";
        this.weight = weight;
    }
    // Конструктор 3. Вся информация
    public Pet(String animal, String name, int age, double weight) {
        this.animal = animal;
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public void displayInfo() {
        System.out.printf("Animal: %s \tName: %s \tAge: %d\tWeight: %f\n", animal, name, age, weight);
    }
}
