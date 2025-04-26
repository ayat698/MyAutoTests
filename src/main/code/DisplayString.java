package code;

public class DisplayString {

    public static void main(String[] args) {

        String text = "";

        // Задание 1
        printerString("Я помню чудное мгновение\n");
        printerString("Передо мной явилась ты\n");
        printerString("Как мимолетное видение\n");
        printerString("Как гений чистой красоты!\n");

        // Пустая строка для отделения заданий
        printerString("");

        // Задание 2
        String word1 = "Java";
        String word2 = "лучший";
        String word3 = "язык";
        String word4 = "программирования!";
        printerString(word1 + " - " + word2 + " " + word3 + " " + word4);

        // Пустая строка для отделения заданий
        printerString("");

        // Задание 3
        String textLang = "Обожаю изучать новые языки";
        // С помощью базового метода substring обрезаем до "изучать новые языки"
        printerString(textLang.substring(7));
        // С помощью базового метода substring обрезаем до "изучать"
        printerString(textLang.substring(7, 14));

        // Пустая строка для отделения заданий
        printerString("");

        // Задание 4
        String textIndex = "Домашнее задание не проблема";
        String Symbols = "не";

        //
        int indexSearchWord = textIndex.indexOf(Symbols);
        int indexAfterAnotherWord = textIndex.indexOf(Symbols, textIndex.indexOf("задание"));

        // Вывод индексов
        printerString("Индекс первого " + Symbols + ": " + indexSearchWord);
        printerString("Индекс " + Symbols + " после 'задание': " + indexAfterAnotherWord);
    }

    private static void printerString(String text) {
        System.out.println(text);
    }
}
