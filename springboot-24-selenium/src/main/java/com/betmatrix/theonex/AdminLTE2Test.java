package com.betmatrix.theonex;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AdminLTE2Test {

    private static final Logger log = LoggerFactory.getLogger(AdminLTE2Test.class);


    public static String userDir = System.getProperty("user.dir");

    private static String BASE_URL = "http://localhost:8080";


    WebDriver driver;

    AdminLoginPage adminLoginPage;

    @Before
    public void before() throws IOException {

        String chromeDriverPath = userDir + "/src/main/resources/ChromeDriver.2.45/chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        Assert.assertNotNull(driver);

        adminLoginPage = PageFactory.initElements(driver, AdminLoginPage.class);

    }

    @After
    public void after() throws Exception {
        driver.quit();
        log.info("end");
    }

    /**
     *
     */
    @Test
    public void test01() throws InterruptedException {
        String url = BASE_URL;
        try {
            driver.get(url);
            WebDriverWait wait = new WebDriverWait(driver, 20);
            Boolean titleExist = false;
            titleExist = wait.until((driver) -> {
                log.debug("submit WebDriverWait to => " + driver.getTitle());
                return "Spring Boot AdminLTE 2 | Login".equalsIgnoreCase(driver.getTitle().trim());
            });

            log.debug("titleExist => " + titleExist);
            adminLoginPage.enterFieldValue("admin", "admin").submit();

            wait.until((driver) -> {
                log.debug("submit WebDriverWait to => " + driver.getTitle());
                return "SpringBoot AdminLTE2 - Dashboard".equalsIgnoreCase(driver.getTitle().trim());
            });
            System.out.println("login ok");

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("alert('selenium alert');");

            TimeUnit.SECONDS.sleep(1);

            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println(alertText);
            alert.accept();

            String return_value = (String) js.executeScript("return $('.user-footer').html()");
            System.out.println(return_value);


            // 這邊會出exception
            WebElement foo = new WebDriverWait(driver, 1).until(driver -> driver.findElement(By.id("foo")));
            Assert.assertNotNull(foo);

            TimeUnit.SECONDS.sleep(3);

        } finally {
            driver.close();
            driver.quit();
        }

    }


}
