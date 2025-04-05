import org.junit.jupiter.api.*;

@DisplayName(value = "Текстовый класс с анотациями")
public class JunitAnnotations {

    @BeforeAll
    public static void meth1() {
        System.out.println("Запускаем @BeforeAll в самом начале всех тестов");
    }

    @BeforeEach
    public void meth2() {
        System.out.println("Запускаем @BeforeEach перед каждым тестом");
    }

    @Test
    @DisplayName(value = "Первый тестовый метод")
    public void test1() {
        System.out.println("Запускаем @Test 1");
    }

    @Test
    @Disabled
    @DisplayName(value = "Второй тестовый метод")
    public void test2() {
        System.out.println("Запускаем @Test 2");
    }

    @AfterEach
    public void meth3() {
        System.out.println("Запускаем @AfterEach после каждого теста");
    }

    @AfterAll
    public static void meth4() {
        System.out.println("Запускаем @AfterAll в конце всех тестов");
    }
}
