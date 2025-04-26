import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;

@DisplayName("Домашка: Создание и редактирование заметок")
public class CiclesBreakTest {

    WebDriver driver;

    @BeforeEach
    public void initDriverAndLogin() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Login.performLogin(driver, "login1", "passTest");

        // Создаем 5 заметок, но не больше. При более 5 не создавать
        for (int i = 0; i < 5; i++) {
            if (countNote() == 5)
                break;

            System.out.println("\nИтерация создания заметки " + i);
            driver.findElement(By.cssSelector("div.Card_containerNew__adAai img.img-fluid")).click();
            driver.findElement(By.id("note-modal-title-new_empty")).sendKeys("Заметка " + i);
            driver.findElement(By.id("note-modal-content-new_empty")).sendKeys("Описание " + i);
            driver.findElement(By.id("note-modal-save-btn-new_empty")).click();
        }
    }

    @Test
    @DisplayName("Редактирование всех заметок с использованием continue")
    public void editNotesTest() {

        // Общее количество заметок
        int noteCount = countNote();

        for (int i = 0; i < noteCount; i++) {
            // Находим элементы заново на каждой итерации. Записываем в список
            List<WebElement> titles = driver.findElements(By.xpath("//p[contains(@id, 'note-title-')]"));
            WebElement titleElement = titles.get(i);

            // Старое название
            String oldTitle = titleElement.getText();
            // ID заметки
            String titleId = titleElement.getAttribute("id");
            // Описание старой заметки
            String noteId = titleId.replace("note-title-", "");

            // Получение старого описания
            String oldDescription = driver.findElement(By.id("note-content-" + noteId)).getText();

            printer("\nИтерация " + (i) + " ДО изменения: Заголовок: " + oldTitle + ", Описание: " + oldDescription);

            // Нажимаем кнопку редактирования
            driver.findElement(By.id("note-edit-btn-" + noteId)).click();

            // Создаем новые заголовок и описание
            String newTitle = "Обновлённая заметка " + (i);
            String newDescription = "Обновлённое описание " + (i);

            // Обновляем заголовок
            WebElement titleInput = driver.findElement(By.id("note-modal-title-" + noteId));
            titleInput.clear();
            titleInput.sendKeys(newTitle);

            // Обновляем описание
            WebElement contentInput = driver.findElement(By.id("note-modal-content-" + noteId));
            contentInput.clear();
            contentInput.sendKeys(newDescription);

            // Сохраняем изменения
            driver.findElement(By.id("note-modal-save-btn-" + noteId)).click();

            // Ждём появления обновлённого заголовка
            WebElement updatedTitleElement = driver.findElement(By.xpath("//p[text()='" + newTitle + "']"));
            String updatedTitle = updatedTitleElement.getText();
            String updatedDescription = driver.findElement(By.id("note-content-" + noteId)).getText();

            printer("\nИтерация " + (i) + " ПОСЛЕ изменения: Заголовок: " + updatedTitle + ", Описание: " + updatedDescription);
        }
    }

    // Метод для вывода текста
    public static void printer(String text) {
        System.out.println(text);
    }

    @AfterEach
    public void deleteAllNotesAndQuit() {
        int i = countNote();
        while (i != 0) {
            driver.findElement(By.xpath("(//img[contains(@id,'note-delete-btn')])")).click();
            driver.findElement(By.xpath("//button[text()='Да']")).click();
            i = countNote();
        }
        driver.quit();
    }

    private int countNote() {
        List<WebElement> notes = driver.findElements(By.xpath("//div[contains(@id, 'note-container')]"));
        return  notes.size();
    }
}