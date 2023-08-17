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
import static org.junit.Assert.assertTrue;

public class TransitionTest {
    WebDriver driver;
    User user;
    Steps steps;
    MainPage mainPage;
    LoginPage loginPage;
    HeaderPage headerPage;
    AccountPage accountPage;

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
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage();
        steps = new Steps();
        user = User.getRandomUser();
        steps.registrationUser(user);
    }
    @Test
    @DisplayName("Проверка перехода по логотипу личного кабинета")
    public void getIntoPersonalAccountWithHeaderButtonTest() {
        headerPage.clickPersonalAccount(driver);
        loginPage.makeSignIn(driver, user.getEmail(), user.getPassword());
        headerPage.clickPersonalAccount(driver);
        assertTrue(headerPage.isDisplayedMessage());
    }
    @Test
    @DisplayName("Проверка перехода по логотипу стеллар бургер")
    public void getIntoMainPageFromPersonalAccountWithLogoBurgerTest() {
        headerPage.clickPersonalAccount(driver);
        loginPage.makeSignIn(driver, user.getEmail(), user.getPassword());
        headerPage.clickPersonalAccount(driver);
        headerPage.clickStellarBurgers(driver);
        assertTrue(mainPage.isHomePageVisible(driver));
    }

    @Test
    @DisplayName("Проверка перехода по логотипу контструктора")
    public void getIntoMainPageFromPersonalAccountWithLogoConstructorTest() {
        headerPage.clickPersonalAccount(driver);
        loginPage.makeSignIn(driver, user.getEmail(), user.getPassword());
        headerPage.clickPersonalAccount(driver);
        headerPage.clickConstructor(driver);
        assertTrue(mainPage.isHomePageVisible(driver));
    }

    @Test
    @DisplayName("Проверка выхода из личного кабинета")
    public void exitFromPersonalAccountTest() {
        headerPage.clickPersonalAccount(driver);
        loginPage.makeSignIn(driver, user.getEmail(), user.getPassword());
        headerPage.clickPersonalAccount(driver);
        accountPage.clickExitButton(driver);
        loginPage.waitLoadingLoginPage();
        assertTrue(loginPage.isSignInButtonVisible(driver));
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
