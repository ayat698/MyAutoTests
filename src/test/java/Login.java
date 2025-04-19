import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {

    public static void performLogin(WebDriver driver, String username, String password) {
        driver.get("http://172.24.120.5:8081/login");
        driver.findElement(By.xpath("//*[@id=\"login-input\"]")).sendKeys(username);
        driver.findElement(By.xpath("//*[@id=\"password-input\"]")).sendKeys(password);
        driver.findElement(By.id("form_auth_button")).click();
    }
}
