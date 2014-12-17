package io.appium.test.workshop;

import io.appium.java_client.MobileBy;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class AndroidUnicodeTest extends AppiumTestClass {

    @Before
    public void setUp() throws Exception {
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.0");
        capabilities.setCapability(MobileCapabilityType.APP, getApp("ApiDemos.apk"));
        capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, ".view.Controls1");
        capabilities.setCapability("unicodeKeyboard", true);
        super.setUpAndroidDriver();
    }

    @Test
    public void basicAndroidUnicodeTest() throws Exception {
        WebElement field = driver.findElement(MobileBy.className("android.widget.EditText"));
        String unicodeStr = "परीक्षा-परीक";
        field.sendKeys(unicodeStr);
        assertEquals(field.getText(), unicodeStr);
    }
}