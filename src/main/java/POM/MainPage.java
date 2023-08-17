package POM;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;
    private By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private By breadButton = By.xpath(".//span[text()='Булки']/..");
    private By sauceButton = By.xpath(".//span[text()='Соусы']/..");
    private By fillingButton = By.xpath(".//span[text()='Начинки']/..");
    private final By mainPage = By.xpath(".//main[@class='App_componentContainer__2JC2W']");
    public MainPage(org.openqa.selenium.WebDriver driver) {
        this.driver = driver;
    }
    @Step("Переход по кнопке Войти в аккаунт")
    public void clickLoginButton(WebDriver driver) {
        driver.findElement(loginButton).click();
    }

    @Step("Нажатие кнопки Булки")
    public void clickBreadButton(WebDriver driver) {
        driver.findElement(breadButton).click();
    }

    @Step("Нажатие кнопки Соусы")
    public void clickSauceButton(WebDriver driver) {
        driver.findElement(sauceButton).click();
    }

    @Step("Нажатие кнопки Начинки")
    public void clickFillingButton(WebDriver driver) {
        driver.findElement(fillingButton).click();
    }

    @Step("Проверка того, что выбрана категория Булки")
    public boolean isBreadSelected(WebDriver driver) {
        return new WebDriverWait(driver, 3)
                .until(ExpectedConditions.attributeContains(breadButton, "class", "current"));
    }

    @Step("Проверка того, что выбрана категория Соусы")
    public boolean isSauceSelected(WebDriver driver) {
        return new WebDriverWait(driver, 3)
                .until(ExpectedConditions.attributeContains(sauceButton, "class", "current"));
    }

    @Step("Проверка того, что выбрана категория Начинки")
    public boolean isFillingSelected(WebDriver driver) {
        return new WebDriverWait(driver, 3)
                .until(ExpectedConditions.attributeContains(fillingButton, "class", "current"));
    }
    @Step("Проверка отображения страницы")
    public boolean isHomePageVisible(WebDriver driver) {
        return driver.findElement(mainPage).isDisplayed();
    }
}
