package code;

public class LessonSeven {
    public static void main(String[] args) {

        final double Pi = 3.14159265359;

        int numb = 17;

        // ДЗ 1.
        numberOfPi(Pi);

        // ДЗ 2.
        operationOfMath(numb);
    }

    private static void numberOfPi(double Pi) {
        // ДЗ 1. Первая часть

        printNumber("Значение числа π равно " + Pi);

        // ДЗ 1. Вторая часть
        String stringPi3 = String.format("%.3f", Pi);
        String stringPi5 = String.format("%.5f", Pi);

        printNumber("π с точностью до 3 знаков: " + stringPi3);
        printNumber("π с точностью до 5 знаков: " + stringPi5 + "\n");
    }

    private static void operationOfMath(int numb) {
        // ДЗ 2. Первая часть

        printNumber("Искомое число равно " + numb);

        // ДЗ 2. Вторая часть
        int square = getSquare(numb);
        printNumber("Квадрат числа " + numb + ": " + square);

        int cube = getCube(numb);
        printNumber("Куб числа " + numb + ": " + cube);

        double squareRoot = getSquareRoot(numb);
        printNumber("Квадратный корень числа " + numb + ": " + squareRoot);
    }
    private static void printNumber(String text) {
        System.out.println(text);
    }

    public static int getSquare(int numb) {
        return numb * numb;
    }

    public static int getCube(int numb) {
        return numb * numb * numb;
    }

    public static double getSquareRoot(int numb) {
        return Math.sqrt(numb);
    }
}
