import org.junit.jupiter.api.*; // JUnit 5
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;


public class ScriptTest {

    static WebDriver driver = new ChromeDriver();

    @BeforeAll
    public static void initSuiteTest() {
        System.out.println("Начало тестирования");
    }

    @Test
    public void newPage() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Открываем первую страницу
        driver.get("http://172.24.120.5:8081/login");

        // Открываем вторую вкладку
        js.executeScript("window.open('https://www.digitalleague.ru','_blank');");

        // Переключаемся на неё
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());

        // Разворачиваем окно
        driver.manage().window().maximize();

        // Ждем загрузки. Так не работает
        // new WebDriverWait(driver, Duration.ofSeconds(15))
        //       .until(driver -> js.executeScript("return document.readyState").equals("complete"));

        // Вот так сработала прокрутка вниз
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Скроллим до конца
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        System.out.println("Тест пройден");
    }

    @AfterAll
    public static void closeSuiteTest() {

        driver.quit();

        System.out.println("Окончание тестирования");
    }
}