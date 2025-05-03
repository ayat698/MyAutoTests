import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login {

    public static void performLogin(WebDriver driver, String username, String password) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://172.24.120.5:8081/login");
        driver.findElement(By.xpath("//*[@id=\"login-input\"]")).sendKeys(username);
        driver.findElement(By.xpath("//*[@id=\"password-input\"]")).sendKeys(password);
        driver.findElement(By.id("form_auth_button")).click();
    }
}
