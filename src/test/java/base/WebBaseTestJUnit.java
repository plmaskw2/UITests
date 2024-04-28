package base;

import framework.utils.ConfigurationUtils;
import framework.utils.driver_factory.DriverFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import stepdefs.*;

import java.io.ByteArrayInputStream;

public abstract class WebBaseTestJUnit implements TestLifecycleListener {
    protected WebDriver driver;
    protected StartupStepdefs startupStepdefs;
    protected NavigationStepdefs navigationStepdefs;
    protected MessagesStepdefs messagesStepdefs;
    protected DashboardStepdefs dashboardStepdefs;
    protected ForumStepdefs forumStepdefs;
    @SneakyThrows
    @Step("Initialize stepdefs classes")
    public void initializeStepdefs() {
        //TODO: Properties and driver initialization move to Before BaseTest class
        ConfigurationUtils.loadProperties();
        driver = DriverFactory.valueOf(ConfigurationUtils.properties.getProperty("driver")).getDriverManager().getDriver();
        startupStepdefs = new StartupStepdefs(driver);
        navigationStepdefs = new NavigationStepdefs(driver);
        messagesStepdefs = new MessagesStepdefs(driver);
        dashboardStepdefs = new DashboardStepdefs(driver);
        forumStepdefs = new ForumStepdefs(driver);
    }

    @AfterTest
    public void closeDriver(TestResult result) {
        if (result.getStatus() == Status.FAILED || result.getStatus() == Status.BROKEN) {
            if (driver != null)
                Allure.addAttachment(result.getName(), new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
    }
}
