package io.appium.test.workshop;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.SauceOnDemandTestWatcher;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

public abstract class SauceTestClass  implements SauceOnDemandSessionIdProvider {
    protected AppiumDriver driver;
    protected DesiredCapabilities capabilities = new DesiredCapabilities();
    protected String sessionId;
    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication();
    protected String username = authentication.getUsername();
    protected String accessKey = authentication.getAccessKey();
    protected String sauceServer = MessageFormat.format("http://{0}:{1}@ondemand.saucelabs.com:80/wd/hub",
            username, accessKey);

    @Rule
    public TestName testName = new TestName();

    @Rule
    public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);

    protected void setUpIosDriver() throws Exception {
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("name", testName.getMethodName());
        capabilities.setCapability("appiumVersion", "1.3.0-beta1");
        driver = new IOSDriver(new URL(sauceServer), capabilities);
        setUpDriver();
    }

    protected void setUpAndroidDriver() throws Exception {
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("name", testName.getMethodName());
        capabilities.setCapability("appiumVersion", "1.3.0-beta1");
        driver = new AndroidDriver(new URL(sauceServer), capabilities);
        setUpDriver();
    }

    protected void setUpDriver() throws Exception {
        sessionId = driver.getSessionId().toString();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

}
