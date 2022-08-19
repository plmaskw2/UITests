package tasks;

import framework.utils.driver_factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import stepdefs.DashboardStepdefs;
import stepdefs.MessagesStepdefs;
import stepdefs.NavigationStepdefs;
import stepdefs.StartupStepdefs;

public abstract class BaseTest{

    protected WebDriver driver = DriverFactory.CHROME.getDriverManager().getDriver();
    protected StartupStepdefs startupStepdefs;
    protected NavigationStepdefs navigationStepdefs;
    protected MessagesStepdefs messagesStepdefs;
    protected DashboardStepdefs dashboardStepdefs;

    protected void initializeStepdefs() {
        startupStepdefs = new StartupStepdefs(driver);
        navigationStepdefs = new NavigationStepdefs(driver);
        messagesStepdefs = new MessagesStepdefs(driver);
        dashboardStepdefs = new DashboardStepdefs(driver);
    }
}
