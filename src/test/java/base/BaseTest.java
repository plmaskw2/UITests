package base;

import framework.utils.ConfigurationUtils;
import framework.utils.driver_factory.DriverFactory;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import stepdefs.*;

public abstract class BaseTest {
    protected WebDriver driver;
    protected StartupStepdefs startupStepdefs;
    protected NavigationStepdefs navigationStepdefs;
    protected MessagesStepdefs messagesStepdefs;
    protected DashboardStepdefs dashboardStepdefs;
    protected ForumStepdefs forumStepdefs;

    @SneakyThrows
    @Step("Initialize stepdefs classes")
    protected void initializeStepdefs() {
        //TODO: Properties and driver initialization move to Before BaseTest class
        ConfigurationUtils.loadProperties();
        driver = DriverFactory.valueOf(ConfigurationUtils.properties.getProperty("driver")).getDriverManager().getDriver();
        startupStepdefs = new StartupStepdefs(driver);
        navigationStepdefs = new NavigationStepdefs(driver);
        messagesStepdefs = new MessagesStepdefs(driver);
        dashboardStepdefs = new DashboardStepdefs(driver);
        forumStepdefs = new ForumStepdefs(driver);
    }
}
