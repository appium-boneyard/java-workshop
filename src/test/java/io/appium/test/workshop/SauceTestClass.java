package io.appium.test.workshop;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.SauceOnDemandTestWatcher;
import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.text.MessageFormat;

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

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

}
