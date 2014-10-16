package io.appium.test.workshop;

import org.junit.Before;
import org.junit.Test;

public class IOSMobileWebTest extends AppiumTestClass {

    @Before
    public void setUp() throws Exception {
        capabilities.setCapability("deviceName", "iPhone Simulator");
        capabilities.setCapability("platformVersion", "7.1");
        capabilities.setCapability("browserName", "safari");
        super.setUpIosDriver();
    }

    @Test
    public void basicIOSWebTest() throws Exception {
        runWebTest();
    }
}