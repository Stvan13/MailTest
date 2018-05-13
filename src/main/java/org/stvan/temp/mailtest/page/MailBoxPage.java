package org.stvan.temp.mailtest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Arkadiy Klimanskiy on 12.05.2018.
 */
public class MailBoxPage extends WebPage {
    private final String successTitle = "Письмо отправлено";

    @FindBy(css = "[data-name='compose']")
    @CacheLookup
    private WebElement newMailBtn;

    @FindBy(css = "[data-name='send']")
    @CacheLookup
    private WebElement sendMailBtn;

    @FindBy(css = "[data-original-name='To']")
    @CacheLookup
    private WebElement to;

    @FindBy(name = "Subject")
    @CacheLookup
    private WebElement subject;

    @FindBy(id = "tinymce")
    @CacheLookup
    private WebElement body;

    public MailBoxPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        wait.until(ExpectedConditions.visibilityOf(newMailBtn));
    }

    public boolean sendMail() {
        newMailBtn.click();
        wait.until(ExpectedConditions.visibilityOf(subject));

        //To
        to.sendKeys("stvan@mail.ru");

        //Subject
        subject.sendKeys("Test");

        //Body
        WebElement frame = driver.findElement(new By.ByTagName("iframe"));
        driver.switchTo().frame(frame);
        body.sendKeys("Text1\n");
        body.sendKeys("Text2");

        //Send
        driver.switchTo().defaultContent();
        sendMailBtn.click();

        try {
            wait.until(ExpectedConditions.titleContains(successTitle));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
