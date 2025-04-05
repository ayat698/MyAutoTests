import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@DisplayName(value = "Авторизация и удаление с явным и неявным ожиданием")
public class SecondSuiteTest {
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
    @DisplayName(value = "Тест авторизации с неявным ожиданием")
    public void authorizationImplicitTest() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://172.24.120.5:8081/login");
        driver.findElement(By.xpath("//*[@id=\"login-input\"]")).sendKeys("login1");
        driver.findElement(By.xpath("//*[@id=\"password-input\"]")).sendKeys("passTest");
        driver.findElement(By.id("form_auth_button")).click();
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[1]/a[2]")).click();
    }

    @Test
    @DisplayName(value = "Тест авторизации с явным ожиданием")
    public void authorizationExplicitTest() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("http://172.24.120.5:8081/login");
        driver.findElement(By.xpath("//*[@id=\"login-input\"]")).sendKeys("login1");
        driver.findElement(By.xpath("//*[@id=\"password-input\"]")).sendKeys("passTest");
        driver.findElement(By.id("form_auth_button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div[1]/div[1]/a[2]"))).click();
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