package POM;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private By emailField = By.xpath(".//label[text()='Email']/../input");
    private By passwordField = By.xpath(".//label[text()='Пароль']/../input");
    private By signInButton = By.xpath(".//button[text()='Войти']");
    private final By enterHeader = By.xpath(".//h2[text()='Вход']");
    private By registerButton = By.xpath(".//a[text()='Зарегистрироваться']");
    private By resetPassword = By.xpath(".//a[text()='Восстановить пароль']");

    public LoginPage(org.openqa.selenium.WebDriver driver) {
        this.driver = driver;
    }
    @Step("Заполненией email")
    public void fillEmail(WebDriver driver, String email) {
        driver.findElement(emailField).click();
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Заполнение пароля")
    public void fillPassword(WebDriver driver, String password) {
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажатие кнопки Войти")
    public void clickSignInButton(WebDriver driver) {
        driver.findElement(signInButton).click();
    }

    @Step("Нажатие кнопки Зарегистироваться")
    public void clickRegisterButton(WebDriver driver) {
        driver.findElement(registerButton).click();
    }

    @Step("Нажатие кнопки Восстановить пароль")
    public void clickResetPasswordButton(WebDriver driver) {
        WebElement element = driver.findElement(resetPassword);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    @Step("Заполнение полей логина")
    public void makeSignIn(WebDriver driver, String email, String password) {
        fillEmail(driver, email);
        fillPassword(driver, password);
        clickSignInButton(driver);
    }

    @Step("Проверка видимости кнопки Войти")
    public boolean isSignInButtonVisible(WebDriver driver) {
        return driver.findElement(signInButton).isDisplayed();
    }
    @Step("Ожидание открытия страницы Входа")
    public void waitLoadingLoginPage() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(signInButton));
    }
    @Step("Получить заголовок Вход")
    public boolean checkEnterHeader(){
        return driver.findElement(enterHeader).isDisplayed();
    }
}
