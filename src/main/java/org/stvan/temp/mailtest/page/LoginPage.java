package org.stvan.temp.mailtest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Arkadiy Klimanskiy on 12.05.2018.
 */
public class LoginPage extends WebPage {
    private final String successTitle = "Входящие";
    private final String errorTitle = "Вход";
    private String url = "https://mail.ru";

    @FindBy(id = "mailbox:login")
    @CacheLookup
    private WebElement usernameField;

    @FindBy(id = "mailbox:password")
    @CacheLookup
    private WebElement passwordField;

    @FindBy(id = "mailbox:submit")
    @CacheLookup
    private WebElement submitBtn;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void loadPage() {
        driver.navigate().to(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ById("auth")));
    }

    public MailBoxPage login(String username, String password) {
        System.out.println(driver.getTitle());
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        submitBtn.click();
        wait.until(ExpectedConditions.or(ExpectedConditions.titleContains(successTitle),
                ExpectedConditions.titleContains(errorTitle)
        ));
        if (driver.getTitle().contains(successTitle)) return new MailBoxPage(driver, wait);
        return null;
    }
}
