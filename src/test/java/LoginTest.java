import Client.Steps;
import POM.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static Client.BaseApi.BASE_URL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginTest {
    WebDriver driver;
    User user;
    Steps steps;
    MainPage mainPage;
    RegisterPage registerPage;
    LoginPage loginPage;
    HeaderPage headerPage;
    ResetPasswordPage resetPasswordPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        headerPage = new HeaderPage(driver);
        registerPage = new RegisterPage();
        loginPage = new LoginPage(driver);
        resetPasswordPage = new ResetPasswordPage();
        steps = new Steps();
        user = User.getRandomUser();
        steps.registrationUser(user);
    }

    @Test
    @DisplayName("Проверка входа по кнопке «Войти в аккаунт»")
    public void loginFromMainPageTest() {
        mainPage.clickLoginButton(driver);
        loginPage.makeSignIn(driver, user.getEmail(), user.getPassword());
        assertTrue(mainPage.isHomePageVisible(driver));
    }

    @Test
    @DisplayName("Проверка входа по кнопке «Личный кабинет» на главной")
    public void signInWithPersonalAccountButtonTest() {
        headerPage.clickPersonalAccount(driver);
        loginPage.makeSignIn(driver, user.getEmail(), user.getPassword());
        assertTrue(mainPage.isHomePageVisible(driver));
    }

    @Test
    @DisplayName("Проверка входа через кнопку в форме регистрации")
    public void signInFromRegistrationPageTest() {
        mainPage.clickLoginButton(driver);
        loginPage.clickRegisterButton(driver);
        assertEquals(driver.getCurrentUrl(), "https://stellarburgers.nomoreparties.site/register");
        registerPage.clickSignInButton(driver);
        assertEquals(driver.getCurrentUrl(), "https://stellarburgers.nomoreparties.site/login");
        loginPage.makeSignIn(driver, user.getEmail(), user.getPassword());
        assertTrue(mainPage.isHomePageVisible(driver));
    }

    @Test
    @DisplayName("Проверка входа через кнопку на форме восстановления пароля")
    public void signInFromResetPasswordPageTest() {
        mainPage.clickLoginButton(driver);
        loginPage.clickResetPasswordButton(driver);
        assertEquals(driver.getCurrentUrl(), "https://stellarburgers.nomoreparties.site/forgot-password");
        resetPasswordPage.clickSignInButton(driver);
        assertEquals(driver.getCurrentUrl(), "https://stellarburgers.nomoreparties.site/login");
        loginPage.makeSignIn(driver, user.getEmail(), user.getPassword());
        assertTrue(mainPage.isHomePageVisible(driver));
    }

    @After
    public void tearDown() {
        driver.quit();
        ValidatableResponse loginResponse = steps.loginUser(user);
        String accessToken = loginResponse.extract().path("accessToken");
        if (accessToken != null) {
            steps.deleteUser(accessToken);
        }
    }
}

