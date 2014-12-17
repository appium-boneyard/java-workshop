package io.appium.test.workshop;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AndroidGestureTest extends AppiumTestClass {

    @Before
    public void setUp() throws Exception {
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.0");
        capabilities.setCapability(MobileCapabilityType.APP, getApp("ApiDemos.apk"));
        capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, ".view.DragAndDropDemo");
        super.setUpAndroidDriver();
    }

    @Test
    public void basicAndroidDragNDropTest() throws Exception {
        WebElement dragDot1 = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        WebElement dragDot3 = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_3"));

        WebElement dragText = driver.findElement(By.id("io.appium.android.apis:id/drag_text"));
        assertEquals("Drag text not empty", "", dragText.getText());

        TouchAction dragNDrop = new TouchAction(driver).longPress(dragDot1).moveTo(dragDot3).release();
        dragNDrop.perform();

        assertNotEquals("Drag text empty", "", dragText.getText());

        ((AndroidDriver) driver).openNotifications();
        Thread.sleep(2000);
    }
}