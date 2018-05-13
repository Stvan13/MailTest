package org.stvan.temp.mailtest.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Arkadiy Klimanskiy on 12.05.2018.
 */
public abstract class WebPage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public WebPage(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = wait;
    }
}
