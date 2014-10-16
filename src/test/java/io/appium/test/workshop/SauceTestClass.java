package io.appium.test.workshop;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.SauceOnDemandTestWatcher;
import org.junit.Rule;
import org.junit.rules.TestName;

import java.text.MessageFormat;

public abstract class SauceTestClass extends AppiumTestClass implements SauceOnDemandSessionIdProvider {
    protected String sessionId;
    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication();
    protected String username = authentication.getUsername();
    protected String accessKey = authentication.getAccessKey();

    @Rule
    public TestName testName = new TestName();

    @Rule
    public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);

    protected void preDriverInit() {
        appiumServer = MessageFormat.format("http://{0}:{1}@ondemand.saucelabs.com:80/wd/hub",
                username, accessKey);
        capabilities.setCapability("name", testName.getMethodName());
        capabilities.setCapability("appiumVersion", "1.3.0-beta1");
        super.preDriverInit();
    }

    protected void postDriverInit() throws Exception {
        sessionId = driver.getSessionId().toString();
        super.postDriverInit();
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

}
