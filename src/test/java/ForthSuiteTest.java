import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;

@DisplayName("Домашка: Создание и редактирование заметок")
public class ForthSuiteTest {

    WebDriver driver;

    @BeforeEach
    public void initDriverAndLogin() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Login.performLogin(driver, "login1", "passTest");

        // Создаем 3 заметки
        for (int i = 1; i <= 3; i++) {
            driver.findElement(By.cssSelector("div.Card_containerNew__adAai img.img-fluid")).click();
            driver.findElement(By.id("note-modal-title-new_empty")).sendKeys("Заметка " + i);
            driver.findElement(By.id("note-modal-content-new_empty")).sendKeys("Описание " + i);
            driver.findElement(By.id("note-modal-save-btn-new_empty")).click();
        }
    }

    @Test
    @DisplayName("Редактирование 3 заметок по названию")
    public void editNotesTest() {
        for (int i = 1; i <= 3; i++) {
            String originalTitle = "Заметка " + i;
            String updatedTitle = "Изменённая заметка " + i;
            String updatedDescription = "Новое описание " + i;

            // Поиск элемента заголовка по точному тексту из создания
            WebElement titleElement = driver.findElement(By.xpath("//p[text()='" + originalTitle + "']"));

            // Получение ID из заголовка
            String titleId = titleElement.getAttribute("id");
            String noteId = titleId.replace("note-title-", "");

            // Получение текущего описания по noteId
            String oldDescription = driver.findElement(By.id("note-content-" + noteId)).getText();

            printer("До редактирования: Заголовок: " + originalTitle + " / Описание: " + oldDescription);

            // Клик по кнопке редактирования по ID
            driver.findElement(By.id("note-edit-btn-" + noteId)).click();

            // Редактируем заголовок
            driver.findElement(By.id("note-modal-title-" + noteId)).clear();
            driver.findElement(By.id("note-modal-title-" + noteId)).sendKeys(updatedTitle);

            // Редактируем описание
            driver.findElement(By.id("note-modal-content-" + noteId)).clear();
            driver.findElement(By.id("note-modal-content-" + noteId)).sendKeys(updatedDescription);

            // Клик по кнопке сохранить
            driver.findElement(By.id("note-modal-save-btn-" + noteId)).click();

            // Получаем обновлённые элементы
            String updatedTitleElement = driver.findElement(By.xpath("//p[text()='" + updatedTitle + "']")).getText();
            String updatedContentElement = driver.findElement(By.id("note-content-" + noteId)).getText();

            printer("После редактирования: Заголовок: " + updatedTitleElement + " / Описание: " + updatedContentElement);
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