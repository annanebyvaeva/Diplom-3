package POM;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResetPasswordPage {
    private By signInButton = By.xpath(".//a[text() = 'Войти']");

    @Step("Переход по кнопке Войти")
    public void clickSignInButton(WebDriver driver) {
        driver.findElement(signInButton).click();
    }
}
