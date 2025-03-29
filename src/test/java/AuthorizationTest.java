import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthorizationTest {

    @Test
    public void testAuthorization() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://172.24.120.5:8081/login");
        String loginClassText = driver.findElement(By.className("form_auth_block_head_text")).getText();
        driver.findElement(By.id("login-input")).sendKeys(loginClassText);
        String loginValueText = driver.findElement(By.id("login-input")).getAttribute("value");
        driver.findElement(By.id("password-input")).sendKeys(loginValueText);
        driver.findElement(By.id("password-input")).clear();
        String buttonBackgroundColor = driver.findElement(By.id("form_auth_button")).getCssValue("background-color");
        driver.findElement(By.id("password-input")).sendKeys(buttonBackgroundColor);
        driver.quit();
    }
}