package io.appium.test.workshop;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;


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

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
