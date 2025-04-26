package code;

public class Person {
    // Публичный доступ — можно обращаться к полю из любого места
    public String name;

    // Приватный доступ — можно обращаться только внутри класса Person
    private int age;

    // Защищённый доступ — можно обращаться из классов в этом пакете и из наследников
    protected float height;

    // Логический тип
    boolean isStudent;

    // Конструктор класса
    public Person(String name, int age, float height, boolean isStudent) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.isStudent = isStudent;
    }

    // Геттер для age
    public int getAge() {
        return age;
    }

    // Сеттер для age
    public void setAge(int age) {
        this.age = age;
    }
}

