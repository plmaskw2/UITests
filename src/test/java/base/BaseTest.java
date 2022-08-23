package base;

import framework.utils.driver_factory.DriverFactory;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import stepdefs.*;

public abstract class BaseTest{

    protected WebDriver driver = DriverFactory.CHROME.getDriverManager().getDriver();
    protected StartupStepdefs startupStepdefs;
    protected NavigationStepdefs navigationStepdefs;
    protected MessagesStepdefs messagesStepdefs;
    protected DashboardStepdefs dashboardStepdefs;
    protected ForumStepdefs forumStepdefs;

    @Step("Initialize stepdefs classes")
    protected void initializeStepdefs() {
        startupStepdefs = new StartupStepdefs(driver);
        navigationStepdefs = new NavigationStepdefs(driver);
        messagesStepdefs = new MessagesStepdefs(driver);
        dashboardStepdefs = new DashboardStepdefs(driver);
        forumStepdefs = new ForumStepdefs(driver);
    }
}
