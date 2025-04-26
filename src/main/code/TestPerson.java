package code;

class TestPerson {
    public static void main(String[] args) {
        Person person = new Person("Аят", 33, 1.75f, false);

        // Публичное поле name доступно напрямую
        // Можно использовать, потому что поле public
        System.out.println("Имя: " + person.name);

        // System.out.println("Возраст: " + person.age); // Ошибка! Поле private
        // Приватное поле age недоступно напрямую, только через геттер
        System.out.println("Возраст: " + person.getAge());

        // Защищённое поле height доступно, так как классы в одном пакете
        System.out.println("Рост: " + person.height);

        // Поле с доступом по умолчанию isStudent доступно, так как классы в одном пакете
        System.out.println("Студент: " + person.isStudent);

        // Изменение значения возраста через сеттер
        person.setAge(34);
        System.out.println("Новый возраст: " + person.getAge());
    }
}
