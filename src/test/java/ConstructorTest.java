import POM.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertTrue;
import static Client.BaseApi.BASE_URL;

public class ConstructorTest {
    WebDriver driver;
    MainPage mainPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        driver.get(BASE_URL);
    }

    @Test
    @DisplayName("Проверка перехода по разделу Булки")
    public void checkBreadCanBeSelectedTest() {
        mainPage.clickSauceButton(driver);
        mainPage.clickBreadButton(driver);
        assertTrue(mainPage.isBreadSelected(driver));
    }

    @Test
    @DisplayName("Проверка перехода по разделу Соусы")
    public void checkSauceCanBeSelectedTest() {
        mainPage.clickSauceButton(driver);
        assertTrue(mainPage.isSauceSelected(driver));
    }

    @Test
    @DisplayName("Проверка перехода по разделу Начинки")
    public void checkFillingCanBeSelectedTest() {
        mainPage.clickFillingButton(driver);
        assertTrue(mainPage.isFillingSelected(driver));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
