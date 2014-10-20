package io.appium.test.workshop;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class IOSNativeTest extends AppiumTestClass {

    @Before
    public void setUp() throws Exception {
        capabilities.setCapability("deviceName", "iPhone Simulator");
        capabilities.setCapability("platformVersion", "7.1");
        capabilities.setCapability("app", getApp("TestApp7.1.app.zip"));
        super.setUpIosDriver();
    }

    @Test
    public void basicIOSNativeTest() throws Exception {
        List<WebElement> fields = driver.findElements(By.className("UIATextField"));
        Integer sum = 0;
        for (WebElement field : fields) {
            Integer num = randInt(1, 100);
            sum += num;
            field.sendKeys(num.toString());
        }
        driver.findElement(By.className("UIAButton")).click();
        String result = driver.findElement(By.className("UIAStaticText")).getText();
        assertEquals(result, sum.toString());
    }

    public static Integer randInt(int min, int max) {
        Random rand = new Random();
        Integer randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}