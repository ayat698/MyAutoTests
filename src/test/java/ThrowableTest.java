import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;

public class ThrowableTest {

    @Test
    public void testLoginWithExplicitWait() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.get("http://172.24.120.5:8081/login");

            // Явное ожидание заниженное
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));

            try {
                WebElement loginInput = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("login-input1"))
                );
                WebElement passwordInput = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("password-input1"))
                );
                WebElement loginButton = wait.until(
                        ExpectedConditions.elementToBeClickable(By.id("form_auth_button"))
                );

                loginInput.sendKeys("login1");
                passwordInput.sendKeys("passTest");
                loginButton.click();

            } catch (TimeoutException e) {
                throw new ElementNotFoundException("Элемент логина или пароля не найден за указанное время!", e);
            }

        } finally {
            driver.quit();
        }
    }

    public class ElementNotFoundException extends RuntimeException {
        public ElementNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}