import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@DisplayName(value = "Регистрация и авторизация на тестовом стенде")
public class FirstSuiteTest {
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
    @DisplayName(value = "Тест регистрации")
    public void registrationTest() {

        driver.get("http://172.24.120.5:8081/login");
        driver.findElement(By.id("form_register_button")).click();
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div/form/div[1]/input")).sendKeys("login1");
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div/form/div[2]/input")).sendKeys("passTest");
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div/form/button")).click();
    }

    @Test
    @DisplayName(value = "Тест авторизации")
    public void authorizationTest() {

        driver.get("http://172.24.120.5:8081/login");
        driver.findElement(By.xpath("//*[@id=\"login-input\"]")).sendKeys("login1");
        driver.findElement(By.xpath("//*[@id=\"password-input\"]")).sendKeys("passTest");
        driver.findElement(By.id("form_auth_button")).click();
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