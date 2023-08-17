import Client.Steps;
import POM.LoginPage;
import POM.MainPage;
import POM.RegisterPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import static Client.BaseApi.BASE_URL;
import static org.junit.Assert.assertTrue;

public class RegistrationTest {
    WebDriver driver;
    User user;
    Steps steps;
    MainPage mainPage;
    RegisterPage registerPage;
    LoginPage loginPage;


    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        registerPage = new RegisterPage();
        loginPage = new LoginPage(driver);
        steps = new Steps();
        user = User.getRandomUser();
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    public void successfulRegisterTest() {
        mainPage.clickLoginButton(driver);
        loginPage.clickRegisterButton(driver);
        registerPage.makeRegistration(driver, user.getName(), user.getEmail(), user.getPassword());
        loginPage.waitLoadingLoginPage();
        assertTrue(loginPage.isSignInButtonVisible(driver));
        Assert.assertTrue(loginPage.checkEnterHeader());
    }

    @Test
    @DisplayName("Проверка появления дополнительного блока с ошибкой при введении некорректного пароля")
    public void checkShortPasswordMakesErrorMessageTest() {
        mainPage.clickLoginButton(driver);
        loginPage.clickRegisterButton(driver);
        user.setPassword(RandomStringUtils.randomNumeric(3));
        registerPage.makeRegistration(driver, user.getName(), user.getEmail(), user.getPassword());
        assertTrue(registerPage.isIncorrectPasswordVisible(driver));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
