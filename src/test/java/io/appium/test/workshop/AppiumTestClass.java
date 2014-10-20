package io.appium.test.workshop;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThan;


public abstract class AppiumTestClass {
    protected AppiumDriver driver;
    protected DesiredCapabilities capabilities = new DesiredCapabilities();
    protected String appiumServer = "http://localhost:4723/wd/hub";

    protected String getApp(String appFile) {
        File projectRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(projectRoot, "apps");
        File app = new File(appDir, appFile);
        return app.toString();
    }

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

    protected void switchToWebview() throws Exception {
        Set<String> contexts = driver.getContextHandles();
        // => ['NATIVE_APP', 'WEBVIEW_1', ...]
        // make sure we have something other than the native context
        assertThat(contexts.size(), greaterThan(1));
        for (String context : contexts) {
            if (!context.equals("NATIVE_APP")) {
                driver.context(context);
                break;
            }
        }
    }

    protected void runHybridTest() throws Exception {
        switchToWebview();
        WebElement search = driver.findElement(By.cssSelector(".search-key"));
        search.sendKeys("j");
        List<WebElement> employees = driver.findElements(By.cssSelector(".topcoat-list a"));
        assertEquals(employees.size(), 5);
        employees.get(3).click();
        List<WebElement> options = driver.findElements(By.cssSelector(".actions a"));
        assertEquals(options.size(), 6);
        options.get(3).click();
        Thread.sleep(2000);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
