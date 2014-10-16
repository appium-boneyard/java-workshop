package io.appium.test.workshop;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public abstract class AppiumTestClass {
    protected AppiumDriver driver;
    protected DesiredCapabilities capabilities = new DesiredCapabilities();
    protected String appiumServer = "http://localhost:4723/wd/hub";


    protected void setUpIosDriver() throws Exception {
        preDriverInit();
        capabilities.setCapability("platformName", "iOS");
        driver = new IOSDriver(new URL(appiumServer), capabilities);
        postDriverInit();
    }

    protected void setUpAndroidDriver() throws Exception {
        preDriverInit();
        capabilities.setCapability("platformName", "Android");
        driver = new AndroidDriver(new URL(appiumServer), capabilities);
        postDriverInit();
    }

    protected void preDriverInit() {
    }

    protected void postDriverInit() throws Exception {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    protected void runWebTest() throws Exception {
        driver.get("http://saucelabs.com/test/guinea-pig");
        WebElement idElement = driver.findElement(By.id("i_am_an_id"));
        assertNotNull(idElement);
        assertEquals("I am a div", idElement.getText());
        WebElement commentElement = driver.findElement(By.id("comments"));
        assertNotNull(commentElement);
        commentElement.sendKeys("This is an awesome comment");
        WebElement submitElement = driver.findElement(By.id("submit"));
        assertNotNull(submitElement);
        submitElement.click();
        Thread.sleep(2000);
        WebElement yourCommentsElement = driver.findElement(By.id("your_comments"));
        assertNotNull(yourCommentsElement);
        assertTrue(driver.findElement(By.id("your_comments")).getText().contains("This is an awesome comment"));
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
