package org.stvan.temp.mailtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.stvan.temp.mailtest.page.LoginPage;
import org.stvan.temp.mailtest.page.MailBoxPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * Created by Arkadiy Klimanskiy on 12.05.2018.
 */
@SuppressWarnings("FieldCanBeLocal")
public class MailSendTest {
    private static String USERNAME = "";//TODO Enter username
    private static String PASSWORD = "";//TODO Enter password
    private WebDriver driver;
    private WebDriverWait wait;
    private MailBoxPage mailBoxPage;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void LoginTest() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.loadPage();
        mailBoxPage = loginPage.login(USERNAME, PASSWORD);
        assertNotNull(mailBoxPage);
    }

    @Test(priority = 2)
    public void SendMailTest() {
        assertNotNull(mailBoxPage);
        assert (mailBoxPage.sendMail());
    }

    @AfterClass
    public void finish() {
        driver.quit();
    }
}
