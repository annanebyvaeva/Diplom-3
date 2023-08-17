package POM;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage {
    WebDriver driver;
    private By exitButton = By.xpath(".//button[text()='Выход']");

    @Step("Выход из личного кабинета")
    public void clickExitButton(WebDriver driver) {
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(exitButton));
        driver.findElement(exitButton).click();
    }
}
