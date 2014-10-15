package io.appium.test.workshop;

import io.appium.java_client.ios.IOSDriver;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class MobileWebTest extends SauceTestClass {

    @Before
    public void setUp() throws Exception {
        capabilities.setCapability("deviceName", "iPhone Simulator");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "7.1");
        capabilities.setCapability("browserName", "safari");
        capabilities.setCapability("name", testName.getMethodName());
        driver = new IOSDriver(new URL(sauceServer), capabilities);
        sessionId = driver.getSessionId().toString();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void basicWebTest() throws Exception {
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
        WebElement yourCommentsElement = driver.findElement(By.id("your_comments"));
        assertNotNull(yourCommentsElement);
        assertTrue(driver.findElement(By.id("your_comments")).getText().contains("This is an awesome comment"));
    }
}