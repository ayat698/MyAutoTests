import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
    public void initDriver() {
        driver = new ChromeDriver();
    }

    @Test
    @DisplayName(value = "Тест заголовка заметки")
    public void noteTitleTest() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://172.24.120.5:8081/login");
        driver.findElement(By.xpath("//*[@id=\"login-input\"]")).sendKeys("login1");
        driver.findElement(By.xpath("//*[@id=\"password-input\"]")).sendKeys("passTest");
        driver.findElement(By.id("form_auth_button")).click();
        String titleText = driver.findElement(By.xpath("//*[@id=\"note-title-37\"]")).getText();
        Assertions.assertEquals("Тестовый заголовок",titleText, "Неверный заголовок заметки");
    }

    @Test
    @DisplayName(value = "Тест текста заметки")
    public void noteTextTest() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://172.24.120.5:8081/login");
        driver.findElement(By.xpath("//*[@id=\"login-input\"]")).sendKeys("login1");
        driver.findElement(By.xpath("//*[@id=\"password-input\"]")).sendKeys("passTest");
        driver.findElement(By.id("form_auth_button")).click();
        String noteText = driver.findElement(By.xpath("//*[@id=\"note-content-37\"]")).getText();
        Assertions.assertEquals("Текст заметки для проверки",noteText, "Неверный текст заметки");
    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }

    @AfterAll
    public static void closeSuiteTest() {
        System.out.println("Окончание тестирования");
    }
}
