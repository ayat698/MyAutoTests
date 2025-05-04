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
    private String userLogin = "autotest" + numberGenerated;

    WebDriver driver;

    @BeforeEach
    public void createUserAndNote() {

        driver = new ChromeDriver();

        // Создание пользователя через БД
        executeUpdate( "INSERT INTO nfaut.users (id, login, password) " +
                "VALUES('" + numberGenerated + "' ,'" + userLogin + "', '$2a$10$cDr4NN.dBNiZRzfH1MTkRex3KwGhiXI4fw2YD9ZIxBpnIKWfoRpFO')");

        // Добавление роли пользователя через БД
        executeUpdate("INSERT INTO nfaut.users_roles (id, user_id, role_id) " +
                "VALUES('" + numberGenerated + "', '" + numberGenerated + "', '2')");

        // Создание заметки через БД
        executeUpdate("INSERT INTO nfaut.notes (id, user_id, name, color, content, priority, archive_flg) " +
                "VALUES('" + numberGenerated + "','" + numberGenerated + "', 'Заметка Аята', '#ccff90', 'Содержание заметки БД', '" + numberGenerated + "', 'false')");
    }

    @Test
    @DisplayName(value = "Тест авторизации и проверка заметки")
    public void testLoginAndCheckNote() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Авторизация
        driver.get("http://172.24.120.5:8081/login");
        driver.findElement(By.id("login-input")).sendKeys(userLogin);
        driver.findElement(By.id("password-input")).sendKeys("12344321");
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
        Assertions.assertEquals("Содержание заметки БД", noteText, "Неверное описание заметки");

    }

    @AfterEach
    public void cleanDBAndCloseDriver() {
        // Удаление заметки через БД
        executeUpdate("DELETE FROM nfaut.notes WHERE id = '" + numberGenerated + "'");

        // Удаление роли добавленного пользователя
        executeUpdate("DELETE FROM nfaut.users_roles WHERE id = '" + numberGenerated + "'");

        // Удаление добавленного пользователя
        executeUpdate("DELETE FROM nfaut.users where login = '" + userLogin + "'");

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
