import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTest {

    @Test
    public void testLogin() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://172.24.120.5:8081/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-input"))).sendKeys("ASYZDYKBEKOV");
        driver.quit();
    }
}
