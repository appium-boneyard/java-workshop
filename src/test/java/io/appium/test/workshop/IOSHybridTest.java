package io.appium.test.workshop;

import org.junit.Before;
import org.junit.Test;

public class IOSHybridTest extends SauceTestClass {

    @Before
    public void setUp() throws Exception {
        capabilities.setCapability("deviceName", "iPhone Simulator");
        capabilities.setCapability("platformVersion", "7.1");
        capabilities.setCapability("app", getApp("HelloGappium.app.zip"));
        super.setUpIosDriver();
    }

    @Test
    public void basicIOSHybridTest() throws Exception {
        runHybridTest();
    }
}