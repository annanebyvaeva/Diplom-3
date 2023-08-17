package POM;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderPage {
    WebDriver driver;
    private By constructor = By.xpath(".//p[text()='Конструктор']");
    private By stellarBurgers = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    private By personalAccount = By.xpath(".//p[text()='Личный Кабинет']");
    private By messageInPagePersonalArea=By.xpath(".//p[contains(text(),'В этом разделе вы можете изменить свои персональные данные')]");

    public HeaderPage(org.openqa.selenium.WebDriver driver){
        this.driver = driver;
    }

    @Step("Переход по логотипу конструктора")
    public void clickConstructor(WebDriver driver) {
        driver.findElement(constructor).click();
    }

    @Step("Переход по логотипу стеллар бургер")
    public void clickStellarBurgers(WebDriver driver) {
        driver.findElement(stellarBurgers).click();
    }

    @Step("Переход по логотипу личного кабинета")
    public void clickPersonalAccount(WebDriver driver) {
        driver.findElement(personalAccount).click();
    }
    @Step("Отображение сообщения в Личном кабинете для проверки перехода в личный кабинет")
    public boolean isDisplayedMessage(){
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(messageInPagePersonalArea));
        return driver.findElement(messageInPagePersonalArea).isDisplayed();
    }
}
