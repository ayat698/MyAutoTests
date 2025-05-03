package code;

public class LessonEight {

    public static void main(String[] args) {
        // ДЗ 1

        int number = 7778;
        double decimal = 876.351;
        String name = "Игорь";
        char symbol = 'n';
        boolean isTrue = true;

        printer("Значение целочисленной переменной - " + number);
        printer("Значение вещественной переменной - " + decimal);
        printer("Значение строковой переменной - " + name);
        printer("Значение символьной переменной - " + symbol);
        printer("Значение логической переменной - " + isTrue + "\n");

        // ДЗ 2
        // Преобразование типов
        double fromIntToDouble = (double) number;
        int fromDoubleToInt = (int) decimal;

        printer("Преобразование int " + number + " в double: " + fromIntToDouble);
        printer("Преобразование double " + decimal + " в int: " + fromDoubleToInt);
    }

    public static void printer(String text) {
        System.out.println(text);
    }

}

