import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

@DisplayName(value = "Проверка заголовка и текста заметки")
public class ThirdSuiteTest {

    WebDriver driver;

    @BeforeAll
    public static void initSuiteTest() {
        System.out.println("Начало тестирования");
    }
    @BeforeEach
    public void initDriverAndLogin() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Login.performLogin(driver, "login1", "passTest");
        // Клик по изображению с кнопкой "+"
        driver.findElement(By.cssSelector("div.Card_containerNew__adAai img.img-fluid")).click();
        // Ввод названия заметки
        driver.findElement(By.xpath("//*[@id=\"note-modal-title-new_empty\"]")).sendKeys("Тестовый заголовок");
        // Ввод содержания заметки
        driver.findElement(By.xpath("//*[@id=\"note-modal-content-new_empty\"]")).sendKeys("Текст заметки для проверки");
        // Клик по кнопке "Ок"
        driver.findElement(By.id("note-modal-save-btn-new_empty")).click();
    }

    @Test
    @DisplayName("Тест заголовка заметки")
    public void noteTitleTest() {
        String titleText = driver.findElement(By.xpath("(//p[starts-with(@id,'note-title')])[last()]")).getText();
        Assertions.assertEquals("Тестовый заголовок", titleText, "Неверный заголовок заметки");
    }

    @Test
    @DisplayName("Тест текста заметки")
    public void noteTextTest() {
        String noteText = driver.findElement(By.xpath("(//div[contains(@id,'note-content')])[last()]")).getText();
        Assertions.assertEquals("Текст заметки для проверки", noteText, "Неверный текст заметки");
    }

    @AfterEach
    public void closeDriver() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Удаление последней заметки
        driver.findElement(By.xpath("//img[contains(@id,'note-delete-btn')][last()]")).click();

        // Подтверждаем удаление
        driver.findElement(By.xpath("//button[text()='Да']")).click();

        // Закрываем браузер
        driver.quit();
    }

    @AfterAll
    public static void closeSuiteTest() {
        System.out.println("Окончание тестирования");
    }
}