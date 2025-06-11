import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.time.Duration;

@DisplayName(value = "Создание через БД. Авторизация")
public class LessonFifteenTest {
    private Integer numberGenerated = 100 + (int) (Math.random() * 10000);
    private final String LOGIN = "test_user_10";
    private final String PASSWORD = "test";

    WebDriver driver;

    @BeforeEach
    public void createUserAndNote() {

        driver = new ChromeDriver();

        // Создание заметки через БД
        executeUpdate("INSERT INTO nfaut.notes (id, user_id, name, color, content, priority, archive_flg) " +
                "VALUES('" + numberGenerated + "', '23', 'Заметка Аята', '#ccff90', 'Содержание заметки БД', '" + numberGenerated + "', 'false')");
    }

    @Test
    @DisplayName(value = "Тест авторизации и проверка заметки")
    public void testLoginAndCheckNote() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Авторизация
        driver.get("http://172.24.120.5:8081/login");
        driver.findElement(By.id("login-input")).sendKeys(LOGIN);
        driver.findElement(By.id("password-input")).sendKeys(PASSWORD);
        driver.findElement(By.id("form_auth_button")).click();

        //Ожидание прогрузки страницы
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("trash-btn")));

        // Находим элемент заголовка заметки
        WebElement titleElement = driver.findElement(By.xpath("//p[contains(@id, 'note-title-')]"));

        // Получаем ID заметки из id-атрибута элемента, а не из текста
        String titleElementId = titleElement.getAttribute("id");
        String noteId = titleElementId.replace("note-title-", "");

        // Проверяем текст заголовка
        String titleText = titleElement.getText();
        Assertions.assertEquals("Заметка Аята", titleText, "Неверный заголовок заметки");

        // Находим и проверяем текст содержимого
        String noteText = driver.findElement(By.id("note-content-" + noteId)).getText();
        Assertions.assertEquals("Заметка Аята\nСодержание заметки БД", noteText, "Неверное описание заметки");

    }

    @AfterEach
    public void cleanDBAndCloseDriver() {
        // Удаление заметки через БД
        executeUpdate("DELETE FROM nfaut.notes WHERE id = '" + numberGenerated + "'");

        // Закрываем окно браузера
        driver.quit();
    }

    public void executeUpdate(String update) {
        try {
            String url = "jdbc:postgresql://172.24.120.5:5432/postgres";
            String login = "root";
            String password = "root";
            Connection connection= DriverManager.getConnection(url, login, password);
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(update);
                statement.close();
            } finally {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
